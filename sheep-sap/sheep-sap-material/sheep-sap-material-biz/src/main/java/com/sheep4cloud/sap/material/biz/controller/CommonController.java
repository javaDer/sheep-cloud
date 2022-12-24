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
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/ping")
    public ResultVo<String> ramrodStr() {
        return ResultVo.data(HttpStatus.OK.value(), System.currentTimeMillis() + "", HttpStatus.OK.getReasonPhrase());
    }
}
