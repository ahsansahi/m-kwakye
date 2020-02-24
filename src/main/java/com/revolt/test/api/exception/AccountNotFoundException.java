package com.revolt.test.api.exception;

public class AccountNotFoundException extends Exception {
    private Long accountnumber;

    public AccountNotFoundException(String message, Throwable cause, Long accountnumber) {
        super(message, cause);
        this.accountnumber = accountnumber;
    }

    public Long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
    }
}
