package com.rr.desafio_anota_ai.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    @Bean
    public MongoDatabaseFactory mongoConfigurer() {
        return new SimpleMongoClientDatabaseFactory("mongodb://admin:123@localhost:27018/product-catalog?authSource=admin");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoConfigurer());
    }
}
