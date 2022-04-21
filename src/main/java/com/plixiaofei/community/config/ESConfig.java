package com.plixiaofei.community.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2022/4/20 by plixiaofei
 */
@Configuration
public class ESConfig {
    // 创建客户端
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClientBuilder builder = RestClient.builder(
                    new HttpHost("localhost", 9200));
        ElasticsearchTransport transport = new RestClientTransport(builder.build(), new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}
