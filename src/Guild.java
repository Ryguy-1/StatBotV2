
import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Guild {
	private String guildId;
	private CovidStatsAPI covidStatsAPI;
	private WeatherAPI weatherAPI;
	private NamePredict namePredict;
	private GetImage getImage;
	private PlaceToLngLat placeToLngLat;
	private PlaceInfoByName placeInfoByName;
	
	Guild(String guildId) {
		this.guildId = guildId;
		// put "us" in constructor as a placeholder
		covidStatsAPI = new CovidStatsAPI("us");
		weatherAPI = new WeatherAPI(1, 1);
		namePredict = new NamePredict("john");
		getImage = new GetImage("tree");
		placeToLngLat = new PlaceToLngLat("San Diego");
		placeInfoByName = new PlaceInfoByName("San Diego");
		
		
	}

	public void sendEvent(MessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		MessageChannel channel = event.getChannel();
		if (message.contains("/stat ping")) {
			// pings server
			ping(channel, event);
		} else if (message.contains("/stat covid")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("covid")) {
				channel.sendMessage("See /stat help covid for formatting!").queue();
			} else {
				covid(channel, substring, event);
			}
		} else if (message.contains("/stat weather")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("weather")) {
				channel.sendMessage("See /stat help weather for formatting!").queue();
			} else {
				weather(channel, substring, event);
			}
		} else if (message.contains("/stat name")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("name")) {
				channel.sendMessage("See /stat help name for formatting!").queue();
			} else {
				name(channel, substring, event);
			}
		}else if (message.contains("/stat image")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("image")) {
				channel.sendMessage("See /stat help image for formatting!").queue();
			} else {
				image(channel, substring, event);
			}
		}else if (message.contains("/stat lnglat")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("lnglat")) {
				channel.sendMessage("See /stat help lnglat for formatting!").queue();
			} else {
				lnglat(channel, substring, event);
			}
		}else if (message.contains("/stat placeinfo")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("placeinfo")) {
				channel.sendMessage("See /stat help placeinfo for formatting!").queue();
			} else {
				placeinfo(channel, substring, event);
			}
		}
	}

	public String getGuildId() {
		return this.guildId;
	}

	// ping code
	private void ping(MessageChannel channel, MessageReceivedEvent event) {
		long time = System.currentTimeMillis();
		channel.sendMessage("Pinging StatBot...").queue(response -> {
			EmbedBuilder eb = new EmbedBuilder();
			MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
			eb.setColor(new Color(255, 105, 180));
			eb.setTitle(event.getAuthor().getName() + "'s Ping:");
			eb.setDescription(System.currentTimeMillis() - time + " ms");
			eb.setFooter("Powered By StatBot");// will need to have image as second parameter eventually
			eb3 = eb.build();
			channel.sendMessage(eb3).queue();
		});
	}

	// Covid API code
	private void covid(MessageChannel channel, String message, MessageReceivedEvent event) {
		covidStatsAPI.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("Covid Stats 🦠📈");
		// sets fields...
		if (!covidStatsAPI.getResponseRaw()
				.equals("API only supports US right now, not individual states or countries ☹.")) {
			eb.addField("Positive", covidStatsAPI.positive() + "", true);
			eb.addField("Negative", covidStatsAPI.negative() + "", true);
			eb.addField("Date", covidStatsAPI.date() + "", true);
			eb.addField("Pending", covidStatsAPI.pending() + "", true);
			eb.addField("Hospitalized Currently", covidStatsAPI.hospitalizedCurrently() + "", true);
			eb.addField("In ICU Currently", covidStatsAPI.inIcuCurrently() + "", true);
			eb.addField("In ICU Cumulative", covidStatsAPI.inIcuCumulative() + "", true);
			eb.addField("On Ventilator Currently", covidStatsAPI.onVentilatorCurrently() + "", true);
			eb.addField("On Ventilator Cumulative", covidStatsAPI.onVentilatorCumulative() + "", true);
			eb.addField("Recovered", covidStatsAPI.recovered() + "", true);
			eb.addField("Date Checked", covidStatsAPI.dateChecked(), true);
			eb.addField("Death", covidStatsAPI.death() + "", true);
			eb.addField("Total Test Results", covidStatsAPI.totalTestResults() + "", true);
			eb.addField("Death Increase", covidStatsAPI.deathIncrease() + "", true);
			eb.addField("Hospitalized Increase", covidStatsAPI.hospitalizedIncrease() + "", true);
			eb.addField("Negative Increase", covidStatsAPI.negativeIncrease() + "", true);
			eb.addField("Positive Increase", covidStatsAPI.positiveIncrease() + "", true);
			eb.addField("Total Test Results Increase", covidStatsAPI.totalTestResultsIncrease() + "", true);
			// end set fields...
		} else {
			eb.setDescription(covidStatsAPI.getResponseRaw());
		}
		eb.setFooter("Powered By The Covid Tracking Project");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void weather(MessageChannel channel, String message, MessageReceivedEvent event) {

		try {
			String[] arrOfStr = message.split(",", 2);
			System.out.println(arrOfStr[0]);
			System.out.println(arrOfStr[1]);
			weatherAPI.getStats(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[1]));
			EmbedBuilder eb = new EmbedBuilder();
			MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
			eb.setColor(new Color(255, 105, 180));
			eb.setTitle("Weather ☁🌡");

			if (!weatherAPI.getResponseRaw().equals("Weather API Error")) {
				for (int i = 0; i < weatherAPI.getResponseArray().length; i++) {
					eb.addField("Hour " + (i + 1) * 3,
							"Weather: " + weatherAPI.getResponseArray()[i][5] + "\nPrecipitation: "
									+ weatherAPI.getResponseArray()[i][1] + "\nPrecipitation Amount: "
									+ weatherAPI.getResponseArray()[i][4] + "\nWind Direction: "
									+ weatherAPI.getResponseArray()[i][2] + "\nWind Speed: "
									+ weatherAPI.getResponseArray()[i][3],
							true);
				}
				// end set fields...
			} else {
				eb.setDescription(weatherAPI.getResponseRaw() + " ☹");
			}
			eb.setFooter("Powered By 7Timer");// will need to have image as second parameter eventually
			eb3 = eb.build();
			channel.sendMessage(eb3).queue();

		} catch (Exception e) {
			EmbedBuilder eb = new EmbedBuilder();
			MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
			eb.setColor(new Color(255, 105, 180));
			eb.setTitle("The Correct Format is /stat weather longitude,lattitude");
			eb.setFooter("Powered By StatBot");// will need to have image as second parameter eventually
			eb3 = eb.build();
			channel.sendMessage(eb3).queue();
		}
	}

	private void name(MessageChannel channel, String message, MessageReceivedEvent event) {
		namePredict.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("NamePredict 💭📊");
		// sets fields...
		if (!namePredict.getResponseRaw().equals("Name API Error")) {
			eb.addField("Predicted Age", namePredict.getPredictedAge() + "", true);
			eb.addField("Count for " + message, namePredict.getNumPeopleWithName() + "", true);
			// end set fields...
		} else {
			eb.setDescription(namePredict.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By agify.io");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	
	private void image(MessageChannel channel, String message, MessageReceivedEvent event) {
		getImage.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("Image Delivery ✉📩: "+message);
		// sets fields...
		if (!getImage.getResponseRaw().equals("Image API Error")) {
			eb.setImage(getImage.getImageURL());
			// end set fields...
		} else {
			eb.setDescription(getImage.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By Unsplash");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	
	private void lnglat(MessageChannel channel, String message, MessageReceivedEvent event) {
		placeToLngLat.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("Longitude and Latitude  🌎🌐: "+message);
		// sets fields...
		if (!placeToLngLat.getResponseRaw().equals("Place to Long Lat API Error")) {
			eb.setDescription(placeToLngLat.getLongitude()+","+placeToLngLat.getLatitude());
			// end set fields...
		} else {
			eb.setDescription(placeToLngLat.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By OpenCage");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	
	private void placeinfo(MessageChannel channel, String message, MessageReceivedEvent event) {
		placeInfoByName.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle(message + " Information  🌎🌐");
		// sets fields...
		if (!placeInfoByName.getResponseRaw().equals("Place Info API Error")) {
//			private String currency;
//			private String roadSide;
//			private String speedIn;
//			private String timeZone;
//			private int callingCode;
//			private String lnglat = "See /stat lnglat";
			eb.addField("Currency💵", placeInfoByName.getCurrency(), true);
			eb.addField("Road Side🚗", placeInfoByName.getRoadSide(), true);
			eb.addField("Speed In🚅", placeInfoByName.getSpeedIn(), true);
			eb.addField("Time Zone🌐", placeInfoByName.getTimeZone(), true);
			eb.addField("Calling Code📞☎", placeInfoByName.getCallingCode()+"", true);
			eb.addField("Longitude Latitude🌐ℹ", placeInfoByName.getlnglat(), true);
			// end set fields...
		} else {
			eb.setDescription(placeInfoByName.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By OpenCage");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	

	// method to pause for x seconds
	private static void pause(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
