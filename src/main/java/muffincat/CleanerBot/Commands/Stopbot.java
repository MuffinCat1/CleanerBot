package muffincat.CleanerBot.Commands;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;

public class Stopbot extends ListenerAdapter {
	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent _event) {
		if(!_event.isFromGuild()) return;
		
		if(_event.getName().equals("stop")) {
			
			Member _member = _event.getMember();

			if(_member != null) {
				if(_member.getPermissions().contains(Permission.ADMINISTRATOR)) {
					_event.getChannel().sendTyping().queue();
					_event.reply("Im going to sleep").queue();
					System.exit(0);
				} else {
					_event.getChannel().sendTyping().queue();
					_event.reply("Only Admins can stop me now!").queue();
				}
			}
		}
	}
}
