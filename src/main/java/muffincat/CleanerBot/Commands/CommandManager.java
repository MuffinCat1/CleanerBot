package muffincat.CleanerBot.Commands;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class CommandManager extends ListenerAdapter{
	
	@Override
	public void onGuildReady(@NotNull GuildReadyEvent _event) {
		List<CommandData> commandData = new ArrayList<>();
		OptionData option = new OptionData(OptionType.INTEGER, "amount", "the amount of messages to delete (100 is max)", true);
		
		commandData.add(Commands.slash("info", "the info of the jenitor"));		
		commandData.add(Commands.slash("clear", "clears all the messages before these message up to a 100").addOptions(option));
		commandData.add(Commands.slash("stop", "just stops the bot"));
		
		_event.getGuild().updateCommands().addCommands(commandData).queue();
	}
}
