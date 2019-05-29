package com.paypal.butterfly.rest.server;

import com.paypal.butterfly.rest.server.endpoints.ButterflyApiImpl;
import com.paypal.butterfly.rest.server.filters.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JaxrsApplication extends ResourceConfig {

    public JaxrsApplication() {
        register(ButterflyApiImpl.class);
        register(CorsFilter.class);
    }

}