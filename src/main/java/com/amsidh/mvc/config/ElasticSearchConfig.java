/*
package com.amsidh.mvc.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfig {
    @Value("${elasticsearch.host}")
    private String elasticHost;

    @Value("${elasticsearch.port}")
    private int elasticPort;

    @Value("${connectionTimeout}")
    private Long connectionTimeout;

    @Value("${elasticUserName}")
    private String elasticUserName;

    @Value("${elasticPassword}")
    private String elasticPassword;

    @Bean
    public RestHighLevelClient getRestHighLevelClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(String.format("%s:%s", elasticHost, elasticPort))
                .withConnectTimeout(connectionTimeout)
                .withBasicAuth(elasticUserName, elasticPassword)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }


}
*/
