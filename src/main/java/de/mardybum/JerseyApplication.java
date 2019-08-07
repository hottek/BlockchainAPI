package de.mardybum;

import de.mardybum.api.instancehelper.BCInstanceFeature;
import de.mardybum.api.V1;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


public class JerseyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(V1.class);
        classes.add(BCInstanceFeature.class);
        return classes;
    }
}
