package com.sheep4cloud.sap.material.biz;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RetrofitScan("com.sheep4cloud.sap.material.biz.remote")
public class SapMaterialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SapMaterialApplication.class, args);
    }
}
