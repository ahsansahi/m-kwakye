package com.revolt.test.api.service;

import com.j256.ormlite.dao.Dao;
import com.revolt.test.api.dao.AccountManager;
import com.revolt.test.api.exception.AccountNotFoundException;
import com.revolt.test.api.model.Account;
import org.jvnet.hk2.annotations.Service;


import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.sql.SQLException;

@Service
@Singleton
public class AccountServiceImpl implements AccountService {

    Dao<Account, Long> accountDao = null;

    public AccountServiceImpl() {
        try {
            accountDao = AccountManager.setUpAccountDao(Account.class, Long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccount(Long accountNumber) throws AccountNotFoundException {
        Account account = null;
        try {
            account = accountDao.queryForId(accountNumber);
        } catch (SQLException e) {
            throw new AccountNotFoundException("Account Not Found !", e, accountNumber);
        }
        if (null == account) throw new AccountNotFoundException("Account Not Found !", null, accountNumber);

        return account;
    }

    @Override
    public void createAccount(Account basicAccount) throws SQLException {
        int number = accountDao.create(basicAccount);
    }

    @Override
    public void updateAccount(Account sourceAccount) throws SQLException {
        int number = accountDao.update(sourceAccount);
    }

    @PostConstruct
    public void post(){
        System.out.println("Service Object created");
    }
}
