package br.xksoberbado.multitenancymongo.config;

import br.xksoberbado.multitenancymongo.holder.TenantHolder;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
            .connectionString("mongodb://root:12345@localhost:27018/multi_one?authSource=admin&w=majority")
            .database("multi_one")
            .build();
    }

    private DataSourceConfiguration getMultiTwo() {
        return DataSourceConfiguration.builder()
            .connectionString("mongodb://root:12345@localhost:27019/multi_two?authSource=admin&w=majority")
            .database("multi_two")
            .build();
    }
}
