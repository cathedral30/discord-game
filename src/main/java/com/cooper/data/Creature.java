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
    int mhp; // Max Health points
    float hp; // Health Points
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

        this.mhp = random.nextInt(100);
        this.hardness = random.nextInt(10);
        this.dodge = random.nextInt(10);
        this.armour = random.nextInt(10);
        this.sAttack = random.nextInt(10);
        this.hAttack = random.nextInt(10);
        this.piercing = random.nextInt(10);

        for (BodyPart bodyPart : this.bodyParts) {
            this.mhp *= bodyPart.hpm;
            this.hardness *= bodyPart.hm;
            this.dodge *= bodyPart.dm;
            this.armour *= bodyPart.am;
            this.sAttack *= bodyPart.sam;
            this.hAttack *= bodyPart.ham;
            this.piercing *= bodyPart.pm;
        }
        this.hp = this.mhp;
    }

    public Long attack(Creature creature) {
        Random random = new Random();
        while (this.hp > 0 && creature.getHp() > 0) {
            float a_h_proportion = (float) (this.hardness / this.hp);
            float b_h_proportion = (float) (creature.getHardness() / creature.getHp());

            if (random.nextInt(100) > creature.getDodge()) {
                if (this.piercing > creature.getArmour()) {
                    System.out.println("Creature A can pierce B armour");
                    creature.setHp(creature.getHp() - (this.sAttack * (1 - b_h_proportion)));
                    creature.setHp(creature.getHp() - (this.hAttack * b_h_proportion));
                } else {
                    if (random.nextInt(9) > 4) {
                        creature.setHp(creature.getHp() - (this.sAttack * (1 - b_h_proportion)));
                        creature.setHp(creature.getHp() - (this.hAttack * b_h_proportion));
                    } else {
                        System.out.println("Creature A failed to pierce B armour");
                    }
                }
            } else {
                System.out.println("Creature B dodged creature A attack");
            }

            System.out.println(String.format("Creature B hp: %s", creature.getHp()));

            if (random.nextInt(100) > this.dodge) {
                if (creature.getPiercing() > this.armour) {
                    System.out.println("Creature B can pierce A armour");
                    this.setHp(this.hp - (creature.getSAttack() * (1 - a_h_proportion)));
                    this.setHp(this.hp - (creature.getHAttack() * a_h_proportion));
                } else {
                    if (random.nextInt(9) > 4) {
                        this.setHp(this.hp - (creature.getSAttack() * (1 - a_h_proportion)));
                        this.setHp(this.hp - (creature.getHAttack() * a_h_proportion));;
                    } else {
                        System.out.println("Creature B failed to pierce A armour");
                    }
                }
            } else {
                System.out.println("Creature A dodged creature B attack");
            }
            System.out.println(String.format("Creature A hp: %s", this.hp));
        }
        return 1L;
    }
}
