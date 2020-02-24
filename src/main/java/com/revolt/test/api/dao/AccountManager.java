package com.revolt.test.api.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.revolt.test.api.model.Account;

import java.io.IOException;
import java.sql.SQLException;


public class AccountManager {

    // we are using the in-memory H2 database
    private final static String DATABASE_URL = "jdbc:h2:mem:testdb";
    private static ConnectionSource connectionSource = null;

    static {

        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
            System.out.println("\n\nDatabase connect setup Completed ! \n\n");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Setup our database and DAOs
     */
    public static Dao<Account, Long> setUpAccountDao(Class accountClass, Class keyType) throws Exception {

        Dao<Account, Long> dao = DaoManager.createDao(connectionSource, accountClass);
        TableUtils.createTableIfNotExists(connectionSource, accountClass);
        return dao;
    }
}