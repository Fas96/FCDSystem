package com.fas.fcdsystem.alfresco.events.handler;

import org.alfresco.event.sdk.handling.handler.OnNodeUpdatedEventHandler;

import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.NodeResource;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ContentUpdatedEventHandler implements OnNodeUpdatedEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentUpdatedEventHandler.class);

    public void handleEvent(final RepoEvent<DataAttributes<Resource>> repoEvent) {
        // Get the data for the node as it looked like before the update
        NodeResource beforeUpdateResource = (NodeResource) repoEvent.getData().getResourceBefore();
        ZonedDateTime prevModificationDate = beforeUpdateResource.getModifiedAt();
        LOGGER.info("Before this update the node was last updated {}", prevModificationDate);
        Set<String> beforeAspects = beforeUpdateResource.getAspectNames();
        if (beforeAspects != null) {
            LOGGER.info("Aspects before the update: ");
            for (String aspectName : beforeAspects) {
                LOGGER.info("    {}", aspectName);
            }
        }
        Map<String, Serializable> beforeProperties = beforeUpdateResource.getProperties();
        if (beforeProperties != null) {
            LOGGER.info("Properties before the update");
            for (Map.Entry<String, Serializable> property : beforeProperties.entrySet()) {
                LOGGER.info("    {} = {}", property.getKey(), property.getValue());
            }
        }

        // Get the latest data for the node
        NodeResource afterUpdateResource = (NodeResource) repoEvent.getData().getResource();
        LOGGER.info("Node data after update:");
        LOGGER.info("    ID: {}", afterUpdateResource.getId());
        LOGGER.info("    Name (cm:name): {}", afterUpdateResource.getName());
        LOGGER.info("    Content Model Type: {}", afterUpdateResource.getNodeType());
        LOGGER.info("    Created date (cm:created): {}", afterUpdateResource.getCreatedAt());
        LOGGER.info("    Created by (cm:creator): {}", afterUpdateResource.getCreatedByUser().getDisplayName());
        LOGGER.info("    Modified date (cm:modified): {}", afterUpdateResource.getModifiedAt());
        LOGGER.info("    Modified by (cm:modifier): {}", afterUpdateResource.getModifiedByUser().getDisplayName());
        if (afterUpdateResource.getContent() != null) {
            LOGGER.info("    Content (cm:content): {}, {}, {} bytes", afterUpdateResource.getContent().getMimeType(),
                    afterUpdateResource.getContent().getEncoding(), afterUpdateResource.getContent().getSizeInBytes());
        }
        Set<String> afterAspects = afterUpdateResource.getAspectNames();
        LOGGER.info("Aspects after the update");
        for (String aspectName: afterAspects) {
            LOGGER.info("    {}", aspectName);
        }
        Map<String, Serializable> afterProperties = afterUpdateResource.getProperties();
        LOGGER.info("Properties after the update");
        for (Map.Entry<String, Serializable> property: afterProperties.entrySet()) {
            LOGGER.info("    {} = {}", property.getKey(), property.getValue());
        }

        // Get the node location hierarchy in the repository
        // Use the ReST API to query for the name of the nodes
        List<String> nodeHierarchy = afterUpdateResource.getPrimaryHierarchy();
        LOGGER.info("Node location hierarchy (immediate parent node first):");
        for (String nodeID: nodeHierarchy) {
            LOGGER.info("    {}", nodeID);
        }
    }
}

