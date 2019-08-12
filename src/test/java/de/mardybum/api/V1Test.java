package de.mardybum.api;

import de.mardybum.api.instancehelper.BCInstanceBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class V1Test extends JerseyTest {

    private static final String PATH = "/api/v1";
    private static final String MINE = "mine";
    private static final String CREATE_TRANSACTION = "create_transaction";

    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig(V1.class).register(new BCInstanceBinder());
    }

    @org.junit.Test
    public void mine() {
        Response response = target(PATH)
                .path("/"+MINE)
                .request()
                .get();

        assertEquals(200, response.getStatus());
    }

    @org.junit.Test
    public void create_Transaction() {
        Response response = target(PATH)
                .path("/"+CREATE_TRANSACTION)
                .request()
                .post(Entity.entity("{\n" +
                        "    \"sender\": \"Siggi\",\n" +
                        "    \"recipient\": \"Lukas\",\n" +
                        "    \"amount\": \"20\"\n" +
                        "}", MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus());
    }
}
