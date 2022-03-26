package muffincat.CleanerBot;

import javax.security.auth.login.LoginException;

import muffincat.CleanerBot.Commands.Clear;
import muffincat.CleanerBot.Commands.Info;
import muffincat.CleanerBot.Commands.Stopbot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class App{
	public static String prefex = "~";
	
	public static void main(String[] args) throws LoginException{
        JDABuilder builder = JDABuilder.createDefault(System.getenv("TOKEN"));
        builder.setActivity(Activity.playing("Cleaning Simulator"));
        builder.setStatus(OnlineStatus.IDLE);
        
        builder.addEventListeners(new Info(), new Stopbot(), new Clear());
        builder.build();
	}
}
