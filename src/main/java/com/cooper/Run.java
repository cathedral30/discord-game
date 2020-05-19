package com.cooper;

import com.cooper.data.Creature;
import com.cooper.repository.Database;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run extends ListenerAdapter
{
    Database database;
    Logger logger = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        Run main = new Run();
        try {
        main.loadDatabase();
        } catch(SQLException e) {
            main.logger.error("Error connecting to database", e);
        }

        File file = main.getFileFromResources("bot_token");
        Scanner myReader = new Scanner(file);
        String token = myReader.next();
        myReader.close();

        try
        {
            JDA jda = new JDABuilder(token)         // The token of the account that is logging in.
                    .addEventListeners(main)  // An instance of a class that will handle events.
                    .build();
            jda.awaitReady(); // Blocking guarantees that JDA will be completely loaded.
            System.out.println("Finished Building JDA!");
        }
        catch (LoginException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        //These are provided with every event in JDA
        JDA jda = event.getJDA();                       //JDA, the core of the api.
        long responseNumber = event.getResponseNumber();//The amount of discord events that JDA has received since the last reconnect.

        //Event specific information
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        MessageChannel channel = event.getChannel();    //This is the MessageChannel that the message was sent to.
        //  This could be a TextChannel, PrivateChannel, or Group!

        String msg = message.getContentDisplay();              //This returns a human readable version of the Message. Similar to
        // what you would see in the client.

        boolean bot = author.isBot();                    //This boolean is useful to determine if the User that
        // sent the Message is a BOT or not!

        if (event.isFromType(ChannelType.TEXT))         //If this message was sent to a Guild TextChannel
        {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();

            if (msg.startsWith("!bear ")) {
                String command = msg.split("!bear ")[1];

                if (command.equals("register")) {
                    try {
                        this.database.CreatePlayer(author.getName());

                        channel.sendMessage(
                                String.format("You been registered @%s!", author.getName())
                        ).queue();

                    } catch(SQLException e) {
                        this.logger.error("Error creating player", e);
                        channel.sendMessage(
                                String.format("I'm sorry @%s I was unable to register you :(", author.getName())
                        ).queue();
                    }
                }

                if (command.equals("generate")) {
                    Creature a = new Creature(this.database);
                    a.attack(new Creature(this.database));
                }

                String name;
                if (message.isWebhookMessage())
                {
                    name = author.getName();                //If this is a Webhook message, then there is no Member associated
                }                                           // with the User, thus we default to the author for name.
                else
                {
                    name = member.getEffectiveName();       //This will either use the Member's nickname if they have one,
                }                                           // otherwise it will default to their username. (User#getName())

                System.out.printf("(%s)[%s]<%s>: %s\n", guild.getName(), textChannel.getName(), name, msg);
            }
        }
        else if (event.isFromType(ChannelType.PRIVATE)) //If this message was sent to a PrivateChannel
        {
            //The message was sent in a PrivateChannel.
            //In this example we don't directly use the privateChannel, however, be sure, there are uses for it!
            PrivateChannel privateChannel = event.getPrivateChannel();

            System.out.printf("[PRIV]<%s>: %s\n", author.getName(), msg);
        }

        //Remember, in all of these .equals checks it is actually comparing
        // message.getContentDisplay().equals, which is comparing a string to a string.
        // If you did message.equals() it will fail because you would be comparing a Message to a String!
        if (msg.equals("!ping"))
        {
            //This will send a message, "pong!", by constructing a RestAction and "queueing" the action with the Requester.
            // By calling queue(), we send the Request to the Requester which will send it to discord. Using queue() or any
            // of its different forms will handle ratelimiting for you automatically!

            channel.sendMessage("pong!").queue();
        }
        else if (msg.equals("!roll"))
        {
            //In this case, we have an example showing how to use the flatMap operator for a RestAction. The operator
            // will provide you with the object that results after you execute your RestAction. As a note, not all RestActions
            // have object returns and will instead have Void returns. You can still use the flatMap operator to run chain another RestAction!

            Random rand = ThreadLocalRandom.current();
            int roll = rand.nextInt(6) + 1; //This results in 1 - 6 (instead of 0 - 5)
            channel.sendMessage("Your roll: " + roll)
                    .flatMap(
                            (v) -> roll < 3, // This is called a lambda expression. If you don't know what they are or how they work, try google!
                            // Send another message if the roll was bad (less than 3)
                            sentMessage -> channel.sendMessage("The roll for messageId: " + sentMessage.getId() + " wasn't very good... Must be bad luck!\n")
                    )
                    .queue();
        }
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

    private void loadDatabase() throws SQLException, ClassNotFoundException {
        this.database = new Database();
    }
}
