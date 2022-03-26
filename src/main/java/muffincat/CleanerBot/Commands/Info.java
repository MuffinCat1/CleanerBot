package muffincat.CleanerBot.Commands;

import muffincat.CleanerBot.App;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Info extends ListenerAdapter{
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)  {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(App.prefex + "info")) {			
			event.getChannel().sendTyping().queue();

			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("🧹Information");
			info.setDescription("A bot that can clean up to 100 messages that are two weeks old, he cannot delete messages that are higher than two weeks old. You can use the \"~\" + "
					+ "commad for example `~clean 100` will clean 100 messages");
			info.addField("🛠️Commands","`~info` - show info\n`~clear [# of messages]` - clear a number of messages that you put up too a 100\n`~stopbot` - command to stop the bot (only admins permissions can do that one)", false);
			info.setColor(0xF23309);
			info.setFooter("Created By 𝓜uffin_ℂat🧁#0767", "https://cdn.discordapp.com/avatars/575921629465673730/b491e31709215f4f6906cd1ab212f407.png");
			
			event.getChannel().sendMessageEmbeds(info.build()).queue();
			info.clear();
			
		}
	}
}