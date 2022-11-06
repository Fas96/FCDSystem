package com.fas.fcdsystem.alfresco.events.filter;

import org.alfresco.event.sdk.handling.filter.AbstractEventFilter;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.NodeResource;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;

import java.util.Objects;

/**
 * Filter that can be used when a node needs to be in a specific folder.
 */
public class ParentFolderFilter extends AbstractEventFilter {
    // The node ID for the folder we want to check against
    private final String parentId;

    // Private constructor, make sure ID is not null
    private ParentFolderFilter(final String parentId) {
        this.parentId = Objects.requireNonNull(parentId);
    }

    // When using the filter, pass in the folder node ID we want to check against
    public static ParentFolderFilter of(final String parentId) {
        return new ParentFolderFilter(parentId);
    }

    // The actual test:
    // get the node resource we are testing (such as a file node),
    // then get its primary parent folder ID and check if it matches desired folder Node ID
    public boolean test(RepoEvent<DataAttributes<Resource>> event) {
        NodeResource resource = (NodeResource) event.getData().getResource();
        boolean parentFound = resource.getPrimaryHierarchy().get(0).equals(parentId);
        return isNodeEvent(event) && parentFound;
    }
}