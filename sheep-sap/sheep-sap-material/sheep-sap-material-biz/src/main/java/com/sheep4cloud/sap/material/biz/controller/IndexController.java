package com.sheep4cloud.sap.material.biz.controller;

import com.sheep4cloud.common.base.api.ResultVo;
import com.sheep4cloud.sap.material.biz.domain.MoviesVo;
import com.sheep4cloud.sap.material.biz.service.MoviesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {
    @Resource
    private MoviesService moviesService;

    @GetMapping("/str")
    public ResultVo<String> ramrodStr() {
        return ResultVo.data(HttpStatus.OK.value(), System.currentTimeMillis() + "", HttpStatus.OK.getReasonPhrase());
    }

    @GetMapping("/downLoad")
    public ResultVo<Boolean> downLoadMovies() {
        log.info("下载movies数据信息");
        Optional<Boolean> result = Optional.ofNullable(moviesService.downLoadJson());
        return result.map(success -> ResultVo.data(HttpStatus.OK.value(), success, HttpStatus.OK.name())).orElseGet(() -> ResultVo.data(HttpStatus.OK.value(), Boolean.FALSE, "数据为空"));
    }

    @GetMapping("/import")
    public ResultVo<List<MoviesVo>> importData() {
        Optional<List<MoviesVo>> result = Optional.ofNullable(moviesService.importData());
        return result.map(success -> ResultVo.data(HttpStatus.OK.value(), success, HttpStatus.OK.name())).orElseGet(() -> ResultVo.data(HttpStatus.OK.value(), Collections.emptyList(), "数据为空"));
    }
}
