package br.xksoberbado.multitenancymongo.holder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantHolder {

    private static final ThreadLocal<String> currentThreadLocal = new ThreadLocal<>();

    public static void setTenant(String tenant) {
        log.info("*** TENANT: {}", tenant);
        currentThreadLocal.set(tenant);
    }

    public static String getTenant() {
        return currentThreadLocal.get();
    }

    public static void clear() {
        currentThreadLocal.remove();
    }
}
