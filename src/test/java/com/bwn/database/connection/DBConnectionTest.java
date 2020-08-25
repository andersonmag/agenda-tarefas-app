package com.bwn.database.connection;

import org.junit.jupiter.api.Test;

public class DBConnectionTest {

    @Test
    public void connectDatabase() {
        DBConnection.connect();
    }

    @Test
    public void disconnect() {
        DBConnection.disconnect();
    }

}