package com.fas.fcdsystem.Pages.alf;

import ch.qos.logback.classic.Logger;
import com.fas.fcdsystem.alfresco.api.ListFolderContentApi;
import org.alfresco.core.model.NodeChildAssociationPagingList;
import org.alfresco.search.handler.SearchApi;
import org.alfresco.search.model.RequestQuery;
import org.alfresco.search.model.ResultSetPaging;
import org.alfresco.search.model.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class FileFoldersController {
    private static final Logger LOGGER = (Logger) org.slf4j.LoggerFactory.getLogger(FileFoldersController.class);
    private final ListFolderContentApi listFolderContentApi;
   private final SearchApi searchApi;
    public FileFoldersController(ListFolderContentApi listFolderContentApi, SearchApi searchApi) {
        this.listFolderContentApi = listFolderContentApi;
        this.searchApi = searchApi;
    }
    @GetMapping("/folders")
    public String getFoldersList() throws IOException {
        listFolderContentApi.listFolderContent("user/home", "/nodes/user/home/children");
        return "layout/home/index";
    }

    @GetMapping("/search")
    @ResponseBody
    public  ResponseEntity<ResultSetPaging> searchResult(String siteId, String term) {
        siteId = "swsdp";
        term = "est";

        ResponseEntity<ResultSetPaging> result = searchApi.search(new SearchRequest()
                .query(new RequestQuery()
                        .language(RequestQuery.LanguageEnum.AFTS)
                        .query("(SITE:\"" + siteId + "\" AND TEXT:\"" + term + "\" )")));

        LOGGER.info("Search result: {}", result.getBody().getList().getEntries());
        return  result;
    }

}
