package com.revolt.test.api.service;

import com.j256.ormlite.dao.Dao;
import com.revolt.test.api.dao.AccountManager;
import com.revolt.test.api.exception.AccountNotFoundException;
import com.revolt.test.api.model.Account;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;
import java.sql.SQLException;

@Service
@Singleton
public class BalanceTransferServiceImpl implements BalanceTransferService {

    Dao<Account, Long> accountDao = null;

    public BalanceTransferServiceImpl() {
        try {
            accountDao = AccountManager.setUpAccountDao(Account.class, Long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int transfer(Long sourceAccountId, Long destinationAccountId, float amount) throws SQLException, AccountNotFoundException {

        int result = 0;
        Account sourceAccount = accountDao.queryForId(sourceAccountId);
        if (null == sourceAccount) return 4;
        Account destinationAccount = accountDao.queryForId(destinationAccountId);
        if (null == destinationAccount) return 5;

        if (amount > 0) {
            try {
                result = completeTransfer(sourceAccount, destinationAccount, amount);
            } catch (SQLException sql) {
                result = 1;
            }
        } else {
            result = 3;
        }
        return result;
    }

    private int completeTransfer(Account sourceAccount, Account destinationAccount, float amount) throws SQLException {

        synchronized (sourceAccount.getBalance()) {
            if (amount > sourceAccount.getBalance()) {
                return 2;
            }
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);
            accountDao.update(sourceAccount);
        }
        accountDao.update(destinationAccount);
        return 0;
    }
}
