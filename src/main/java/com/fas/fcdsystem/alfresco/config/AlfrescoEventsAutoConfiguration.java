package com.fas.fcdsystem.alfresco.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alfresco.event.sdk.autoconfigure.AlfrescoEventsProperties;
import org.alfresco.event.sdk.handling.EventHandlingExecutor;
import org.alfresco.event.sdk.handling.EventHandlingRegistry;
import org.alfresco.event.sdk.handling.SimpleEventHandlingExecutor;
import org.alfresco.event.sdk.handling.handler.EventHandler;
import org.alfresco.event.sdk.integration.EventChannels;
import org.alfresco.event.sdk.integration.transformer.EventGenericTransformer;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.transformer.GenericTransformer;

import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AutoConfigureAfter({ IntegrationAutoConfiguration.class, ActiveMQAutoConfiguration.class, JmsAutoConfiguration.class })
@ConditionalOnClass(RepoEvent.class)
@EnableConfigurationProperties(AlfrescoEventsProperties.class)
public class AlfrescoEventsAutoConfiguration {

    @Autowired(required = false)
    private final List<EventHandler> eventHandlers = new ArrayList<>();
    @Autowired
    private AlfrescoEventsProperties alfrescoEventsProperties;

    // CORE INTEGRATION WITH BROKER CONFIGURATION
    @Bean
    public IntegrationFlow acsEventsListeningFlow(final ActiveMQConnectionFactory activeMQConnectionFactory) {
        return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(activeMQConnectionFactory)
                        .destination(acsEventsTopic())
                        .errorChannel(acsEventErrorChannel()))
                .transform(acsEventTransformer())
                .routeToRecipients(route -> route
                        .recipient(EventChannels.SPRING_INTEGRATION, s -> alfrescoEventsProperties.isEnableSpringIntegration())
                        .recipient(EventChannels.HANDLERS, s -> alfrescoEventsProperties.isEnableHandlers()))
                .get();
    }

    @Bean
    public Topic acsEventsTopic() {
        return new ActiveMQTopic(alfrescoEventsProperties.getTopicName());
    }

    @Bean(name = EventChannels.ERROR)
    public DirectChannel acsEventErrorChannel() {
        return new DirectChannel();
    }

    @Bean
    public GenericTransformer<String, RepoEvent<DataAttributes<Resource>>> acsEventTransformer() {
        return new EventGenericTransformer();
    }

    // SPRING INTEGRATION OPTION CONFIGURATION
    @Bean
    public IntegrationFlow acsEventsSpringIntegrationFlow() {
        return IntegrationFlows.from(EventChannels.SPRING_INTEGRATION)
                .log(LoggingHandler.Level.DEBUG)
                .channel(acsEventChannel())
                .get();
    }

    @Bean(name = EventChannels.MAIN)
    public PublishSubscribeChannel acsEventChannel() {
        return new PublishSubscribeChannel();
    }

    // PLAIN HANDLERS OPTION CONFIGURATION
    @Bean
    public IntegrationFlow acsEventsHandlersFlow() {
        return IntegrationFlows.from(EventChannels.HANDLERS)
                .log(LoggingHandler.Level.DEBUG)
                .handle(s -> eventHandlingExecutor().executeEventHandlers((RepoEvent<DataAttributes<Resource>>) s.getPayload()))
                .get();
    }

    @Bean
    public EventHandlingExecutor eventHandlingExecutor() {
        return new SimpleEventHandlingExecutor(eventHandlingRegistry());
    }

    @Bean
    public EventHandlingRegistry eventHandlingRegistry() {
        return new EventHandlingRegistry(eventHandlers);
    }
}