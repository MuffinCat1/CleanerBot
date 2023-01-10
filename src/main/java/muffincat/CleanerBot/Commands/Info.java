package muffincat.CleanerBot.Commands;

import muffincat.CleanerBot.App;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
//import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

public class Info extends ListenerAdapter{

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent _event) {
		if (!_event.isFromGuild()) return;
		
		if(_event.getName().equals("info")) {			

			EmbedBuilder _info = new EmbedBuilder();
			_info.setTitle("🧹Information");
			_info.setDescription("A bot that can clean up to 100 messages that are two weeks old, he can't delete messages that are higher than two weeks old. You can use the `/` + "
					+ "`clear` to clean the messages; for example `/clear 100` will clean 100 messages");
			_info.addField("🛠️Commands","`/info` - show info on the bot\n`/clear [amount of messages]` - clear a number of messages that are in chat. up to a 100 messages per delete", false);
			//_info.addField("⚙️Options","`noEmbed` - disables the embed message after the bot finishes deleting the messages", false); //maybe in the future 
			_info.setColor(0xF23309);
			
			_info.setFooter(String.format("Created By %s",App.ME.getAsTag()), App.ME.getAvatarUrl());
			
			_event.replyEmbeds(_info.build()).queue();
			_info.clear();
		}
	}
}
