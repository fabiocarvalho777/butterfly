package com.paypal.butterfly.rest.server.endpoints;

import com.paypal.butterfly.api.ButterflyFacade;
import com.paypal.butterfly.api.Configuration;
import com.paypal.butterfly.api.TransformationResult;
import com.paypal.butterfly.extensions.api.TransformationTemplate;
import com.paypal.butterfly.rest.api.Transformations;
import com.paypal.butterfly.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.File;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Butterfly Transformations service endpoint implementation
 *
 * @author facarvalho
 */
@Component
public class TransformationsImpl implements Transformations {

    @Autowired
    private ButterflyFacade butterflyFacade;

    @Override
    public List<Transformation> getTransformations(int offset, @Min(5) @Max(100) int limit, String user, Date date, Date dateStart, Date dateEnd, TransformationState.State state, String extensionName, String templateClassName, TransformationTemplateType templateType, String versionFrom, String versionTo) {
        Application application = new Application();
        application.setFolder("/Users/jdoe/myapp");

        TransformationRequestSimple request = new TransformationRequestSimple();
        request.setUserId("jdoe");
        request.setExtensionName("com.test");
        request.setTemplateClassName("com.test.MyExtension");
        request.setApplication(application);

        Transformation transformation = new Transformation();
        transformation.setDateTime(Date.from(Instant.now()));
        transformation.setId(34);
        transformation.setTransformationRequestSimple(request);

        return Collections.singletonList(transformation);
    }

    // FIXME
    @Override
    public TransformationState postTransformation(TransformationRequest transformationRequest) {
        TransformationState state = new TransformationState();
        try {
            Configuration configuration = butterflyFacade.newConfiguration(null, false);
            TransformationResult transformationResult = butterflyFacade.transform(
                    new File(transformationRequest.getTransformationRequestSimple().getApplication().getFolder()),
                    (Class<? extends TransformationTemplate>) Class.forName(transformationRequest.getTransformationRequestSimple().getTemplateClassName()),
                    transformationRequest.getUpgradeToVersion(),
                    configuration
            ).get();
            state.setState((transformationResult.isSuccessful() ? TransformationState.State.SUCCEEDED : TransformationState.State.FAILED));
        } catch (ClassNotFoundException e) {
            state.setState(TransformationState.State.INVALID);
            e.printStackTrace();
        } catch (InterruptedException e) {
            state.setState(TransformationState.State.INVALID);
            e.printStackTrace();
        } catch (ExecutionException e) {
            state.setState(TransformationState.State.INVALID);
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public Transformation getTransformationById(String id) {
        return null;
    }

    @Override
    public TransformationState getTransformationStateById(String id) {
        return null;
    }

    @Override
    public TransformationDetails getTransformationDetailsById(String id) {
        return null;
    }

    @Override
    public TransformationMetrics getTransformationMetricsById(String id) {
        return null;
    }

}
