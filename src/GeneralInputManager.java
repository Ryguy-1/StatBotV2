
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//listens to every input it has access to and sends the input to the input manager for each guild.
public class GeneralInputManager extends ListenerAdapter {
	// static JDA instance
	public static JDA jda;
	// runner class
	private Runner runner;
	// boolean to avoid missed messages in onMessageReceived method
	private boolean isWrong;
	// number of Guilds
	private int numGuilds;

	// guild variables
	private ArrayList<String> guildIds;
	private ArrayList<String> guildNames;
	private ArrayList<Guild> guilds;
	
	//scrapers for users
	public static ArrayList<SeleniumBot> userBots = new ArrayList<SeleniumBot>();
	//4 probably for final version
	public static final int capacity = 1;

	GeneralInputManager() throws LoginException {
		//sets property for google scraping
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		for (int i = 0; i <	capacity; i++) {
			userBots.add(new SeleniumBot());
		}
		// initialize Runner
		runner = new Runner();
		// initialize JDA
		jda = JDABuilder.createDefault(runner.getDiscordAPIKey()).addEventListeners(this).build();
		// set status of StatBot
		jda.getPresence().setActivity(Activity.playing("Gathering Reconnaissance..."));
		// initialize
		isWrong = false;
		// initialize guild variables
		guildIds = new ArrayList<String>();
		guildNames = new ArrayList<String>();
		guilds = new ArrayList<Guild>();
		
		

	}

	public void onMessageReceived(MessageReceivedEvent event) {

		// will not respond to bots
		if (!event.getAuthor().isBot()) {
			boolean inGuilds = false;
			// checks if in a guild
			for (int i = 0; i < this.guildIds.size(); i++) {
				try {
					if (this.guildIds.get(i).equals(event.getGuild().getId())) {
						inGuilds = true;
					}
				} catch (Exception e) {
					// if you try to access the bot by talking directly to it. Also sets isWrong to
					// make sure second try catch block does not print twice;
					if (!event.getMessage().getContentRaw().contains("Please enter a server")) {
						event.getChannel().sendMessage("Please enter a server, not directly to the bot.").queue();
						isWrong = true;
						// so loop doesn't keep running and print message more than once.
						break;
					}
				}
			}
			// if not in a guild do this...
			if (!inGuilds) {
				try {
					System.out.println(
							"New Guild - Id: " + event.getGuild().getId() + ", Name: " + event.getGuild().getName());
				} catch (Exception e) {
				}
				// tries to add the new guild if the message was sent from a guild and inGuilds
				// == false
				this.numGuilds = jda.getGuilds().size();
				try {
					System.out.println("Here1");
					this.guildIds.add(event.getGuild().getId());
					System.out.println("Here2");
					this.guildNames.add(event.getGuild().getName());
					System.out.println("Here3");
					try {
						// adds new input manager for the new guild
						this.guilds.add(new Guild(event.getGuild().getId()));
					} catch (Exception e) {
						System.out.println("no add guild");
					}
					// sends the event just put in to the latest created inputManager. This ensures
					// no messages go unheard.
					this.guilds.get(this.guilds.size() - 1).sendEvent(event);
					System.out.println("Here4");
				} catch (Exception e) {
					// if you try to access the bot by talking directly to it. And message has not
					// been printed out already.
					if (!event.getMessage().getContentRaw().contains("Please enter a server") && isWrong == false) {
						event.getChannel().sendMessage("There was an error :/ join support server for help!").queue();
					}
				}
				// condition to make sure Please enter a server message is not printed twice. Is
				// set in first try catch block above.
				isWrong = false;
				// if in a guild do this.
			} else if (inGuilds) {
				// if the event sent is from a guild with an inputManager already in place, it
				// directs the event to that inputManager.
				for (int i = 0; i < this.guildIds.size(); i++) {
					if (this.guildIds.get(i).equals(event.getGuild().getId())) {
						// managers and guildIds have same indexes
						try {
							this.guilds.get(i).sendEvent(event);
						} catch (Exception e) {
						}
					}
				}
			}
		}
	}
}