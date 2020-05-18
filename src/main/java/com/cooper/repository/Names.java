package com.cooper.repository;

import lombok.AllArgsConstructor;

import java.sql.*;

@AllArgsConstructor
public class Names {
    Connection conn;

    public String generateName() throws SQLException {
        String name = "";
        String[] table_names = {"startnames", "midnames", "endnames"};
        for (String i : table_names) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "+ i +" ORDER BY RAND() LIMIT 1;");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                name += rs.getString("name");
            }
            rs.close();
            stmt.close();
        }

        if (name.length() > 1) {
            return name;
        } else {
            throw new SQLDataException();
        }
    }
}
