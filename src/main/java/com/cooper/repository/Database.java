package com.cooper.repository;

import java.sql.*;

import com.cooper.data.BodyPart;
import com.cooper.data.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
    static final String DB_URL = "jdbc:mysql://localhost/discord";

    static final String USER = "root";
    static final String PASS = "comsc";
    Logger logger = LoggerFactory.getLogger(Database.class);
    Connection conn;
    Players players;
    Names names;
    BodyParts bodyParts;

    public Database() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        logger.debug("Connecting to database...");
        this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
        this.players = new Players(conn);
        this.names = new Names(conn);
        this.bodyParts = new BodyParts(conn);
    }

    public Player GetPlayerByUsername(String username) throws SQLException {
        return this.players.getPlayerByUsername(username);
    }

    public void CreatePlayer(String username) throws SQLException {
        this.players.createPlayer(username);
    }

    public String generateName() throws SQLException {
        return this.names.generateName();
    }

    public BodyPart getBodyPart(String type) throws SQLException {
        return this.bodyParts.getBodyPart(type);
    }
}
