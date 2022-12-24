package com.sheep4cloud.sap.material.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * movies
 *
 * @TableName movies
 */
@TableName(value = "movies")
@Data
public class Movies implements Serializable {
    /**
     * movies_id
     */
    @TableId(value = "movies_id", type = IdType.AUTO)
    private Long moviesId;

    /**
     * id
     */
    @TableField(value = "id")
    @JsonAlias(value = "id")
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    @JsonAlias(value = "title")
    private String title;

    /**
     * 描述
     */
    @TableField(value = "overview")
    @JsonAlias(value = "overview")
    private String overview;

    /**
     * 图片
     */
    @TableField(value = "poster")
    @JsonAlias(value = "poster")
    private String poster;
    @TableField(value = "release_date")
    @JsonAlias(value = "release_date")
    private String release_date;
    @JsonIgnore
    @TableField(exist = false)
    private String[] genres;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}