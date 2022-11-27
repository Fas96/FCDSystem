package com.fas.fcdsystem.alfresco.events.handler;

import org.alfresco.event.sdk.handling.handler.OnChildAssocCreatedEventHandler;
import org.alfresco.event.sdk.model.v1.model.ChildAssociationResource;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class ParentChildAssocCreatedEventHandler implements OnChildAssocCreatedEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParentChildAssocCreatedEventHandler.class);
    public void handleEvent(final RepoEvent<DataAttributes<Resource>> repoEvent) {
        ChildAssociationResource resource = (ChildAssociationResource) repoEvent.getData().getResource();
        LOGGER.info("A secondary Parent-Child association of type {} was created between nodes: {} -> {}",
                resource.getAssocType(), resource.getParent().getId(), resource.getChild().getId());
    }
}

