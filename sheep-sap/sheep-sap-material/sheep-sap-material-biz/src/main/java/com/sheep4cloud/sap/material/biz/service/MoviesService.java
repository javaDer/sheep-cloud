package com.sheep4cloud.sap.material.biz.service;

import com.sheep4cloud.sap.material.biz.domain.Movies;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sheep4cloud.sap.material.biz.domain.MoviesVo;

import java.util.List;

/**
* @author fa200
* @description 针对表【movies(movies)】的数据库操作Service
* @createDate 2022-08-05 18:15:56
*/
public interface MoviesService extends IService<Movies> {

    Boolean downLoadJson();

    List<MoviesVo> importData();
}
