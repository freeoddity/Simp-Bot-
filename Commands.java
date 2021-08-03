import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class Commands extends ListenerAdapter{
	Random rand = new Random();
	String [] fun = {"Heya", "Hi cutie", "What's up dood"};
	final String [] trigger = {"Hey", "Hi", "Hola", "Heyy", "Heyyy", "What's up", "Yo"};
	String randStr = randString(5);
	int test = 0;
	List <>
	public File randFile()
	{
		test++;
		File randFile = new File(randStr + "_" +  test + ".pcm");
		return randFile;
	}
	public String randString(int length)
	{
		int min = 97;
		int max = 122;
		Random rand = new Random();
		int randInt;
		StringBuilder Buffer = new StringBuilder(length);
		String randStr;
		for (int i = 0; i < length; i++)
		{
			randInt = min + (int)(rand.nextFloat() * (max - min +1));
			Buffer.append((char)randInt);
		}
		randStr = Buffer.toString();
		return randStr;
	}
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if (event.getAuthor().isBot())
		{
			return;
		}
		Message message = event.getMessage();
		String content = message.getContentRaw();
		MessageChannel channel = event.getChannel();
		if (content.equals(trigger[4]))
		{
			channel.sendMessage(fun[1]).queue();
		}
		else
		{
			for (int i =0; i < trigger.length; i++)
			{
				if (content.equalsIgnoreCase(trigger[i]))
				{
					channel.sendMessage(fun[rand.nextInt(3)]).queue();
				}
			}
		}
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		String message = event.getMessage().getContentRaw();
		if (event.getAuthor().isBot())
		{
			return;
		}
		Guild guild = event.getGuild();
		MessageChannel channel2 = event.getChannel();
		VoiceChannel channel = event.getMember().getVoiceState().getChannel();
		AudioManager manager = guild.getAudioManager();
		
		if (event.getMessage().getContentRaw().equals(Simp.prefix + "join"))
		{
			if (channel == null)
			{
				event.getChannel().sendMessage("Join a Voice Channel first!, Then try again").queue();
				return;
			}
			manager.openAudioConnection(channel);
			List <Member> mem = channel.getMembers();
			for (Member e: mem)
			{
				
			}
			MyReceiveHandler handler = new MyReceiveHandler();
			handler.clearList();
			manager.setReceivingHandler(handler);
		}
		
		if (message.equals(Simp.prefix + "leave"))
		{
			manager.closeAudioConnection();
			try(FileOutputStream outputStream = new FileOutputStream(randFile()))
			{
				MyReceiveHandler handler = (MyReceiveHandler) manager.getReceivingHandler();
				for (byte[] e : handler.getList())
				{
					outputStream.write(e);
				}
				channel2.sendMessage("finished writing file").queue();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}