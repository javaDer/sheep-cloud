package com.sheep4cloud.common.meilisearch.configuration;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.sheep4cloud.common.meilisearch.service.DefaultMeiliSearchService;
import com.sheep4cloud.common.meilisearch.service.MeiliSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import static com.sheep4cloud.common.meilisearch.support.Constant.BeanName.DEFAULT_MEILISEARCH_CLIENT_BEAN_NAME;

@Configuration
@Slf4j
@EnableConfigurationProperties(MeiliSearchProperties.class)
public class MeiliSearchAutoConfiguration {
    @Autowired
    private MeiliSearchProperties meiliSearchClientProperties;

    @Bean(name = DEFAULT_MEILISEARCH_CLIENT_BEAN_NAME)
    @ConditionalOnMissingBean
    @Primary
    public Client meiliSearchClient() {
        log.debug("start create meilisearch client...");
        Config config =
                new Config(
                        meiliSearchClientProperties.getUrl(), meiliSearchClientProperties.getApiKey());
        Client client = new Client(config);
        log.debug("end create meilisearch client...");
        return client;
    }

    @Bean
    @ConditionalOnMissingBean
    @DependsOn(value = {DEFAULT_MEILISEARCH_CLIENT_BEAN_NAME})
    public MeiliSearchService meiliSearchService() {
        return new DefaultMeiliSearchService();
    }

}
