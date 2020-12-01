
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
	private ExchangeRates exchangeRates;
	private IpInfo ipInfo;
	private KanyeRest kanyeRest;
	private Jokes jokes;
	private MovieReview review;
	private PokemonInfo pokemonInfo;
	private Dog dog;
	private Cat cat;
	private NumberInfo numberInfo;
	private Date date;

	Guild(String guildId) {
		this.guildId = guildId;
		// put "us" in constructor as a placeholder
		covidStatsAPI = new CovidStatsAPI("us");
		weatherAPI = new WeatherAPI(1, 1);
		namePredict = new NamePredict("john");
		getImage = new GetImage("tree");
		placeToLngLat = new PlaceToLngLat("San Diego");
		placeInfoByName = new PlaceInfoByName("San Diego");
		exchangeRates = new ExchangeRates("USD", "USD");
		ipInfo = new IpInfo("161.185.160.93");
		kanyeRest = new KanyeRest();
		jokes = new Jokes();
		review = new MovieReview("Lebowski");
		pokemonInfo = new PokemonInfo("Charizard");
		dog = new Dog();
		cat = new Cat();
		numberInfo = new NumberInfo("42");
		date = new Date("4/30");

	}

	public void sendEvent(MessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		MessageChannel channel = event.getChannel();
		if (message.contains("intel ping")) {
			// pings server
			ping(channel, event);
		} else if (message.contains("intel covid")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("covid")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				covid(channel, substring, event);
			}
		} else if (message.contains("intel weather")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("weather")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				weather(channel, substring, event);
			}
		} else if (message.contains("intel name")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("name")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				name(channel, substring, event);
			}
		} else if (message.contains("intel image")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("image")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				image(channel, substring, event);
			}
		} else if (message.contains("intel lnglat")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("lnglat")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				lnglat(channel, substring, event);
			}
		} else if (message.contains("intel placeinfo")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("placeinfo")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				placeinfo(channel, substring, event);
			}
		} else if (message.contains("intel convertcurrency")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("convertcurrency")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				convertcurrency(channel, substring, event);
			}
		} else if (message.contains("intel ipinfo")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("ipinfo")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				ipinfo(channel, substring, event);
			}
		} else if (message.contains("intel movie")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("movie")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				movie(channel, substring, event);
			}
		} else if (message.contains("intel pokemon")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("pokemon")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				pokemon(channel, substring, event);
			}
		} else if (message.contains("intel number")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("number")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				number(channel, substring, event);
			}
		}  else if (message.contains("intel date")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("date")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				date(channel, substring, event);
			}
		} else if (message.contains("intel google")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("google")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				google(channel, substring, event);
			}
		} else if (message.contains("intel kanye")) {
			kanyequote(channel, event);
		} else if (message.contains("intel joke")) {
			joke(channel, event);
		} else if (message.contains("intel dog")) {
			dog(channel, event);
		}else if (message.contains("intel cat")) {
			cat(channel, event);
		}else if (message.contains("intel help")) {
			help(channel);
			// add to help list
			// - convertCurrency
			// -ipinfo
		}
	}

	public String getGuildId() {
		return this.guildId;
	}

	// ping code
	private void ping(MessageChannel channel, MessageReceivedEvent event) {
		long time = System.currentTimeMillis();
		channel.sendMessage("Pinging Recon...").queue(response -> {
			EmbedBuilder eb = new EmbedBuilder();
			MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
			eb.setColor(new Color(255, 105, 180));
			eb.setTitle(event.getAuthor().getName() + "'s Ping:");
			eb.setDescription(System.currentTimeMillis() - time + " ms");
			eb.setFooter("Powered By Recon");// will need to have image as second parameter eventually
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
			eb.setTitle("The Correct Format is intel weather longitude,lattitude");
			eb.setFooter("Powered By Recon");// will need to have image as second parameter eventually
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
		eb.setTitle("Image Delivery ✉📩: " + message);
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
		eb.setTitle("Longitude and Latitude  🌎🌐: " + message);
		// sets fields...
		if (!placeToLngLat.getResponseRaw().equals("Place to Long Lat API Error")) {
			eb.setDescription(placeToLngLat.getLongitude() + "," + placeToLngLat.getLatitude());
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
			eb.addField("Currency💵", placeInfoByName.getCurrency(), true);
			eb.addField("Road Side🚗", placeInfoByName.getRoadSide(), true);
			eb.addField("Speed In🚅", placeInfoByName.getSpeedIn(), true);
			eb.addField("Time Zone🌐", placeInfoByName.getTimeZone(), true);
			eb.addField("Calling Code📞☎", placeInfoByName.getCallingCode() + "", true);
			eb.addField("Longitude Latitude🌐ℹ", placeInfoByName.getlnglat(), true);
			// end set fields...
		} else {
			eb.setDescription(placeInfoByName.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By OpenCage");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void convertcurrency(MessageChannel channel, String message, MessageReceivedEvent event) {
		try {
			String[] arrOfStr = message.split(",", 2);
			System.out.println(arrOfStr[0]);
			System.out.println(arrOfStr[1]);
			exchangeRates.getStats(arrOfStr[0], arrOfStr[1]);
			EmbedBuilder eb = new EmbedBuilder();
			MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
			eb.setColor(new Color(255, 105, 180));
			eb.setTitle("Exchange Rate from " + arrOfStr[0] + " to " + arrOfStr[1] + "💵💶💷");

			if (!exchangeRates.getResponseRaw().equals("Exchange Rate API Error")) {
				eb.setDescription(exchangeRates.getExchangeRate() + "");
			} else {
				eb.setDescription(exchangeRates.getResponseRaw() + " ☹");
			}
			eb.setFooter("Powered By ExchangeRate-API");// will need to have image as second parameter eventually
			eb3 = eb.build();
			channel.sendMessage(eb3).queue();

		} catch (Exception e) {
			EmbedBuilder eb = new EmbedBuilder();
			MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
			eb.setColor(new Color(255, 105, 180));
			eb.setTitle("The Correct Format is intel convertcurrency (convertFrom,convertTo)");
			eb.setFooter("Powered By ExchangeRate-API");// will need to have image as second parameter eventually
			eb3 = eb.build();
			channel.sendMessage(eb3).queue();
		}
	}

	private void ipinfo(MessageChannel channel, String message, MessageReceivedEvent event) {
		ipInfo.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("IP Information for " + message + "  🔗💻");
		// sets fields...
		if (!ipInfo.getResponseRaw().equals("Ip Info API Error")) {
			String[] arrOfStr = ipInfo.getLoc().split(",", 2);
			String tempLngLat = arrOfStr[1] + "," + arrOfStr[0];

			eb.addField("City", ipInfo.getCity(), true);
			eb.addField("Region", ipInfo.getRegion(), true);
			eb.addField("Country", ipInfo.getCountry(), true);
			eb.addField("Lng,Lat", tempLngLat, true);
			eb.addField("Postal", ipInfo.getPostal(), true);
			eb.addField("Time Zone", ipInfo.getTimeZone(), true);

			// end set fields...
		} else {
			eb.setDescription(ipInfo.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By IpInfo");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void kanyequote(MessageChannel channel, MessageReceivedEvent event) {
		kanyeRest.getStats();
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("The Prophet Speaks... 🗯");
		// sets fields...
		if (!kanyeRest.getResponseRaw().equals("Kanye Rest API Error")) {
			eb.setImage("https://wpr-public.s3.amazonaws.com/wprorg/images/segments/kanye_west.jpg");
			eb.setDescription("\"" + kanyeRest.getQuote() + "\" -Kanye");
			// end set fields...
		} else {
			eb.setDescription(kanyeRest.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By Kanye Rest");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void joke(MessageChannel channel, MessageReceivedEvent event) {
		jokes.getStats();
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("A Joke for the Uncreative... 😂🧺");
		// sets fields...
		if (!jokes.getResponseRaw().equals("Joke API Error")) {
			eb.addField("Setup", jokes.getSetup(), true);
			eb.addField("Punchline", jokes.getPunchline(), true);
			// end set fields...
		} else {
			eb.setDescription(jokes.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By Official-Joke-API");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void movie(MessageChannel channel, String message, MessageReceivedEvent event) {
		review.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("Review of: " + message + "  📜");
		// sets fields...
		if (!review.getResponseRaw().equals("Movie API Error")) {
//			this.displayTitle = obj2.getString("display_title");
//			this.rating = obj2.getString("mpaa_rating");
//			this.summary = obj2.getString("summary_short");
//			this.openingDate = obj2.getString("opening_date");
			eb.addField("Title", review.getTitle(), true);
			eb.addField("Rating", review.getRating(), true);
			try {
				eb.addField("Opening Date", review.getOpeningDate(), true);
			} catch (Exception e) {
			}
			eb.setDescription("**Summary**\n" + review.getSummary());

			// end set fields...
		} else {
			eb.setDescription(review.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By New York Times API");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void pokemon(MessageChannel channel, String message, MessageReceivedEvent event) {
		pokemonInfo.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle(message + " 🎴🃏");
		// sets fields...
		if (!pokemonInfo.getResponseRaw().equals("Pokemon API Error")) {
			try {
				eb.addField("Weight", pokemonInfo.getWeight() + "", true);
				eb.addField("Height", pokemonInfo.getHeight() + "", true);
				System.out.println(pokemonInfo.getImage());
				eb.setImage(pokemonInfo.getImage());
				
				String moves = "**First 10 Moves**\n\n";
				for (int i = 0; i < pokemonInfo.getMoves().size(); i++) {
					if (i < 10) {
						moves += i + 1 + ") " + pokemonInfo.getMoves().get(i) + "   ";
//						if(i==4) {
//							moves+="\n";
//						}
					}
				}
				pokemonInfo.clearMoves();
				eb.setDescription(moves);
			} catch (Exception e) {
				pokemonInfo.clearMoves();
			}

			// end set fields...
		} else {
			eb.setDescription(pokemonInfo.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By PokeAPI");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void dog(MessageChannel channel, MessageReceivedEvent event) {
		dog.getStats();
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("Dog🐶🐕‍");
		// sets fields...
		if (!dog.getResponseRaw().equals("Dog API Error")) {
			eb.setImage(dog.getImageURL());
			// end set fields...
		} else {
			eb.setDescription(dog.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By Dog API");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	
	private void cat(MessageChannel channel, MessageReceivedEvent event) {
		cat.getStats();
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("Cat🐱🐈");
		// sets fields...
		if (!cat.getResponseRaw().equals("Cat API Error")) {
			eb.setImage(cat.getImageURL());
			// end set fields...
		} else {
			eb.setDescription(cat.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By Cat API");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	
	private void number(MessageChannel channel, String message, MessageReceivedEvent event) {
		numberInfo.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle(message + " 🔢");
		// sets fields...
		if (!numberInfo.getResponseRaw().equals("Number API Error")) {
			eb.setDescription(numberInfo.getResponseRaw());

			// end set fields...
		} else {
			eb.setDescription(numberInfo.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By NumbersAPI");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	
	private void date(MessageChannel channel, String message, MessageReceivedEvent event) {
		date.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle(message + " 🔢");
		// sets fields...
		if (!date.getResponseRaw().equals("Date API Error")) {
			eb.setDescription(date.getResponseRaw());

			// end set fields...
		} else {
			eb.setDescription(date.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By NumbersAPI");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}
	
	
	private void google(MessageChannel channel, String message, MessageReceivedEvent event) {

		channel.sendMessage("Gathering Recon On "+message+"... 🔎").queue();

		Thread t1 = new Thread(() -> {
			int inUseIndex = -1;

			for (int i = 0; i < GeneralInputManager.userBots.size(); i++) {
				if (GeneralInputManager.userBots.get(i).isInUse() == false) {
					GeneralInputManager.userBots.get(i).setAndStart(message);
					inUseIndex = i;
					break;
				}
			}
			
			// Initialized to -1 and if not -1 then there is a bot available. If is -1 that
			// means capacity is slammed at that moment.
			if (inUseIndex != -1) {
				
				//asks SeleniumBot class every second if it has the result yet
				do {
					pauseMilliseconds(100);
				} while (GeneralInputManager.userBots.get(inUseIndex).isDone() == false);
				
				
				try {
					channel.sendMessage("Your search for "+message+"...🔎");

					channel.sendFile(GeneralInputManager.userBots.get(inUseIndex).getFileFile(), "User Thread.jpg").queue();
				} catch (Exception e) {
					EmbedBuilder eb = new EmbedBuilder();
					MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null,
							null);

					eb.setColor(new Color(255, 105, 180));
					eb.setTitle(
							"Unexpected Error... Please wait a few seconds and try again or join our **Support Server** for help and questions!");
					eb3 = eb.build();

					channel.sendMessage(eb3).queue();
				}
				// if there is not enough capacity with the amount of scrapers at the moment.
			} else {
				EmbedBuilder eb = new EmbedBuilder();
				MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null,
						null);

				eb.setColor(new Color(255, 105, 180));
				eb.setTitle(
						"Not Enough Capacity at the Moment! Wait a few seconds and try again or join our **Support Server** for help and questions!");
				eb3 = eb.build();

				channel.sendMessage(eb3).queue();
			}

		});
		t1.start();
		
		
	}
	
	
	private void help(MessageChannel channel) {
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(255, 105, 180));
		eb.setTitle("📜Gathering Reconnaissance...📊");
		eb.addField("Google🔎🔍", "`intel google (anything)`", true);
		eb.addField("Covid Stats🦠📈", "`intel covid (us)`", true);
		eb.addField("Weather☁🌡", "`intel weather (long,lat)`", true);
		eb.addField("Name -> Age Predictions💭📊", "`intel name (name)`", true);
		eb.addField("Image📷🖼", "`intel image (ImageName)`", true);
		eb.addField("Longitude Latitude🌎🌐", "`intel lnglat (place)`", true);
		eb.addField("Place Information🌎🌐", "`intel placeinfo (place)`", true);
		eb.addField("Convert Currency💶💵", "`intel convertcurrency (convert from,convert to)`", true);
		eb.addField("IP Info💻🔗", "`intel ipinfo (ip address)`", true);
		eb.addField("Kanye Quotes💭", "`intel kanye`", true);
		eb.addField("Uncreative Jokes😂🧺", "`intel joke`", true);
		eb.addField("Movie Recon🎥🎬", "`intel movie (movie title)`", true);
		eb.addField("Pokemon🃏🎴", "`intel pokemon (pokemon)`", true);
		eb.addField("Dog🐶🐕‍", "`intel dog`", true);
		eb.addField("Cat🐱🐈", "`intel cat`", true);
		eb.addField("Number🔢", "`intel number (number)`", true);
		eb.addField("Date🔢", "`intel date (mm/dd)`", true);
		eb.addField("Help🆘", "`intel help`", true);

		eb.setDescription(
				"**[Invite Here](https://discord.com/api/oauth2/authorize?client_id=779185137971494932&permissions=522304&scope=bot)**");
		eb.setFooter("Powered By Recon");// will need to have image as second parameter eventually
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	// method to pause for x seconds
	private static void pauseSeconds(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void pauseMilliseconds(long milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
