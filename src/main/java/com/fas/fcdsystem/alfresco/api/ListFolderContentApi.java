package com.fas.fcdsystem.alfresco.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.alfresco.core.handler.NodesApi;
import org.alfresco.core.handler.SitesApi;
import org.alfresco.core.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component("listFolderContentApi")
public class ListFolderContentApi {
    static final Logger LOGGER = LoggerFactory.getLogger(ListFolderContentApi.class);

    SitesApi sitesApi;



   private final NodesApi nodesApi;

    public ListFolderContentApi(NodesApi nodesApi) {
        this.nodesApi = nodesApi;
    }


    public NodeChildAssociationPagingList listFolderContent(String rootNodeId, String relativeFolderPath) {
        Integer skipCount = 0;
        Integer maxItems = 100;
        List<String> include = null;
        List<String> fields = null;
        List<String> orderBy = null;
        String where = null;
        Boolean includeSource = false;

        LOGGER.info("Listing folder {}{}", rootNodeId, relativeFolderPath);
        NodeChildAssociationPagingList result = nodesApi.listNodeChildren(rootNodeId, skipCount, maxItems, orderBy, where, include,
                relativeFolderPath, includeSource, fields).getBody().getList();
        if(result!= null) {
            for (NodeChildAssociationEntry childNodeAssoc: result.getEntries()) {
                LOGGER.info("Found node [name=" + childNodeAssoc.getEntry().getName() + "]");
            }
        }


        return result;
    }
}