package muffincat.CleanerBot.Commands;

import muffincat.CleanerBot.App;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Info extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent _event) {
		if (!_event.isFromGuild()) return;
		String[] _args = _event.getMessage().getContentRaw().split("\\s+"); 
		
		if(_args[0].equalsIgnoreCase(App.PREFEX + "info")) {			
			_event.getChannel().sendTyping().queue();

			EmbedBuilder _info = new EmbedBuilder();
			_info.setTitle("🧹Information");
			_info.setDescription("A bot that can clean up to 100 messages that are two weeks old, he cannot delete messages that are higher than two weeks old. You can use the \"~\" + "
					+ "commad for example `~clean 100` will clean 100 messages");
			_info.addField("🛠️Commands","`~info` - show info on the bot\n`~clear [# of messages]` - clear a number of messages that you put up to a 100", false);
			//_info.addField("⚙️Options","`noEmbed` - disables the embed message after the bot finishes deleting the messages", false); //maybe in future 
			_info.setColor(0xF23309);
			
			_info.setFooter(String.format("Created By %s",App.ME.getAsTag()), App.ME.getAvatarUrl());
			 
			_event.getChannel().sendMessageEmbeds(_info.build()).queue();
			_info.clear();
		}
	}
}
