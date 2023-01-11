package muffincat.CleanerBot;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import muffincat.CleanerBot.Commands.Clear;
import muffincat.CleanerBot.Commands.CommandManager;
import muffincat.CleanerBot.Commands.Info;
import muffincat.CleanerBot.Commands.Stopbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App{
	public static User ME;
	
	public static void main(String[] args) throws LoginException{
        	JDABuilder _builder = JDABuilder.createDefault(System.getenv("T"));
        	_builder.setActivity(Activity.playing("Cleaning Simulator"));
        
        	_builder.setStatus(OnlineStatus.ONLINE);
        	_builder.enableIntents(EnumSet.allOf(GatewayIntent.class));
        	_builder.addEventListeners(new CommandManager(), new Info(), new Clear(), new Stopbot()); 
        
        	JDA _jda = _builder.build();
       		ME = _jda.retrieveUserById(575921629465673730L).complete();
	}
}
