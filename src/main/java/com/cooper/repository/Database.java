package com.cooper.repository;

import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
    static final String DB_URL = "jdbc:mysql://localhost/discord";

    static final String USER = "root";
    static final String PASS = "comsc";
    Logger logger = LoggerFactory.getLogger(Database.class);
    Connection conn;

    public Database() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        logger.debug("Connecting to database...");
        this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }
}