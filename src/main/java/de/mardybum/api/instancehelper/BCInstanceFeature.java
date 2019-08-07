package de.mardybum.api.instancehelper;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class BCInstanceFeature implements Feature {
    @Override
    public boolean configure(FeatureContext featureContext) {
        featureContext.register(new BCInstanceBinder());
        return true;
    }
}
