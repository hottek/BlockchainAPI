package de.mardybum.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import de.mardybum.blockchain.Block;
import de.mardybum.blockchain.Transaction;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/api/v1")
public class V1 {

    @Inject
    private BCInstance bcInstance;

    @GET
    @Path("/mine")
    @Produces(MediaType.TEXT_PLAIN)
    public String Mine() {
        ArrayList<Transaction> transactions = bcInstance.bc.getCurrent_transaction();
        int proof_of_new_block = bcInstance.bc.proof_of_work(bcInstance.bc.last_block().getProof());
        int previous_hash = bcInstance.bc.hash(bcInstance.bc.last_block());
        Block block = bcInstance.bc.new_block(proof_of_new_block, transactions, previous_hash);
        return String.valueOf(block.getIndex());
    }

    @GET
    @Path("/chain")
    @Produces(MediaType.TEXT_PLAIN)
    public String print_Chain() {
        return bcInstance.bc.toString();
    }

    @POST
    @Path("/create_transaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String Create_Transaction(String input) {
        try {
            JsonObject transaction;
            transaction = new Gson().fromJson(input, JsonObject.class);
            String sender = transaction.get("sender").getAsString();
            String recipient = transaction.get("recipient").getAsString();
            int amount = transaction.get("amount").getAsInt();
            bcInstance.bc.new_transaction(sender,recipient,amount);
            return String.valueOf(true);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return String.valueOf(false);
        }
    }

}
