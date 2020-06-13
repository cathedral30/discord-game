package com.cooper.repository;

import java.sql.*;
import java.util.List;

import com.cooper.data.BodyPart;
import com.cooper.data.Creature;
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
    Creatures creatures;
    private static Database _database;

    private Database() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        logger.debug("Connecting to database...");
        this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
        this.players = new Players(conn);
        this.names = new Names(conn);
        this.bodyParts = new BodyParts(conn);
        this.creatures = new Creatures(conn);
    }

    public static Database getSharedDatabase() throws SQLException, ClassNotFoundException {
        if (_database == null) {
            _database = new Database();
        }
        return _database;
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

    public void createCreature(Player player, Creature creature) throws SQLException {
        this.creatures.createCreature(player, creature);
    }

    public List<Creature> getCreaturesByPlayerId(Long id) throws SQLException {
        return this.creatures.getCreaturesForPlayerId(id);
    }

    public Creature getCreatureById(Long id) throws SQLException {
        return this.creatures.getCreatureById(id);
    }
}
