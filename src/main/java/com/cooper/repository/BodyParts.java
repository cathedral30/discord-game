package com.cooper.repository;

import com.cooper.data.BodyPart;
import lombok.AllArgsConstructor;

import java.sql.*;

@AllArgsConstructor
public class BodyParts {
    Connection conn;

    public BodyPart getBodyPart(String type) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bodyparts  WHERE `type` = ? ORDER BY RAND() LIMIT 1;");
        stmt.setString(1, type);
        ResultSet rs = stmt.executeQuery();

        BodyPart bodyPart = null;

        while(rs.next()){
            bodyPart = new BodyPart(rs);
        }
        rs.close();
        stmt.close();
        if (bodyPart != null) {
            return bodyPart;
        } else {
            throw new SQLDataException();
        }
    }
}
