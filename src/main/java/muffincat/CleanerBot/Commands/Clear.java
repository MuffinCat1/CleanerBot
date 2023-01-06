package muffincat.CleanerBot.Commands;

import java.util.List;

import muffincat.CleanerBot.App;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Clear extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent _event) {
		if (!_event.isFromGuild()) return;
		
		String[] _args = _event.getMessage().getContentRaw().split("\\s+");
		 
		if(_args[0].equalsIgnoreCase(App.PREFEX + "clear")) {
			Member _member = _event.getMember();
			
			if(_member != null) {
				
				if(_member.getPermissions().contains(Permission.ADMINISTRATOR)) {
					
					if(_args.length < 2 || _args.length > 2) {
						EmbedBuilder _usage = new EmbedBuilder();
						_usage.setColor(0xff3923);
						_usage.setTitle("Specify amount to delete");
						_usage.setDescription("Usage: `" + App.PREFEX + "clear [# of messages]`");
					
						_event.getChannel().sendMessageEmbeds(_usage.build()).queue();
					} else { 
						try {
							Message _firstMessage = _event.getMessage();
							_event.getGuildChannel().deleteMessageById(_firstMessage.getId()).queue();
							
							List<Message> _messages = _event.getChannel().getHistory().retrievePast(Integer.parseInt(_args[1])).complete();
							_event.getGuildChannel().deleteMessages(_messages).queue();
															
							EmbedBuilder _succes = new EmbedBuilder();
							_succes.setColor(0x22ff2a);
							_succes.setTitle("✅ Succesfully deleted " + _args[1] + " messages");
								
							_event.getChannel().sendMessageEmbeds(_succes.build()).queue();
							
						} catch (Exception _ex) {
							_ex.printStackTrace();
							EmbedBuilder _error = new EmbedBuilder();
							
							if(_ex.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {	
								_error.setColor(0xff3923);
								_error.setTitle("🔴 Too many messages selected");
								_error.setDescription("only messages Between 1-100 can be deleted at one time (cannot delete 0 messages)");
						
								_event.getChannel().sendMessageEmbeds(_error.build()).queue();
					
							} else if(_ex.toString().startsWith("java.lang.NumberFormatException: For input string")){
								_error.setColor(0xff3923);
								_error.setTitle("🔴 Wrong Input");
								_error.setDescription("Please put a number between 1-100");
						
								_event.getChannel().sendMessageEmbeds(_error.build()).queue();								
							} else {
								_error.setColor(0xff3923);
								_error.setTitle("🔴 Selected messages are older then two weeks");
								_error.setDescription("Messages older then two weeks cannot be deleted");
						
								_event.getChannel().sendMessageEmbeds(_error.build()).queue();
							}
				
						} 
					}
					
				} else {
					_event.getChannel().sendTyping().queue();
					_event.getChannel().sendMessage("Only Admins can use that command").queue();
				}
			 }
		 }
	}
