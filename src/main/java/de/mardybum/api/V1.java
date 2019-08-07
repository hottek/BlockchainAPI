package de.mardybum.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.mardybum.blockchain.Block;
import de.mardybum.blockchain.Transaction;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/api/v1")
public class V1 {

    @Inject
    private BCInstance bc;

    @GET
    @Path("/mine")
    @Produces(MediaType.TEXT_PLAIN)
    public String Mine() {
        ArrayList<Transaction> transactions = bc.bc.getCurrent_transaction();
        Block block = bc.bc.new_block(bc.bc.proof_of_work(bc.bc.last_block().getProof()), transactions, bc.bc.hash(bc.bc.last_block()));
        System.out.println(bc.bc.toString());
        return String.valueOf(block.getIndex());
    }

    @POST
    @Path("/create_transaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String Create_Transaction(String input) {
        JsonObject transaction = null;
        transaction = new Gson().fromJson(input, JsonObject.class);
        bc.bc.new_transaction(transaction.get("sender").getAsString(),transaction.get("recipient").getAsString(),transaction.get("amount").getAsInt());
        return "Success";
    }

}
