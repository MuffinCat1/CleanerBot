package muffincat.CleanerBot.Commands;

import java.util.List;

import muffincat.CleanerBot.App;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Clear extends ListenerAdapter{
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		 String[] args = event.getMessage().getContentRaw().split("\\s+");
		 
		 Member member = event.getMember();
		 if(member != null) {
			if(member.getPermissions().contains(Permission.ADMINISTRATOR)) {
				if(args[0].equalsIgnoreCase(App.prefex + "clear")) {
					if(args.length < 2 || args.length > 2) {
						EmbedBuilder usage = new EmbedBuilder();
						usage.setColor(0xff3923);
						usage.setTitle("Specify amount to delete");
						usage.setDescription("Usage: `" + App.prefex + "clear [# of messages]`");
					
						event.getChannel().sendMessageEmbeds(usage.build()).queue();
					} else { 
						List<Message> messages;
						try {
							messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete(false);
							event.getChannel().deleteMessages(messages).queue();
					
							EmbedBuilder succes = new EmbedBuilder();
							succes.setColor(0x22ff2a);
							succes.setTitle("✅ Succesfully deleted " + args[1] + " messages");
						
							event.getChannel().sendMessageEmbeds(succes.build()).queue();
				
						} catch (IllegalArgumentException e) {
							if(e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {	
								EmbedBuilder error = new EmbedBuilder();
								error.setColor(0xff3923);
								error.setTitle("🔴 Too many messages selected");
								error.setDescription("only messages Between 1-100 can be deleted at one time (cannot delete zero messages)");
						
								event.getChannel().sendMessageEmbeds(error.build()).queue();
					
							} else if(e.toString().startsWith("java.lang.NumberFormatException: For input string")){
								EmbedBuilder error = new EmbedBuilder();
								error.setColor(0xff3923);
								error.setTitle("🔴 Wrong Input");
								error.setDescription("Please put a number between 1-100");
						
								event.getChannel().sendMessageEmbeds(error.build()).queue();								
							} else {
								EmbedBuilder error = new EmbedBuilder();
								error.setColor(0xff3923);
								error.setTitle("🔴 Selected messages are older then two weeks");
								error.setDescription("Messages older then two weeks cannot be deleted");
						
								event.getChannel().sendMessageEmbeds(error.build()).queue();
							}
				
						} catch (RateLimitedException e) {
							e.printStackTrace();
						}
					}
				}
			} if(!member.getPermissions().contains(Permission.ADMINISTRATOR)) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Only Admins can use that command").queue();
			}
		 }
	}
}