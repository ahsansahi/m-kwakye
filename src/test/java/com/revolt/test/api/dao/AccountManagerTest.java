package com.revolt.test.api.dao;

import com.j256.ormlite.dao.Dao;
import com.revolt.test.api.model.Account;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AccountManagerTest {

    @Test
    public void setupDatabase() throws Exception {
        AccountManager accountManager = new AccountManager();
        Dao accountDao = accountManager.setUpAccountDao(Account.class, Long.class);
        assertNotNull("Dao must be created",accountDao);

    }

    @Test(expected = Exception.class)
    public void setupDatabaseException() throws Exception {
        AccountManager accountManager = new AccountManager();
        Dao accountDao = accountManager.setUpAccountDao(AccountManagerTest.class, Long.class);

    }
}