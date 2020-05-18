package com.cooper.data;

import com.cooper.repository.Database;
import lombok.Data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Creature {
    Long id;
    String name;
    int lifespan;
    List<BodyPart> bodyParts;
    int hp; // Health points
    int hardness;
    int dodge;
    int armour;
    int sAttack; // Soft Attack
    int hAttack; // Hard Attack
    int piercing;

    public Creature(Database database) {
        try {
            this.name = database.generateName();
        } catch (SQLException e) {
            e.printStackTrace();
            this.name = "blah";
        }

        Random random = new Random();
        List<BodyPart> parts = new ArrayList<>();
        try {
            parts.add(database.getBodyPart("H"));
            parts.add(database.getBodyPart("T"));
            for (int i = 0; i < random.nextInt(5); i++) {
                parts.add(database.getBodyPart("A"));
            }
            for (int i = 0; i < random.nextInt(5); i++) {
                parts.add(database.getBodyPart("L"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.bodyParts = parts;

        this.hp = random.nextInt(10);
        this.hardness = random.nextInt(10);
        this.dodge = random.nextInt(10);
        this.armour = random.nextInt(10);
        this.sAttack = random.nextInt(10);
        this.hAttack = random.nextInt(10);
        this.piercing = random.nextInt(10);

        for (BodyPart bodyPart : this.bodyParts) {
            this.hp *= bodyPart.hpm;
            this.hardness *= bodyPart.hm;
            this.dodge *= bodyPart.dm;
            this.armour *= bodyPart.am;
            this.sAttack *= bodyPart.sam;
            this.hAttack *= bodyPart.ham;
            this.piercing *= bodyPart.pm;
        }
    }
}
