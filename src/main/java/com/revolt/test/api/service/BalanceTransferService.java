package com.revolt.test.api.service;

import com.revolt.test.api.exception.AccountNotFoundException;
import org.glassfish.jersey.spi.Contract;

import java.sql.SQLException;

@Contract
public interface BalanceTransferService {

    int transfer(Long sourceAccountId, Long destinationAccountId, float amount) throws SQLException, AccountNotFoundException;
}
