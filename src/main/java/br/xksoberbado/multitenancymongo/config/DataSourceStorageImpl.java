package br.xksoberbado.multitenancymongo.config;

import br.xksoberbado.multitenancymongo.holder.TenantHolder;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceStorageImpl implements DataSourceStorage {

    private Map<String, DataSourceConfiguration> configPerTenant;

    @PostConstruct
    @Lazy
    public void init() {
        configPerTenant = new HashMap<>();
        configPerTenant.put("MULTI_ONE", getMultiOne());
        configPerTenant.put("MULTI_TWO", getMultiTwo());
    }

    @Override
    public MongoDatabase getCurrentDataBase() {
        var tenant = TenantHolder.getTenant();
        var configuration = configPerTenant.get(tenant);

        return configuration.getMongoDatabase();
    }

    private DataSourceConfiguration getMultiOne() {
        return DataSourceConfiguration.builder()
                .alias("multi_one")
                .host("localhost")
                .port(27018)
                .database("multi_one")
                .username("root")
                .password("12345")
                .build();
    }

    private DataSourceConfiguration getMultiTwo() {
        return DataSourceConfiguration.builder()
                .alias("multi_two")
                .host("localhost")
                .port(27019)
                .database("multi_two")
                .username("root")
                .password("12345")
                .build();
    }
}
