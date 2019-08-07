package de.mardybum.api.instancehelper;

import de.mardybum.api.BCInstance;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.ext.Provider;

@Provider
public class BCInstanceBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(new BCInstance()).to(BCInstance.class);
    }
}
