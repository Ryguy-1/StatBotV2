
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Guild {

	private final int amountRPS = 20;
	private final int amountPerCommand = 1;
	private final int googleCost = 10;

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
	private MinecraftServer minecraft;
	private Stonks stonks;
	private StonksDetailed detailStonks;

	private Random random = new Random();

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
		minecraft = new MinecraftServer();
		stonks = new Stonks();
		detailStonks = new StonksDetailed();
	}

	// economy commands and help do not earn you money

	public void sendEvent(MessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		MessageChannel channel = event.getChannel();
		if (message.contains("intel ping")) {
			addCashFromEvent(event, amountPerCommand);
			// pings server
			ping(channel, event);
			System.out.println("ping");
		} else if (message.contains("intel covid")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("covid")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				covid(channel, substring, event);
				System.out.println("covid");
			}
		} else if (message.contains("intel weather")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("weather")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				weather(channel, substring, event);
				System.out.println("weather");
			}
		} else if (message.contains("intel name")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("name")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				name(channel, substring, event);
				System.out.println("name");
			}
		} else if (message.contains("intel image")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("image")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				image(channel, substring, event);
				System.out.println("image");
			}
		} else if (message.contains("intel lnglat")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("lnglat")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				lnglat(channel, substring, event);
				System.out.println("lnglat");
			}
		} else if (message.contains("intel placeinfo")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("placeinfo")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				placeinfo(channel, substring, event);
				System.out.println("placeinfo");
			}
		} else if (message.contains("intel convertcurrency")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("convertcurrency")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				convertcurrency(channel, substring, event);
				System.out.println("convertcurrency");
			}
		} else if (message.contains("intel ip")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("ip")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				ipinfo(channel, substring, event);
				System.out.println("ipinfo");
			}
		} else if (message.contains("intel movie")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("movie")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				movie(channel, substring, event);
				System.out.println("movie");
			}
		} else if (message.contains("intel pokemon")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("pokemon")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				pokemon(channel, substring, event);
				System.out.println("pokemon");
			}
		} else if (message.contains("intel number")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("number")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				number(channel, substring, event);
				System.out.println("number");
			}
		} else if (message.contains("intel date")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("date")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				date(channel, substring, event);
				System.out.println("date");
			}
		} else if (message.contains("intel google")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("google")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				// check if have enough money and if so subtract amount from balance
				if (authorHasCash(event, googleCost)) {
					subtractCashFromEvent(event, googleCost);
					google(channel, substring, event);
					System.out.println("google");
				} else {
					channel.sendMessage("You don't have $" + googleCost + " to search Google :( ").queue();
				}
			}
		} else if (message.contains("intel minecraft")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("minecraft")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				minecraft(channel, substring, event);
				System.out.println("minecraft");
			}
		} else if (message.contains("intel stonk")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("stonk")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				stonk(channel, substring, event);
				System.out.println("stonk");
			}
		} else if (message.contains("intel detailstonk")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("detailstonk")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				addCashFromEvent(event, amountPerCommand);
				detailStonk(channel, substring, event);
				System.out.println("detailstonk");
			}
		} else if (message.contains("intel rps")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("rps")) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				rps(channel, substring, event);
				System.out.println("rock paper scissors");
			}
		} else if (message.contains("intel donate")) {
			// substring to get message
			String substring = message.substring(message.indexOf(" ", 6) + 1);
			// makes sure they formatted it correctly
			if (substring.contains("donate") || event.getMessage().getMentionedMembers().size() != 1) {
				channel.sendMessage("See `intel help` for formatting!").queue();
			} else {
				System.out.println("donate");
				donate(channel, event);
			}
		} else if (message.contains("intel kanye")) {
			addCashFromEvent(event, amountPerCommand);
			kanyequote(channel, event);
			System.out.println("kanye");
		} else if (message.contains("intel leaderboard")) {
			leaderboard(channel);
			System.out.println("leaderboard");
		} else if (message.contains("intel joke")) {
			addCashFromEvent(event, amountPerCommand);
			joke(channel, event);
			System.out.println("joke");
		} else if (message.contains("intel dog")) {
			addCashFromEvent(event, amountPerCommand);
			dog(channel, event);
			System.out.println("dog");
		} else if (message.contains("intel cat")) {
			addCashFromEvent(event, amountPerCommand);
			cat(channel, event);
			System.out.println("cat");
		} else if (message.contains("intel balance")) {
			getBalance(channel, event);
			System.out.println("getBalance");
		} else if (message.contains("intel help")) {
			help(channel);
			System.out.println("help");
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
			eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))); // 256 non-inclusive
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
			eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
			eb.setTitle("Weather ☁🌡");

			if (!weatherAPI.getResponseRaw().equals("Weather API Error")) {
				for (int i = 0; i < weatherAPI.getResponseArray().length; i++) {
					eb.addField("Hour " + (i + 1) * 3,
							"Temp Farenheit: " + (Integer.parseInt(weatherAPI.getResponseArray()[i][6]) * 1.8 + 32)
									+ "\nWeather: " + weatherAPI.getResponseArray()[i][5] + "\nPrecipitation: "
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
			eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
			eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
			eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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

		channel.sendMessage("Gathering Recon On " + message + "... 🔎").queue();

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

				// asks SeleniumBot class every second if it has the result yet
				do {
					pauseMilliseconds(10);
				} while (GeneralInputManager.userBots.get(inUseIndex).isDone() == false);

				try {
					channel.sendMessage("Your search for " + message + "...🔎");

					channel.sendFile(GeneralInputManager.userBots.get(inUseIndex).getFileFile(), "User Thread.jpg")
							.queue();

					EmbedBuilder eb = new EmbedBuilder();
					MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null,
							null);

					eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
					eb.setTitle("**" + message.toUpperCase() + "🔗🖱**",
							GeneralInputManager.userBots.get(inUseIndex).getCurrentURL());
					String temp = "";
					for (int i = 0; i < 12; i++) { // only top 12 links or blank spaces
						try {
							if (!GeneralInputManager.userBots.get(inUseIndex).getLinkTitles().get(i).equals("")) {
								temp += "[" + GeneralInputManager.userBots.get(inUseIndex).getLinkTitles().get(i) + "]("
										+ GeneralInputManager.userBots.get(inUseIndex).getLinkURLs().get(i) + ")\n\n";
							}
						} catch (Exception e) {
						}
					}
					GeneralInputManager.userBots.get(inUseIndex).clearLinks(); // necessarry to clear the links for next
																				// user
					eb.setDescription(temp);
					eb.setFooter("Powered By Google",
							"https://images.theconversation.com/files/93616/original/image-20150902-6700-t2axrz.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1000&fit=clip"); // url
																																										// to
																																										// google
																																										// logo
					eb3 = eb.build();

					channel.sendMessage(eb3).queue();

				} catch (Exception e) {
					EmbedBuilder eb = new EmbedBuilder();
					MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null,
							null);

					eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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

				eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				eb.setTitle(
						"Not Enough Capacity at the Moment! Wait a few seconds and try again or join our **Support Server** for help and questions!");
				eb3 = eb.build();

				channel.sendMessage(eb3).queue();
			}

		});
		t1.start();
	}

	private void minecraft(MessageChannel channel, String message, MessageReceivedEvent event) {
		minecraft.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		eb.setTitle(minecraft.getHostName() + " 🏦");
		// sets fields...
		if (!minecraft.getResponseRaw().equals("Minecraft API Error")) {
//			private String ip = "";
//			private int port = 0;
//			private String motd = "";
//			private int playersOnline = 0;
//			private int playersMax = 0;
//			private String version = "";
//			private boolean online = false;
//			private String hostName = "";

			eb.addField("IP", minecraft.getIp(), true);
			eb.addField("Port", minecraft.getPort() + "", true);
			eb.addField("MOTD", minecraft.getMOTD(), true);
			eb.addField("Players Online", minecraft.getPlayersOnline() + "", true);
			eb.addField("Players Max", minecraft.getPlayersMax() + "", true);
			eb.addField("Version", minecraft.getVersion(), true);
			eb.addField("Online", String.valueOf(minecraft.getOnlineStatus()), true);
			eb.addField("Host Name", minecraft.getHostName(), true);

			// end set fields...
		} else {
			eb.setDescription(minecraft.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By api.mcsrvstat.us");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void stonk(MessageChannel channel, String message, MessageReceivedEvent event) {
		stonks.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setTitle(message.toUpperCase() + " 📈");
		// sets fields...
		if (!stonks.getResponseRaw().equals("Stonk API Error")) {
			eb.addField("Current Price", "$" + stonks.getCurrentPrice() + "", true);
			eb.addField("Open Price", "$" + stonks.getOpenPrice() + "", true);
			eb.addField("High price", "$" + stonks.getHighPrice() + "", true);
			eb.addField("Low Price", "$" + stonks.getLowPrice() + "", true);
			eb.addField("Previous Close Price", "$" + stonks.getPreviousClosePrice() + "", true);

			if (stonks.getCurrentPrice() > stonks.getOpenPrice()) {
				// set to green up arrow
				eb.setColor(new Color(0, 255, 0));
				eb.setTitle(message.toUpperCase() + " 📈");
				eb.setThumbnail(
						"https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Solid_green.svg/1200px-Solid_green.svg.png");
			} else if (stonks.getCurrentPrice() < stonks.getOpenPrice()) {
				eb.setColor(new Color(255, 0, 0));
				eb.setTitle(message.toUpperCase() + " 📉");
				eb.setThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Red.svg/768px-Red.svg.png");
			}

			// end set fields...
		} else {
			eb.setDescription(stonks.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By Finnhub.io");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void detailStonk(MessageChannel channel, String message, MessageReceivedEvent event) {
		detailStonks.getStats(message);
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		eb.setTitle(message.toUpperCase() + " 📊");
		// sets fields...
		if (!detailStonks.getResponseRaw().equals("Stonk API Error")) {

			eb.addField("52 Week High", detailStonks.get52WeekHigh() + "", true);
			eb.addField("52 Week Low", detailStonks.get52WeekLow() + "", true);
			eb.addField("52 Week Price Return Daily", detailStonks.get52WeekPriceReturnDaily() + "", true);
			eb.addField("5 Day price Return Daily", detailStonks.get5DayPriceReturnDaily() + "", true);
			eb.addField("Dividend Yield Indicated Annual", detailStonks.getDividendYieldIndicatedAnnual() + "", true);
			eb.addField("Current Ev/Free Cash Flow Annual", detailStonks.getCurrentEvOverFreeCashFlowAnnual() + "",
					true);
			eb.addField("Eps Growth 3 Year", detailStonks.getEpsGrowth3Year() + "", true);
			eb.addField("Eps Growth 5 Year", detailStonks.getEpsGrowth5Year() + "", true);
			eb.addField("Eps Growth TTM Yoy", detailStonks.getEpsGrowthTTMYoy() + "", true);
			eb.addField("Market Cap in Millions", detailStonks.getMarketCapitalizationInMillions() + "", true);
			eb.addField("Operating Margin 5 Year", detailStonks.getOperatingMargin5Y() + "", true);
			eb.addField("Operating Margin Annual", detailStonks.getOperatingMarginAnnual() + "", true);
			eb.addField("Pe Basic Excluding Extra TTM", detailStonks.getPeBasicExclExtraTTM() + "", true);
			eb.addField("Price Relative to S&P 500 52 Week", detailStonks.getPriceRelativeToSandP50052Week() + "",
					true);
			eb.addField("Revenue Growth 3 Year", detailStonks.getRevenueGrowth3Y() + "", true);
			eb.addField("Revenue Growth 5 Year", detailStonks.getRevenueGrowth5Y() + "", true);
			eb.addField("Revenue Growth TTM Yoy", detailStonks.getRevenueGrowthTTMYoy() + "", true);

			// end set fields...
		} else {
			eb.setDescription(detailStonks.getResponseRaw() + " ☹");
		}
		eb.setFooter("Powered By Finnhub.io");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void rps(MessageChannel channel, String message, MessageReceivedEvent event) {

		System.out.println("rpssss " + message);

		int computerChoice = random.nextInt(3);

		// 0=rock, 1=paper, 2=scissors

		// ties
		if (message.equalsIgnoreCase("rock") && computerChoice == 0) {
			channel.sendMessage("Computer Chose Rock. Tie.").queue();
		} else if (message.equalsIgnoreCase("paper") && computerChoice == 1) {
			channel.sendMessage("Computer Chose Paper. Tie.").queue();
		} else if (message.equalsIgnoreCase("scissors") && computerChoice == 2) {
			channel.sendMessage("Computer Chose Scissors. Tie.").queue();
		}
		// wins
		else if (message.equalsIgnoreCase("rock") && computerChoice == 2) {
			channel.sendMessage("Computer Chose Scissors. You won " + amountRPS + " Dollar(s)!").queue();
			addCashFromEvent(event, amountRPS);
		} else if (message.equalsIgnoreCase("paper") && computerChoice == 0) {
			channel.sendMessage("Computer Chose Rock. You won " + amountRPS + " Dollar(s)!").queue();
			addCashFromEvent(event, amountRPS);
		} else if (message.equalsIgnoreCase("scissors") && computerChoice == 1) {
			channel.sendMessage("Computer Chose Paper. You won " + amountRPS + " Dollar(s)!").queue();
			addCashFromEvent(event, amountRPS);
		}
		// losses
		else if (message.equalsIgnoreCase("rock") && computerChoice == 1) {
			channel.sendMessage("Computer Chose Paper. You lost " + amountRPS + " Dollar(s)!").queue();
			addCashFromEvent(event, -amountRPS);
		} else if (message.equalsIgnoreCase("paper") && computerChoice == 2) {
			channel.sendMessage("Computer Chose Scissors. You lost " + amountRPS + " Dollar(s)!").queue();
			addCashFromEvent(event, -amountRPS);
		} else if (message.equalsIgnoreCase("scissors") && computerChoice == 0) {
			channel.sendMessage("Computer Chose Rock. You lost " + amountRPS + " Dollar(s)!").queue();
			addCashFromEvent(event, -amountRPS);
		}
	}

	private void getBalance(MessageChannel channel, MessageReceivedEvent event) {
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			if (event.getAuthor().getId().equals(GeneralInputManager.readWrite.getUsers().get(i).getId())) {
				EmbedBuilder eb = new EmbedBuilder();
				MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null,
						null);
				eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				eb.setTitle(event.getAuthor().getName() + "'s Balance");
				eb.setDescription("**Balance: **" + GeneralInputManager.readWrite.getUsers().get(i).getCash());// will
																												// need
																												// to
																												// have
																												// image
																												// as
																												// second
																												// parameter
																												// eventually
				eb3 = eb.build();
				channel.sendMessage(eb3).queue();
			}
		}
	}

	private void donate(MessageChannel channel, MessageReceivedEvent event) {
		String amount = "";
		String message = event.getMessage().getContentRaw();
		System.out.println(event.getMessage().getContentRaw());
		try {
			amount = message.substring(message.indexOf('$') + 1);
		} catch (Exception e) {
			channel.sendMessage("See `intel help` for formatting!").queue();
		}
		System.out.println("amount = " + amount);

		boolean donated = false;

		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			System.out.println(GeneralInputManager.readWrite.getUsers().get(i).getId() + "|||"
					+ event.getMessage().getMentionedMembers().get(0).getId());
			if (GeneralInputManager.readWrite.getUsers().get(i).getId()
					.equals(event.getMessage().getMentionedMembers().get(0).getId())) {
				try {
					// subtracts from author balance
					if (authorHasCash(event, Integer.parseInt(amount))) {
						subtractCashFromEvent(event, Integer.parseInt(amount));
						// adds to mentioned balance
						addCashTo(event.getMessage().getMentionedMembers().get(0), Integer.parseInt(amount));
						donated = true;
						// sends message
						EmbedBuilder eb = new EmbedBuilder();
						MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null,
								null, null);
						eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
						// sets fields...
						eb.setTitle("**Donated!** New Balances..");
						eb.addField(event.getAuthor().getName(), getUserCash(event.getAuthor().getId()), true);
						eb.addField(event.getMessage().getMentionedMembers().get(0).getEffectiveName(),
								getUserCash(event.getMessage().getMentionedMembers().get(0).getId()), true);
						eb.setFooter("Powered By Recon");
						eb3 = eb.build();
						channel.sendMessage(eb3).queue();
					}
				} catch (Exception e) {
					channel.sendMessage("See `intel help` for formatting!").queue();
					donated = true;
				}
			}
		}
		if (!donated) {
			EmbedBuilder eb = new EmbedBuilder();
			MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
			eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
			// sets fields...
			eb.setTitle("**Cannot Donate to Non-Users!**");
			eb.setFooter("Powered By Recon");
			eb3 = eb.build();
			channel.sendMessage(eb3).queue();
		}
	}

	private void leaderboard(MessageChannel channel) {
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<User> sortedUsers = new ArrayList<User>();
		// copy users over
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			users.add(GeneralInputManager.readWrite.getUsers().get(i));
		}
		// sort and take out highest each time and add to leaderbaord list
		while (users.size() != 0) {
			int userIndex = 0;
			for (int i = 0; i < users.size(); i++) {
				if (Integer.parseInt(users.get(i).getCash()) > Integer.parseInt(users.get(userIndex).getCash())) {
					userIndex = i;
				}
			}
			sortedUsers.add(users.get(userIndex));
			users.remove(userIndex);
		}
		// send sortedUsers in order with the cash values
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		eb.setTitle("Leaderboard📊");
		for (int i = 0; i < sortedUsers.size(); i++) {
			eb.addField(sortedUsers.get(i).getName(), "$" + sortedUsers.get(i).getCash(), true);
		}
		eb.setFooter("Powered by Recon");
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();

	}

	private void help(MessageChannel channel) {
		EmbedBuilder eb = new EmbedBuilder();
		MessageEmbed eb3 = new MessageEmbed("", "", "", null, null, 0, null, null, null, null, null, null, null);
		eb.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		eb.setTitle("📜Gathering Reconnaissance...📊");
		eb.addField("Google🔎🔍", "`intel google (anything)`", true);
		eb.addField("Covid Stats🦠📈", "`intel covid (us)`", true);
		eb.addField("Weather☁🌡", "`intel weather (long,lat)`", true);
		eb.addField("Name -> Age Predictions💭📊", "`intel name (name)`", true);
		eb.addField("Image📷🖼", "`intel image (ImageName)`", true);
		eb.addField("Longitude Latitude🌎🌐", "`intel lnglat (place)`", true);
		eb.addField("Place Information🌎🌐", "`intel placeinfo (place)`", true);
		eb.addField("Convert Currency💶💵", "`intel convertcurrency (convert from,convert to)`", true);
		eb.addField("IP Info💻🔗", "`intel ip (ip address)`", true);
		eb.addField("Kanye Quotes💭", "`intel kanye`", true);
		eb.addField("Uncreative Jokes😂🧺", "`intel joke`", true);
		eb.addField("Movie Recon🎥🎬", "`intel movie (movie title)`", true);
		eb.addField("Pokemon🃏🎴", "`intel pokemon (pokemon)`", true);
		eb.addField("Dog🐶🐕‍", "`intel dog`", true);
		eb.addField("Cat🐱🐈", "`intel cat`", true);
		eb.addField("Number🔢", "`intel number (number)`", true);
		eb.addField("Date🔢", "`intel date (mm/dd)`", true);
		eb.addField("Minecraft Server Info🏦", "`intel minecraft (server address)`", true);
		eb.addField("Stonks📊", "`intel stonk (stock symbol)`", true);
		eb.addField("Detailed Stonks📊", "`intel detailstonk (stock symbol)`", true);
		eb.addField("(Economy) Rock Paper Scissors✂", "`intel rps (rock/paper/scissors)`", true);
		eb.addField("(Economy) Balance💰", "`intel balance`", true);
		eb.addField("(Economy) Donate👬", "`intel donate (@User) ($amount)`", true);
		eb.addField("Ping🏓", "`intel ping`", true);
		eb.addField("Help🆘", "`intel help`", true);

		eb.setDescription(
				"**[🌟Invite Here🌟](https://discord.com/api/oauth2/authorize?client_id=779185137971494932&permissions=522304&scope=bot)** \n\n **Economy:** $ are earned by using"
						+ " commands! Each command (except economy and help commands) used gains you $1 💵. You can also gamble this money and donate it to other users. 💰 \n\n");
		eb.setFooter("Powered By Recon");// will need to have image as second parameter eventually
		eb3 = eb.build();
		channel.sendMessage(eb3).queue();
	}

	private void addCashFromEvent(MessageReceivedEvent event, int cash) {
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			if (GeneralInputManager.readWrite.getUsers().get(i).getId().equals(event.getAuthor().getId())) {
				GeneralInputManager.readWrite.getUsers().get(i).addCash(cash);
				GeneralInputManager.readWrite.updateUserJSON(GeneralInputManager.readWrite.getUsers().get(i));
			}
		}
	}

	private void subtractCashFromEvent(MessageReceivedEvent event, int cash) {
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			if (GeneralInputManager.readWrite.getUsers().get(i).getId().equals(event.getAuthor().getId())) {
				GeneralInputManager.readWrite.getUsers().get(i).subtractCash(cash);
				GeneralInputManager.readWrite.updateUserJSON(GeneralInputManager.readWrite.getUsers().get(i));
			}
		}
	}

	private void addCashTo(Member m, int cash) {
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			if (GeneralInputManager.readWrite.getUsers().get(i).getId().equals(m.getId())) {
				GeneralInputManager.readWrite.getUsers().get(i).addCash(cash);
				GeneralInputManager.readWrite.updateUserJSON(GeneralInputManager.readWrite.getUsers().get(i));
			}
		}

	}

	private void subtractCashFrom(Member m, int cash) {
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			if (GeneralInputManager.readWrite.getUsers().get(i).getId().equals(m.getId())) {
				GeneralInputManager.readWrite.getUsers().get(i).subtractCash(cash);
				GeneralInputManager.readWrite.updateUserJSON(GeneralInputManager.readWrite.getUsers().get(i));
			}
		}
	}

	private boolean authorHasCash(MessageReceivedEvent event, int cash) {
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			if (GeneralInputManager.readWrite.getUsers().get(i).getId().equals(event.getAuthor().getId())) {
				if (Integer.parseInt(GeneralInputManager.readWrite.getUsers().get(i).getCash()) >= cash) {
					return true;
				}
				return false;
			}
		}

		// should never be here..
		return false;
	}

	private String getUserCash(String id) {
		for (int i = 0; i < GeneralInputManager.readWrite.getUsers().size(); i++) {
			if (GeneralInputManager.readWrite.getUsers().get(i).getId().equals(id)) {
				return GeneralInputManager.readWrite.getUsers().get(i).getCash();
			}
		}
		return "";
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
