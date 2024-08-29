package br.xksoberbado.multitenancymongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceConfiguration {

    private String connectionString;
    private String database;

    private MongoClient mongoClient;

    public MongoDatabase getMongoDatabase() {
        return getMongoClient().getDatabase(this.database);
    }

    private MongoClient getMongoClient() {
        if (Objects.isNull(mongoClient)) {
            this.mongoClient = MongoClients.create(this.connectionString);
        }

        return this.mongoClient;
    }
}
