package com.cooper.repository;

import com.cooper.data.Player;
import lombok.AllArgsConstructor;

import java.sql.*;

@AllArgsConstructor
public class Players {
    Connection conn;

    public Player getPlayerByUsername(String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM players WHERE `username` = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        Player player = null;

        while(rs.next()){
            player = new Player(rs);
        }
        rs.close();
        stmt.close();
        if (player != null) {
            return player;
        } else {
            throw new SQLDataException();
        }
    }

    public void createPlayer(String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO players(`username`) VALUES(?)");
        stmt.setString(1, username);
        stmt.execute();
        stmt.close();
    }
}
