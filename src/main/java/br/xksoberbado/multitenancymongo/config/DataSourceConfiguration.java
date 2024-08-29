package br.xksoberbado.multitenancymongo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.*;

import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceConfiguration {

    private String alias;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    private MongoClient mongoClient;

    public MongoDatabase getMongoDatabase() {
        return getMongoClient().getDatabase(getDatabase());
    }

    private MongoClient getMongoClient() {
        if (Objects.isNull(mongoClient)) {
            var credential = MongoCredential.createCredential(getUsername(), getDatabase(), getPassword().toCharArray());
            this.mongoClient = MongoClients.create(getMongoClientSettings(credential));
        }

        return this.mongoClient;
    }

    private MongoClientSettings getMongoClientSettings(final MongoCredential credential) {
        return MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(new ServerAddress(getHost(), getPort()))))
                .credential(credential)
                .build();
    }

}
