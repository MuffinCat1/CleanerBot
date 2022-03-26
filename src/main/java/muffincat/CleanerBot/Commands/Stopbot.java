package muffincat.CleanerBot.Commands;

import muffincat.CleanerBot.App;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;

public class Stopbot extends ListenerAdapter{
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)  {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(App.prefex + "stopbot")) {
			Member member = event.getMember();
			if(member != null) {
				if(member.getPermissions().contains(Permission.ADMINISTRATOR)) {
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage("Im going to sleep").queue();
					System.exit(0);
				} if(!member.getPermissions().contains(Permission.ADMINISTRATOR)) {
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage("Only Admins can stop me now!").queue();
				}
			}
		}
	}
}