package com.cooper.commands;

import com.cooper.data.Creature;
import com.cooper.repository.Database;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreaturesCommand extends Command {

    private final EventWaiter waiter;

    public CreaturesCommand(EventWaiter waiter)
    {
        this.waiter = waiter;
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

            EmbedBuilder eBuilder = new EmbedBuilder();

            waiter.waitForEvent(MessageReceivedEvent.class,
                    // make sure it's by the same user, and in the same channel, and for safety, a different message
                    e -> e.getAuthor().equals(event.getAuthor())
                            && e.getChannel().equals(event.getChannel())
                            && !e.getMessage().equals(event.getMessage()),
                    // respond, inserting the name they listed into the response
                    e -> {
                        eBuilder.setAuthor(creatures.get(Integer.parseInt(e.getMessage().getContentRaw()) - 1).getName());
                        event.reply(eBuilder.build());
                    },
                    // if the user takes more than a minute, time out
                    1, TimeUnit.MINUTES, () -> event.reply("Sorry, you took too long."));

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
