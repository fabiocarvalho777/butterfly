package com.paypal.butterfly.rest.server.endpoints;

import com.paypal.butterfly.api.ButterflyFacade;
import com.paypal.butterfly.rest.api.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Butterfly Info service endpoint implementation
 *
 * @author facarvalho
 */
@Component
public class InfoImpl implements Info {

    @Autowired
    private ButterflyFacade butterflyFacade;

    @Override
    public String getVersion() {
        return butterflyFacade.getButterflyVersion();
    }

}
