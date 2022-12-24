package com.sheep4cloud.sap.material.biz.service.impl;

import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.SearchRequest;
import com.sheep4cloud.common.meilisearch.model.SearchRequestDTO;
import com.sheep4cloud.common.meilisearch.model.SearchResultDTO;
import com.sheep4cloud.common.meilisearch.service.MeiliSearchService;
import com.sheep4cloud.sap.material.biz.config.CustomConfig;
import com.sheep4cloud.sap.material.biz.domain.Movies;
import com.sheep4cloud.sap.material.biz.domain.MoviesVo;
import com.sheep4cloud.sap.material.biz.mapper.MoviesMapper;
import com.sheep4cloud.sap.material.biz.remote.MoviesHttpApi;
import com.sheep4cloud.sap.material.biz.service.MoviesService;
import com.sheep4cloud.sap.material.biz.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fa200
 * @description 针对表【movies(movies)】的数据库操作Service实现
 * @createDate 2022-08-05 18:15:56
 */
@Slf4j
@Service
public class MoviesServiceImpl extends ServiceImpl<MoviesMapper, Movies>
        implements MoviesService {
    public static final ObjectMapper mapper = CustomConfig.getObjectMapper();
    @Resource
    private MoviesHttpApi moviesHttpApi;
    @Resource
    MeiliSearchService meiliSearchService;

    @Override
    public Boolean downLoadJson() {
        String s = HttpUtil.get("https://docs.meilisearch.com/movies.json");
        List<Movies> movies = new ArrayList<>();
        try {
            movies = mapper.readValue(s, new TypeReference<List<Movies>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("数据信息");
//        Response<ResponseBody> response = moviesHttpApi.getMoviesData();
//        ResponseBody responseBody = response.body();
//        // 二进制流
//        InputStream inputStream = Objects.requireNonNull(responseBody).byteStream();
//        JSONTokener jsonTokener = new JSONTokener(inputStream, JSONConfig.create());
//        JSONArray json = jsonTokener.toJSONArray();
//        log.info("{}", json.getJSONObject(0));
//        List<Movies> movies = JSONUtil.toList(json, Movies.class);
        return this.saveBatch(movies);
    }

    @Override
    public List<MoviesVo> importData() {
        try {
            SearchRequestDTO dto = new SearchRequestDTO();
            dto.setIndex("movies");
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.setQ("");
            searchRequest.setLimit(1000);
            searchRequest.setOffset(1);
            dto.setSearchRequest(searchRequest);
            SearchResultDTO resultDTO = meiliSearchService.query(dto);
            log.info("{}", JsonUtils.toJson(resultDTO));
        } catch (Exception e) {
            log.error("异常信息:{}", e);
            throw new RuntimeException(e);
        }
        return null;
    }
}




