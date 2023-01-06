package muffincat.CleanerBot;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import muffincat.CleanerBot.Commands.Clear;
import muffincat.CleanerBot.Commands.Info;
import net.dv8tion.jda.api.JDA;
//import muffincat.CleanerBot.Commands.Stopbot; stopbot for tests only; if u want 
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App{
	public static final String PREFEX = "~";
	public static User ME;
	
	public static void main(String[] args) throws LoginException{
        JDABuilder _builder = JDABuilder.createDefault("xxxxxxxxxxxxxxxxxxxx");
        _builder.setActivity(Activity.playing("Cleaning Simulator"));
        
        _builder.setStatus(OnlineStatus.ONLINE);
        _builder.enableIntents(EnumSet.allOf(GatewayIntent.class));
        _builder.addEventListeners(new Info(), new Clear()); 
        
        JDA _jda = _builder.build();
        ME = _jda.retrieveUserById(your_id).complete(); //just put your discord id, and dont forget that your id is a long, not int
	}
}
