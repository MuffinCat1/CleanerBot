package muffincat.CleanerBot.Commands;

import muffincat.CleanerBot.App;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;

public class Stopbot extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent _event) {
		if (!_event.isFromGuild()) return;
		
		String[] _args = _event.getMessage().getContentRaw().split("\\s+");
		
		if(_args[0].equalsIgnoreCase(App.PREFEX + "stopbot")) {
			Member _member = _event.getMember();
			if(_member != null) {
				if(_member.getPermissions().contains(Permission.ADMINISTRATOR)) {
					_event.getChannel().sendTyping().queue();
					_event.getChannel().sendMessage("Im going to sleep").queue();
					System.exit(0);
				} else {
					_event.getChannel().sendTyping().queue();
					_event.getChannel().sendMessage("Only Admins can stop me now!").queue();
				}
			}
		}
	}
}
