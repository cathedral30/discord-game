package com.cooper.repository;

import com.cooper.data.BodyPart;
import com.cooper.data.Creature;
import com.cooper.data.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Creatures {
    Connection conn;

    public Creatures(Connection conn) {
        this.conn = conn;
    }

    public void createCreature(Player player, Creature creature) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO creatures(`player_id`, `name`, `max_hp`, `hp`, `hardness`, `dodge`, `armour`, `sAttack`, `hAttack`, `piercing`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, player.getId());
        stmt.setString(2, creature.getName());
        stmt.setInt(3, creature.getMhp());
        stmt.setFloat(4, creature.getHp());
        stmt.setInt(5, creature.getHardness());
        stmt.setInt(6, creature.getDodge());
        stmt.setInt(7, creature.getArmour());
        stmt.setInt(8, creature.getSAttack());
        stmt.setInt(9, creature.getHAttack());
        stmt.setInt(10, creature.getPiercing());

        int affectedRows = stmt.executeUpdate();

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                Long creature_id = generatedKeys.getLong(1);
                for (BodyPart bodyPart : creature.getBodyParts()){
                    PreparedStatement statement = conn.prepareStatement("INSERT INTO creatureparts(`creature_id`, `part_id`) VALUES (?, ?);");
                    statement.setLong(1, creature_id);
                    statement.setLong(2, bodyPart.getId());
                    statement.execute();
                    statement.close();
                }
            }
            else {
                throw new SQLException("Creating creature failed, no ID obtained.");
            }
        }
        stmt.close();
    }

    public List<Creature> getCreaturesForPlayerId(Long id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM creatures WHERE `player_id` = ?");
        stmt.setLong(1, id);

        ResultSet resultSet = stmt.executeQuery();
        List<Creature> creatures = new ArrayList<>();

        while (resultSet.next()) {
            long creature_id = resultSet.getLong("id");
            creatures.add(new Creature(creature_id, conn));
        }
        return creatures;
    }

    public Creature getCreatureById(Long id) throws SQLException {
        return new Creature(id, conn);
    }
}
