package com.cooper;

import com.cooper.commands.*;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.AboutCommand;
import com.jagrosh.jdautilities.examples.command.PingCommand;
import com.jagrosh.jdautilities.examples.command.ShutdownCommand;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run extends ListenerAdapter
{
    Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, LoginException, SQLException {
        Run main = new Run();

        File file = main.getFileFromResources("bot_token");
        Scanner myReader = new Scanner(file);
        String token = myReader.next();
        myReader.close();

        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();
        client.useDefaultGame();
        client.setOwnerId("223819123241648141");

        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

        client.setPrefix("!bear ");

        client.addCommands(
                // command to show information about the bot
                new AboutCommand(Color.BLUE, "an example bot",
                        new String[]{"Cool commands","Nice examples","Lots of fun!"},
                        new Permission[]{Permission.ADMINISTRATOR}),

                new HelloCommand(waiter),
                new GuildlistCommand(waiter),
                new GenerateCommand(),
                new CreaturesCommand(waiter),

                // command to check bot latency
                new PingCommand(),

                // command to shut off the bot
                new ShutdownCommand());

        new JDABuilder(AccountType.BOT)
                // set the token
                .setToken(token)

                // set the game for when the bot is loading
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("loading..."))

                // add the listeners
                .addEventListeners(waiter, client.build())

                // start it up!
                .build();
    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
