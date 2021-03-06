package de.mardybum.api;

import de.mardybum.api.instancehelper.BCInstanceBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class V1Test extends JerseyTest {

    private static final String PATH = "/api/v1";
    private static final String MINE = "mine";
    private static final String CREATE_TRANSACTION = "create_transaction";
    private static final String PRINT_CHAIN = "chain";

    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig(V1.class).register(new BCInstanceBinder());
    }

    @Test
    public void mine() {
        Response response = target(PATH)
                .path("/"+MINE)
                .request()
                .get();

        // TODO better unit test
        assertEquals(200, response.getStatus());
    }

    @Test
    public void create_Transaction() {
        Response response = target(PATH)
                .path("/"+CREATE_TRANSACTION)
                .request()
                .post(Entity.entity("{\n" +
                        "    \"sender\": \"Siggi\",\n" +
                        "    \"recipient\": \"Lukas\",\n" +
                        "    \"amount\": \"20\"\n" +
                        "}", MediaType.APPLICATION_JSON));

        // Pseudo-boolean compare
        assertEquals("true", response.readEntity(String.class));
    }

    @Test
    public void print_Chain() {
        Response response = target(PATH)
                .path("/"+PRINT_CHAIN)
                .request()
                .get();
        String responseString = response.readEntity(String.class);

        assertEquals("=\n", responseString.substring(responseString.length() - 2));
    }
}
