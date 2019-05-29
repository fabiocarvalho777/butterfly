package com.paypal.butterfly.rest.server.endpoints;

import com.paypal.butterfly.rest.api.ButterflyApi;
import com.paypal.butterfly.rest.api.Info;
import com.paypal.butterfly.rest.api.Transformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Butterfly REST API root resource
 * service endpoint implementation
 *
 * @author facarvalho
 */
@Component
public class ButterflyApiImpl implements ButterflyApi {

    @Autowired
    private Transformations transformations;

    @Autowired
    private Info info;

    @Override
    public Info info() {
        return info;
    }

    @Override
    public Transformations transformations() {
        return transformations;
    }

}
