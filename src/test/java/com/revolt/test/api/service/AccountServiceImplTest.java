package com.revolt.test.api.service;

import com.revolt.test.api.exception.AccountNotFoundException;
import com.revolt.test.api.model.Account;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AccountServiceImplTest {

    AccountService accountService=new AccountServiceImpl();

    @Test
    public void getAccount() {

        createAccount();
        Account account=null;
        try {
            account=accountService.getAccount(1L);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }

        assertNotNull(account);
        assertTrue("Id must be same ", account.getId()==1L);

    }
    @Test
    public void updateAccount() {

        createAccount();
        Account account=null;
        try {
            account=accountService.getAccount(1L);
            account.setBalance(1000.00);
            accountService.updateAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }

        assertNotNull(account);
        assertTrue("must update balance to new value ", account.getBalance()==1000.00);

    }

    @Test(expected = AccountNotFoundException.class)
    public void getAccountHaveException() throws SQLException, AccountNotFoundException {
        createAccount();
        Account account=null;
            account=accountService.getAccount(999999999L);

    }
    @Test
    public void createAccount() {
        Account account=new Account();
        account.setAccountName("source account");
        account.setTitle("Test Account");
        account.setBalance(0.0D);
        account.setId(0L);

        try {
            accountService.createAccount(account);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue("Account Must have an ID",account.getId()>0);
    }
}