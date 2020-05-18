package com.cooper.data;

import lombok.Data;

@Data
public class BodyPart {
    Long id;
    String name;
    int hpm; // Health points multiplier
    int hm; // Hardness multiplier
    int dm; // Dodge multiplier
    int am; // Armour multiplier
    int sam; // Soft attack multiplier
    int ham; // Hard attack multiplier
    int pm; // Pierce multiplier
}
