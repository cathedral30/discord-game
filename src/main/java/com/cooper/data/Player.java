package com.cooper.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class Player {
    Long id;
    String username;
    Long bucks;

    public Player(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.username = rs.getString("username");
        this.bucks = rs.getLong("bucks");
    }
}
