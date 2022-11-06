package com.fas.fcdsystem.alfresco.events.handler;



        import org.alfresco.event.sdk.handling.filter.EventTypeFilter;
        import org.alfresco.event.sdk.handling.filter.IsFileFilter;
        import org.alfresco.event.sdk.integration.EventChannels;
        import org.alfresco.event.sdk.integration.filter.IntegrationEventFilter;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.context.annotation.Bean;
        import org.springframework.integration.dsl.IntegrationFlow;
        import org.springframework.integration.dsl.IntegrationFlowAdapter;
        import org.springframework.integration.dsl.IntegrationFlowDefinition;
        import org.springframework.integration.dsl.IntegrationFlows;
        import org.springframework.messaging.MessageHandlingException;
        import org.springframework.stereotype.Component;

/**
 * Spring Integration based event handler that will execute code when a file is uploaded
 */
@Component
public class NewContentFlowEventHandler extends IntegrationFlowAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewContentFlowEventHandler.class);

    // Use builder to create an integration flow based on alfresco.events.main.channel event channel
    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        return from(EventChannels.MAIN) // Listen to events coming from the Alfresco events channel
                .filter(IntegrationEventFilter.of(EventTypeFilter.NODE_CREATED)) // Filter events and select only node created events
                .filter(IntegrationEventFilter.of(IsFileFilter.get())) // Filter node and make sure it is a file node
                .handle(t -> LOGGER.info("File uploaded: {}", t.getPayload().toString())); // Handle event with a bit of logging
    }

    @Bean
    public IntegrationFlow logCreateFileNode() {
        return IntegrationFlows.from(EventChannels.MAIN) // Listen to events coming from the Alfresco events channel
                .filter(IntegrationEventFilter.of(EventTypeFilter.NODE_CREATED)) // Filter events and select only node created events
                .filter(IntegrationEventFilter.of(IsFileFilter.get())) // Filter node and make sure it is a file node
                .handle(t -> LOGGER.info("File uploaded: {}", t.getPayload().toString())) // Handle event with a bit of logging
                .get();
    }
    @Bean
    public IntegrationFlow logError() {
        return IntegrationFlows.from(EventChannels.ERROR).handle(t -> {
            LOGGER.info("Error: {}", t.getPayload().toString());
            MessageHandlingException exception = (MessageHandlingException) t.getPayload();
            exception.printStackTrace();
        }).get();
    }
}
