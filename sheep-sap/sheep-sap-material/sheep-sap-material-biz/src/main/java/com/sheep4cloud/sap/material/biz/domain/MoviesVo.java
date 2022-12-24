package com.sheep4cloud.sap.material.biz.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class MoviesVo implements Serializable {
    /**
     * movies_id
     */
    private Long moviesId;

    /**
     * id
     */
    @JsonAlias(value = "id")
    private Long id;

    /**
     * 标题
     */
    @JsonAlias(value = "title")
    private String title;

    /**
     * 描述
     */
    @JsonAlias(value = "overview")
    private String overview;

    /**
     * 图片
     */
    @JsonAlias(value = "poster")
    private String poster;
    @JsonAlias(value = "release_date")
    private String release_date;
    @JsonIgnore
    private String[] genres;
}
