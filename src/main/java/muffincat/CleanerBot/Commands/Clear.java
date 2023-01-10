package muffincat.CleanerBot.Commands;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.RestAction;

@SuppressWarnings("unused")
public class Clear extends ListenerAdapter{
	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent _event) {
		if (!_event.isFromGuild()) return;
		
		if(_event.getName().equals("clear")) {
			
			_event.deferReply().queue(); 
			
			OptionMapping amountOption = _event.getOption("amount");
			
			Member _member = _event.getMember();
			
			if(_member != null) {
				
				if(_member.getPermissions().contains(Permission.ADMINISTRATOR)) {
					
					if(amountOption == null) {
						EmbedBuilder _usage = new EmbedBuilder();
						_usage.setColor(0xff3923);
						_usage.setTitle("Specify amount to delete");
						_usage.setDescription("Usage: `/clear [# of messages]`");
					
						_event.getHook().sendMessageEmbeds(_usage.build()).queue();
					
					} else { 
						try {
							
							int amount = amountOption.getAsInt();
							
							 String _firstMessageID = _event.getChannel().getLatestMessageId();
							_event.getGuildChannel().deleteMessageById(_firstMessageID).queue();
							
							List<Message> _messages = _event.getChannel().getHistory().retrievePast(amount).complete();
							
							if (_messages.size() > 0) {								
								if (amount < 2) {
									_event.getGuildChannel().deleteMessageById(_messages.get(0).getId()).queue();
								} else {
									_event.getGuildChannel().deleteMessages(_messages).queue();
								}
								
								EmbedBuilder _succes = new EmbedBuilder();
								_succes.setColor(0x22ff2a);
								_succes.setTitle("✅ Succesfully deleted " + amount + " messages");
								
								_event.getHook().sendMessageEmbeds(_succes.build()).queue();
							}
							
						} catch (Exception _ex) {
							_ex.printStackTrace();
							EmbedBuilder _error = new EmbedBuilder();
							
							if(_ex.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {	
								_error.setColor(0xff3923);
								_error.setTitle("🔴 Too many messages selected");
								_error.setDescription("only messages Between 1-100 can be deleted at one time (cannot delete 0 messages)");
						
								_event.getHook().sendMessageEmbeds(_error.build()).queue();
					
							} else if(_ex.toString().startsWith("java.lang.NumberFormatException: For input string")){
								_error.setColor(0xff3923);
								_error.setTitle("🔴 Wrong Input");
								_error.setDescription("Please write a number between 1-100");
						
								_event.getHook().sendMessageEmbeds(_error.build()).queue();								
							
							} else if(_ex.toString().startsWith("java.lang.IllegalArgumentException: Must provide at least 2")){
								_error.setColor(0xff3923);
								_error.setTitle("🔴 Something went wrong");
								_error.setDescription("Too many messages were selected");
						
								_event.getHook().sendMessageEmbeds(_error.build()).queue();	
							} else {
								_error.setColor(0xff3923);
								_error.setTitle("🔴 Selected messages are older then two weeks");
								_error.setDescription("Messages older then two weeks cannot be deleted");
						
								_event.getHook().sendMessageEmbeds(_error.build()).queue();
							}
				
						} 
					}
					
				} else {
					_event.getHook().sendMessage("Only Admins can use that command").queue();
				}
			 }
		 }
	}
}
