package com.sheep4cloud.common.meilisearch.model;

import com.meilisearch.sdk.SearchRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchRequestDTO implements Serializable {
    private static final long serialVersionUID = 5173750962679666620L;
    private String index;

    private SearchRequest searchRequest;

    private String queryStr;
}
