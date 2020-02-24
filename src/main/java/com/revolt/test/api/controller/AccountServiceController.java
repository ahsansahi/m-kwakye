package com.revolt.test.api.controller;

import com.revolt.test.api.exception.AccountNotFoundException;
import com.revolt.test.api.model.Account;
import com.revolt.test.api.service.AccountService;
import com.revolt.test.api.service.BalanceTransferService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/api/account")
public class AccountServiceController {
    private static final String[] responseString = {"OK", "SYSTEM ERROR", "INSUFFICIENT BALANCE",
            "INVALID AMOUNT", "INVALID SOURCE ACCOUNT", "INVALID DESTINATION ACCOUNT"};
    @Inject
    AccountService accountService;// = new AccountServiceImpl();
    @Inject
    BalanceTransferService balanceTransferService;// = new BalanceTransferServiceImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        try {
            accountService.createAccount(account);
            return Response.status(201).build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(500).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAccount(@PathParam("id") Long id, Account account) {
        try {
            account.setId(id);
            accountService.updateAccount(account);
            return Response.status(200).build();
        } catch (SQLException e) {
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("id") Long id) {
        try {
            Account account = accountService.getAccount(id);
            return Response.status(201).entity(account).build();
        } catch (SQLException e) {
            return Response.status(500).build();
        } catch (AccountNotFoundException e) {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/transfer/{source}/{destination}/{amount}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processTransfer(@PathParam("source") Long sourceId, @PathParam("destination") Long destinationId, @PathParam("amount") float amount) {
        try {
            int result = balanceTransferService.transfer(sourceId, destinationId, amount);
            return Response.status(result == 0 ? 200 : (result == 3 ? 500 : 400)).entity(responseString[result]).build();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        return Response.status(200).build();

    }
}
