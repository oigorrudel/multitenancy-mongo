package br.xksoberbado.multitenancymongo.web.interceptor;

import br.xksoberbado.multitenancymongo.holder.TenantHolder;
import br.xksoberbado.multitenancymongo.error.TenantHeaderNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    private static final String TENANT_HEADER = "X-Tenant";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var tenant = Optional.ofNullable(request.getHeader(TENANT_HEADER))
                .orElseThrow(TenantHeaderNotFoundException::new);

        TenantHolder.setTenant(tenant);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TenantHolder.clear();
    }

}
