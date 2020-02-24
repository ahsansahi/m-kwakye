package com.revolt.test.api.service;

import com.revolt.test.api.exception.AccountNotFoundException;
import com.revolt.test.api.model.Account;
import org.glassfish.jersey.spi.Contract;

import java.sql.SQLException;
@Contract
public interface AccountService {

    Account getAccount(Long accountNumber) throws SQLException, AccountNotFoundException;

    void createAccount(Account basicAccount) throws SQLException;

    void updateAccount(Account sourceAccount) throws SQLException;
}
