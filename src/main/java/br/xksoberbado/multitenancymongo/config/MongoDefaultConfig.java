package br.xksoberbado.multitenancymongo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class MongoDefaultConfig {

    private final MongoProperties mongoProperties;

    @Bean
    public String databaseName() {
        return mongoProperties.getDatabase();
    }

    @Bean
    public MongoClient mongoClient() {
        var credential = MongoCredential.createCredential(
                mongoProperties.getUsername(),
                mongoProperties.getDatabase(),
                mongoProperties.getPassword()
        );

        return MongoClients.create(getMongoClientSettings(credential));
    }

    private MongoClientSettings getMongoClientSettings(final MongoCredential credential) {
        return MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .credential(credential)
                .build();
    }

}
