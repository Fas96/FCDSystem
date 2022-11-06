package com.fas.fcdsystem.alfresco.events.handler;

import org.alfresco.event.sdk.handling.handler.OnPeerAssocCreatedEventHandler;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.PeerAssociationResource;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Peer2PeerAssocCreatedEventHandler implements OnPeerAssocCreatedEventHandler {

     private static final Logger LOGGER = LoggerFactory.getLogger(Peer2PeerAssocCreatedEventHandler.class);

    public void handleEvent(final RepoEvent<DataAttributes<Resource>> repoEvent) {
        PeerAssociationResource resource = (PeerAssociationResource) repoEvent.getData().getResource();
        LOGGER.info("A Peer-Peer association was created: Assoc Type {}: Source {} -> Target {}", resource.getAssocType(),
                resource.getSource().getId(), resource.getTarget().getId());
    }
}
