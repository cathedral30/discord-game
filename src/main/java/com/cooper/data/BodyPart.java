package com.cooper.data;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class BodyPart {
    Long id;
    String name;
    String type; // L (leg), A (arm), T (torso), H (head)
    int hpm; // Health points multiplier
    int hm; // Hardness multiplier
    int dm; // Dodge multiplier
    int am; // Armour multiplier
    int sam; // Soft attack multiplier
    int ham; // Hard attack multiplier
    int pm; // Pierce multiplier

    public BodyPart(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.type = rs.getString("type");
        this.hpm = rs.getInt("hpm");
        this.hm = rs.getInt("hm");
        this.dm = rs.getInt("dm");
        this.am = rs.getInt("am");
        this.sam = rs.getInt("sam");
        this.ham = rs.getInt("ham");
        this.pm = rs.getInt("pm");
    }
}
