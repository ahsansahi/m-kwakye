package com.revolt.test.api.service;

import com.revolt.test.api.exception.AccountNotFoundException;
import com.revolt.test.api.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class BalanceTransferServiceImplTest {

    BalanceTransferService balanceTransferService=new BalanceTransferServiceImpl();
    AccountService accountService=new AccountServiceImpl();
    Account destAccount;
    Account sourceAccount;
    @Before
    public void setUp(){

        sourceAccount=new Account();
        sourceAccount.setBalance(100.00);
        sourceAccount.setTitle("source");
        sourceAccount.setAccountName("source account");

        destAccount=new Account();
        destAccount.setBalance(100.00);
        destAccount.setTitle("source");
        destAccount.setAccountName("source account");
        try {
            accountService.createAccount(sourceAccount);
            accountService.createAccount(destAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void transfer() throws SQLException, AccountNotFoundException {

        sourceAccount.setBalance(100.00);
        destAccount.setBalance(100.00);
        accountService.updateAccount(sourceAccount);
        accountService.updateAccount(destAccount);
        int result = balanceTransferService.transfer(sourceAccount.getId(),destAccount.getId(),50F);

        assertTrue("successful transfer",0==result);
        sourceAccount=accountService.getAccount(sourceAccount.getId());
        assertTrue("New Balance for Source Account",50D==sourceAccount.getBalance());
        destAccount=accountService.getAccount(destAccount.getId());
        assertTrue("New Balance for Destination Account",150D==destAccount.getBalance());
    }

    @Test
    public void invalidIds() throws SQLException, AccountNotFoundException {

        int result = balanceTransferService.transfer(15L,destAccount.getId(),50F);
        assertTrue("Invalid Source Account ",4==result);
        result = balanceTransferService.transfer(sourceAccount.getId(),15L,50F);
        assertTrue("Invalid Destination Account ",5==result);
    }
    @Test
    public void insufficientFunds() throws SQLException, AccountNotFoundException {
        sourceAccount.setBalance(100.00);
        destAccount.setBalance(100.00);
        accountService.updateAccount(sourceAccount);
        accountService.updateAccount(destAccount);
        int result=balanceTransferService.transfer(sourceAccount.getId(),destAccount.getId(),1000f);
        assertTrue("balance in source account is not enough",2==result);
    }

    @Test
    public void invalidTransferAmount() throws SQLException, AccountNotFoundException {
        int result=balanceTransferService.transfer(sourceAccount.getId(),destAccount.getId(),-2.50f);
        assertTrue("balance in source account is not enough",3==result);
    }

}