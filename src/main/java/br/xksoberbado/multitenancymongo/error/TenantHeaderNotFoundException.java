package br.xksoberbado.multitenancymongo.error;

public class TenantHeaderNotFoundException extends RuntimeException {

    public TenantHeaderNotFoundException() {
        super("Tenant header not found");
    }
}
