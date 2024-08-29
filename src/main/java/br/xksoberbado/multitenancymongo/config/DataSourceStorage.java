package br.xksoberbado.multitenancymongo.config;

import com.mongodb.client.MongoDatabase;

public interface DataSourceStorage {

    MongoDatabase getCurrentDataBase();

}
