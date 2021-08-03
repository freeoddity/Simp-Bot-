import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.audio.OpusPacket;
import net.dv8tion.jda.api.audio.UserAudio;
import net.dv8tion.jda.api.entities.User;
public class MyReceiveHandler implements AudioReceiveHandler{
	List <byte[]> received = new ArrayList<>();
	public boolean canReceiveCombined()
	{
		return false;
	}
	
	public boolean canReceiveEncoded()
	{
		return false;
	}
	
	public boolean canReceiveUser()
	{
		return true;
	}
	
	public void handleCombinedAudio(CombinedAudio combinedAudio)
	{
		/*try {
			received.add(combinedAudio.getAudioData(1.0));
		}catch(OutOfMemoryError e)
		{
			e.printStackTrace();
		}
		*/
	}
	
	public void handleEncodedAudio(OpusPacket packet)
	{
		return;
	}
	
	public void handleUserAudio(UserAudio userAudio)
	{
		try {
			received.add(userAudio.getAudioData(1.0));
		}catch(OutOfMemoryError e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean includeUserInCombinedAudio(User user)
	{
		if (user.isBot())
			return false;
		else
			return true;
	}
	public List <byte[]> getList()
	{	
		return received;
	}
	public void clearList()
	{
		received.clear();
	}
}
