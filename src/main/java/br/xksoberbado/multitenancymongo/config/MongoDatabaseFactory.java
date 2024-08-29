package br.xksoberbado.multitenancymongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDatabaseFactory extends SimpleMongoClientDatabaseFactory {

    @Autowired
    private DataSourceStorage dataSourceStorage;

    public MongoDatabaseFactory(final MongoClient mongoClient, final String databaseName) {
        super(mongoClient, databaseName);
    }

    @Override
    protected MongoDatabase doGetMongoDatabase(final String dbName) {
        return dataSourceStorage.getCurrentDataBase();
    }
}
