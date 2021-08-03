import java.util.List;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

public class Simp {
	public static JDA jda;
	public static String prefix = "--";
	public static void main (String[] args) throws Exception
	{
		jda = JDABuilder.createDefault("ODQwMDQwOTMxNDgxODc4NTM5.YJSbFQ.vKZ-pJ2DhhDEGSqJxixi1mo3axA").build();
		jda.addEventListener(new Commands());
		jda.awaitReady();
		List <Guild> Servers = jda.getGuilds();
		jda.getPresence().setActivity(Activity.playing("Currently Serving " + Servers.size() + " Servers"));
	}
	
	
}
