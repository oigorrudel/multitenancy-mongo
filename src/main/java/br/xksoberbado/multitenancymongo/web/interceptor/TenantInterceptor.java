package br.xksoberbado.multitenancymongo.web.interceptor;

import br.xksoberbado.multitenancymongo.error.TenantHeaderNotFoundException;
import br.xksoberbado.multitenancymongo.holder.TenantHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    private static final String TENANT_HEADER = "X-Tenant";

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        var tenant = Optional.ofNullable(request.getHeader(TENANT_HEADER))
            .orElseThrow(TenantHeaderNotFoundException::new);

        TenantHolder.setTenant(tenant);
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response,
                           final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        TenantHolder.clear();
    }

}
