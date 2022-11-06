package com.fas.fcdsystem.alfresco.events.handler;

import com.fas.fcdsystem.alfresco.events.filter.ParentFolderFilter;
import com.google.gson.internal.LinkedTreeMap;
import org.alfresco.core.handler.NodesApi;
import org.alfresco.core.model.NodeBodyUpdate;
import org.alfresco.event.sdk.handling.filter.EventFilter;
import org.alfresco.event.sdk.handling.filter.IsFileFilter;
import org.alfresco.event.sdk.handling.handler.OnNodeCreatedEventHandler;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.NodeResource;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentUploadedEventHandler implements OnNodeCreatedEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentUploadedEventHandler.class);

    @Autowired
    NodesApi nodesApi;

    private String folderID = "e0cb99dc-81ed-4b5d-9fe8-a455f078c3f3";

    public void handleEvent(final RepoEvent<DataAttributes<Resource>> repoEvent) {

        NodeResource nodeResource = (NodeResource) repoEvent.getData().getResource();
        LOGGER.info("======================================");
        LOGGER.info(String.valueOf(nodeResource));
        LOGGER.info("======================================");

        LOGGER.info("Handler - A file was uploaded to the repository: {}, {}, {}", nodeResource.getId(), nodeResource.getNodeType(), nodeResource.getName());
        LinkedTreeMap<String, Object> properties = new LinkedTreeMap<>();
        properties.put("cm:title", "ALFRESCO");
        properties.put("cm:description", "The Description");
        NodeBodyUpdate nodeBodyUpdate = new NodeBodyUpdate();
        LOGGER.info("Handler - Setting title and description");
        nodesApi.updateNode(nodeResource.getId(), nodeBodyUpdate.properties(properties), null, null);
    }

    public EventFilter getEventFilter() {
        LOGGER.info("Event Filter method entered");
        return IsFileFilter.get()
                .and(ParentFolderFilter.of(folderID));

    }
}