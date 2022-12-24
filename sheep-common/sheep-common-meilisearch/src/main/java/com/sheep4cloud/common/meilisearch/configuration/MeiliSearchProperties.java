package com.sheep4cloud.common.meilisearch.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "meilisearch")
@Data
public class MeiliSearchProperties {
    private String url;
    private String apiKey;
}
