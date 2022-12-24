package com.sheep4cloud.common.meilisearch.service;

import com.alibaba.fastjson.JSONObject;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.Task;
import com.meilisearch.sdk.model.SearchResult;
import com.sheep4cloud.common.meilisearch.model.SearchRequestDTO;
import com.sheep4cloud.common.meilisearch.model.SearchResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Slf4j
public class DefaultMeiliSearchService implements MeiliSearchService {
    @Resource
    Client meiliSearchClient;

    public DefaultMeiliSearchService() {
        log.debug("MeiliSearch Client信息:{}", JSONObject.toJSONString(meiliSearchClient));
    }

    @Override
    public SearchResultDTO query(SearchRequestDTO searchRequestModel) throws Exception {
        String index = searchRequestModel.getIndex();
        if (StringUtils.isEmpty(index.trim())) {
            throw new RuntimeException("index illegal");
        }
        SearchRequest searchRequest = searchRequestModel.getSearchRequest();
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        SearchResult search = meiliSearchClient.index(index).search(searchRequest);
        searchResultDTO.setIndex(index);
        searchResultDTO.setSearchResult(search);
        log.debug("[rawSearchStr#query:{},searchResult:{} ]", searchRequestModel, searchResultDTO);
        return searchResultDTO;
    }

    @Override
    public String rawSearchStr(SearchRequestDTO searchRequestDTO) throws Exception {
        String index = searchRequestDTO.getIndex();
        if (StringUtils.isEmpty(index.trim())) {
            throw new RuntimeException("index illegal");
        }
        SearchRequest searchRequest = searchRequestDTO.getSearchRequest();
        String searchResult = meiliSearchClient.index(index).rawSearch(searchRequest);
        log.debug("[rawSearchStr#query:{},searchResult:{} ]", searchRequestDTO, searchResult);
        return searchResult;
    }

    @Override
    public SearchResultDTO rawSearch(SearchRequestDTO searchRequestDTO) throws Exception {
        String index = searchRequestDTO.getIndex();
        if (StringUtils.isEmpty(index.trim())) {
            throw new RuntimeException("index illegal");
        }
        SearchRequest searchRequest = searchRequestDTO.getSearchRequest();
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        String search = meiliSearchClient.index(index).rawSearch(searchRequest);
        searchResultDTO.setIndex(index);
        searchResultDTO.setRawSearchStrResult(search);
        log.debug("[search#query:{},searchResultDTO:{} ]", searchRequestDTO, searchResultDTO);
        return searchResultDTO;
    }

    @Override
    public SearchResultDTO search(SearchRequestDTO query) throws Exception {
        if (StringUtils.isEmpty(query.getIndex().trim())) {
            throw new RuntimeException("index illegal");
        }
        String searchRequest = query.getQueryStr();
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        SearchResult search = meiliSearchClient.index(query.getIndex()).search(searchRequest);
        searchResultDTO.setIndex(query.getIndex());
        searchResultDTO.setSearchResult(search);
        log.debug("[search#query:{},searchResultDTO:{} ]", query, searchResultDTO);
        return searchResultDTO;
    }

    @Override
    public Task addDocuments(String index, String json) throws Exception {
        log.debug("[addDocuments#index:{},json:{}]", index, json);
        Task task = addDocuments(index, json, null);
        log.debug("[addDocuments#task:{} ]", task);
        return task;

    }

    @Override
    public Task addDocuments(String index, String json, String privateKey) throws Exception {
        log.debug("[addDocuments#index:{},json:{},privateKey:{}]", index, json, privateKey);

        if (StringUtils.isEmpty(index.trim())) {
            throw new RuntimeException("index illegal");
        }
        if (StringUtils.isEmpty(json.trim())) {
            throw new RuntimeException("json data illegal");
        }
        Task task = meiliSearchClient.index(index).addDocuments(json, privateKey);
        log.debug("[addDocuments#tasks:{} ]", task);
        return task;
    }

    @Override
    public Task[] addDocumentsInBatches(String index, String json) throws Exception {
        log.debug("[addDocumentsInBatches#index:{},json:{}]", index, json);

        Task[] tasks = addDocumentsInBatches(index, json, 1000, null);
        log.debug("[addDocumentsInBatches#tasks:{} ]", tasks);
        return tasks;
    }

    @Override
    public Task[] addDocumentsInBatches(String index, String json, int batches, String privateKey) throws Exception {
        log.debug("[addDocumentsInBatches#index:{},json:{},batches:{},privateKey:{}]", index, json, batches, privateKey);

        if (StringUtils.isEmpty(index.trim())) {
            throw new RuntimeException("index illegal");
        }
        if (StringUtils.isEmpty(json.trim())) {
            throw new RuntimeException("json data illegal");
        }
        Task[] tasks = meiliSearchClient.index(index).addDocumentsInBatches(json, batches, privateKey);
        log.debug("[addDocumentsInBatches#tasks:{} ]", tasks);
        return tasks;
    }

    @Override
    public Task deleteAllDocuments(String index) throws Exception {
        log.debug("[deleteAllDocuments#index:{}]", index);
        Task task = meiliSearchClient.index(index).deleteAllDocuments();
        log.debug("[deleteAllDocuments#task:{} ]", task);
        return task;
    }

    @Override
    public Task deleteDocument(String index, String identifiers) throws Exception {
        log.debug("[deleteAllDocument#index:{}]", index);
        Task task = deleteDocuments(index, Collections.singletonList(identifiers));
        log.debug("[deleteAllDocuments#task:{} ]", task);
        return task;
    }

    @Override
    public Task deleteDocuments(String index, List<String> identifiers) throws Exception {
        log.debug("[deleteAllDocuments#index:{}]", index);
        Task task = meiliSearchClient.index(index).deleteDocuments(identifiers);
        log.debug("[deleteAllDocuments#task:{} ]", task);
        return task;
    }

    @Override
    public String getDocuments(String index) throws Exception {
        log.debug("[getDocuments#index:{}]", index);
        String documents = meiliSearchClient.index(index).getDocuments();
        log.debug("[getDocuments#documents:{} ]", documents);
        return documents;
    }

    @Override
    public String getDocuments(String index, int limit, int offset) throws Exception {
        log.debug("[getDocuments#index:{}]", index);
        String documents = meiliSearchClient.index(index).getDocuments(limit, offset);
        log.debug("[getDocuments#documents:{} ]", documents);
        return documents;
    }

    @Override
    public String getDocuments(String index, int limit) throws Exception {
        log.debug("[getDocuments#index:{}]", index);
        String documents = meiliSearchClient.index(index).getDocuments(limit);
        log.debug("[getDocuments#documents:{} ]", documents);
        return documents;
    }

    @Override
    public String getDocument(String index, String identifier) throws Exception {
        log.debug("[getDocuments#index:{}]", index);
        String documents = meiliSearchClient.index(index).getDocument(identifier);
        log.debug("[getDocuments#task:{} ]", documents);
        return documents;
    }

    @Override
    public Task updateDocuments(String index, String data) throws Exception {
        log.debug("[updateDocuments#index:{}]", index);
        Task task = meiliSearchClient.index(index).updateDocuments(data, null);
        log.debug("[updateDocuments#task:{} ]", task);
        return task;
    }

    @Override
    public Task updateDocuments(String index, String document, String primaryKey) throws Exception {
        log.debug("[updateDocuments#index:{},primaryKey:{}]", index, primaryKey);
        Task task = meiliSearchClient.index(index).updateDocuments(document, primaryKey);
        log.debug("[updateDocuments#task:{} ]", task);
        return task;
    }

    @Override
    public Task[] updateDocumentsInBatches(String index, String document, String primaryKey) throws Exception {
        log.debug("[updateDocumentsInBatches#index:{},primaryKey:{}]", index, primaryKey);
        Task[] task = updateDocumentsInBatches(index, document, 1000, primaryKey);
        return task;
    }

    @Override
    public Task[] updateDocumentsInBatches(String index, String document) throws Exception {
        log.debug("[updateDocumentsInBatches#index:{},doc:{}]", index, document);
        Task[] task = updateDocumentsInBatches(index, document, 1000, null);
        return task;
    }

    @Override
    public Task[] updateDocumentsInBatches(String index, String document, Integer batchSize, String primaryKey) throws Exception {
        log.debug("[updateDocumentsInBatches#index:{},doc:{}]", index, document);
        Task[] task = meiliSearchClient.index(index).updateDocumentsInBatches(document, batchSize, primaryKey);
        log.debug("[updateDocumentsInBatches#tasks:{} ]", task);
        return task;
    }

    @Override
    public String[] getDisplayedAttributesSettings(String index) throws Exception {
        log.debug("[getDisplayedAttributesSettings#index:{}]", index);
        String[] settings = meiliSearchClient.index(index).getDisplayedAttributesSettings();
        log.debug("[getDisplayedAttributesSettings#settings:{} ]", settings);
        return settings;
    }

    @Override
    public Task getTask(String index, int taskId) throws Exception {
        log.debug("[getTask#index:{},taskId:{}]", index, taskId);
        Task task = meiliSearchClient.index(index).getTask(taskId);
        log.debug("[getTask#task:{} ]", task);
        return task;
    }
}
