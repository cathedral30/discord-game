package com.cooper.commands;

import com.cooper.data.Creature;
import com.cooper.data.Player;
import com.cooper.repository.Database;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.sql.SQLException;

public class GenerateCommand extends Command {

    public GenerateCommand()
    {
        this.name = "generate";
        this.help = "generates a creature for the user";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event)
    {
        try {
            Database database = Database.getSharedDatabase();
            database.createCreature(database.GetPlayerByUsername(event.getAuthor().getName()), new Creature(database));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
