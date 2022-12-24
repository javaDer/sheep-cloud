package com.sheep4cloud.common.meilisearch.model;

import com.meilisearch.sdk.model.SearchResult;
import lombok.Data;

import java.io.Serializable;
@Data
public class SearchResultDTO implements Serializable {
    private static final long serialVersionUID = -8018839873242344260L;
    private String index;

    private SearchResult searchResult;

    private String rawSearchStrResult;

}
