package com.cooper.data;

import lombok.Data;

import java.util.List;

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
}
