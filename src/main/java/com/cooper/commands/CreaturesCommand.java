package com.cooper.commands;

import com.cooper.data.Creature;
import com.cooper.repository.Database;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.sql.SQLException;
import java.util.List;

public class CreaturesCommand extends Command {

    public CreaturesCommand()
    {
        this.name = "creatures";
        this.help = "lists all creatures owned by the player";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event)
    {
        try {
            Database database = Database.getSharedDatabase();
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Creatures owned by "+ event.getAuthor().getName() +"!", null, event.getAuthor().getAvatarUrl());
            List<Creature> creatures = database.getCreaturesByPlayerId(database.GetPlayerByUsername(event.getAuthor().getName()).getId());

            StringBuilder nums = new StringBuilder();
            StringBuilder names = new StringBuilder();
            StringBuilder mhps = new StringBuilder();
            StringBuilder hps = new StringBuilder();

            for (int i = 0; i < creatures.size(); i++) {
                names.append(i + 1).append(". ").append(creatures.get(i).getName()).append("\n");
                mhps.append(creatures.get(i).getMhp()).append("\n");
                hps.append(creatures.get(i).getHp()).append("\n");
            }

            builder.addField("Name", names.toString(), true);

            builder.addField("Max HP", mhps.toString(), true);

            builder.addField("HP", hps.toString(), true);

            builder.setDescription("Type the number of a creature to see more info.");

            event.reply(builder.build());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
