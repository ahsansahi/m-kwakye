package com.revolt.test.api;

import com.revolt.test.api.controller.AccountServiceController;
import com.revolt.test.api.service.AccountServiceImpl;
import com.revolt.test.api.service.BalanceTransferServiceImpl;

import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestApplication extends Application {
    private final Set<Class<?>> classes;
    private final Set<Object> singletons;
    public TestApplication() {
        HashSet<Class<?>> c = new HashSet<Class<?>>();
        c.add(AccountServiceController.class);
        classes = Collections.unmodifiableSet(c);

        HashSet<Object> s = new HashSet<Object>();
        s.add(new AccountServiceImpl());
        s.add(new BalanceTransferServiceImpl());
        singletons = Collections.unmodifiableSet(s);
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
