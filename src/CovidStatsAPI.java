
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.*;

public class CovidStatsAPI {

	private String response = "";
	private String location = "";
	private String[][] states = { { "Alabama", "al" }, { "Alaska", "ak" }, { "Arizona", "az" }, { "Arkansas", "ar" },
			{ "California", "ca" }, { "Colorado", "co" }, { "Connecticut", "ct" }, { "Delaware", "de" },
			{ "Florida", "fl" }, { "Georgia", "ga" }, { "Hawaii", "hi" }, { "Idaho", "id" }, { "Illinois", "il" },
			{ "Indiana", "in" }, { "Iowa", "ia" }, { "Kansas", "ks" }, { "Kentucky", "ky" }, { "Louisiana", "la" },
			{ "Maine", "me" }, { "Maryland", "md" }, { "Massachusetts", "ma" }, { "Michigan", "mi" },
			{ "Minnesota", "mn" }, { "Mississippi", "ms" }, { "Missouri", "mo" }, { "Montana", "mt" },
			{ "Nebraska", "ne" }, { "Nevada", "nv" }, { "New Hampshire", "nh" }, { "New Jersey", "nj" },
			{ "New Mexico", "nm" }, { "New York", "ny" }, { "North Carolina", "nc" }, { "North Dakota", "nd" },
			{ "Ohio", "oh" }, { "Oklahoma", "ok" }, { "Oregon", "or" }, { "Pennsylvania", "pa" },
			{ "Rhode Island", "ri" }, { "South Carolina", "sc" }, { "South Dakota", "sd" }, { "Tennessee", "tn" },
			{ "Texas", "tx" }, { "Utah", "ut" }, { "Vermont", "vt" }, { "Virginia", "va" }, { "Washington", "wa" },
			{ "West Virginia", "wv" }, { "Wisconsin", "wi" }, { "Wyoming", "wy" } };
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private int positive = 0;
	private int negative = 0;
	private int date = 0;
	private int pending = 0;
	private int hospitalizedCurrently = 0;
	private int inIcuCurrently = 0;
	private int inIcuCumulative = 0;
	private int onVentilatorCurrently = 0;
	private int onVentilatorCumulative = 0;
	private int recovered = 0;
	private String dateChecked = "";
	private int death = 0;
	private int totalTestResults = 0;
	private int deathIncrease = 0;
	private int hospitalizedIncrease = 0;
	private int negativeIncrease = 0;
	private int positiveIncrease = 0;
	private int totalTestResultsIncrease = 0;

	CovidStatsAPI(String location) {
		//getStats(location);
	}

	public void getStats(String location) {
		this.location = location;
		try {
			// "https://api.covidtracking.com/v1/us/current.json"
			if (!getFormattedURL().equals("bad request")) {
				response = doGetRequest(getFormattedURL());
			} else {
				response = "API only supports US right now, not individual states or countries ☹.";
			}
		} catch (IOException e) {
			response = "Covid API Response Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	public int positive() {
		return this.positive;
	}

	public int negative() {
		return this.negative;
	}

	public int date() {
		return this.date;
	}

	public int pending() {
		return this.pending;
	}

	public int hospitalizedCurrently() {
		return this.hospitalizedCurrently;
	}

	public int inIcuCurrently() {
		return this.inIcuCurrently;
	}

	public int inIcuCumulative() {
		return this.inIcuCumulative;
	}

	public int onVentilatorCurrently() {
		return this.onVentilatorCurrently;
	}

	public int onVentilatorCumulative() {
		return this.onVentilatorCumulative;
	}

	public int recovered() {
		return this.recovered;
	}

	public String dateChecked() {
		return this.dateChecked;
	}

	public int death() {
		return this.death;
	}

	public int totalTestResults() {
		return this.totalTestResults;
	}

	public int deathIncrease() {
		return this.deathIncrease;
	}

	public int hospitalizedIncrease() {
		return this.hospitalizedIncrease;
	}

	public int negativeIncrease() {
		return this.negativeIncrease;
	}

	public int positiveIncrease() {
		return this.positiveIncrease;
	}

	public int totalTestResultsIncrease() {
		return this.totalTestResultsIncrease;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response.substring(1, this.response.length() - 1); // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			positive = obj.getInt("positive");
			negative = obj.getInt("negative");
			date = obj.getInt("date");
			pending = obj.getInt("pending");
			hospitalizedCurrently = obj.getInt("hospitalizedCurrently");
			inIcuCurrently = obj.getInt("inIcuCurrently");
			inIcuCumulative = obj.getInt("inIcuCumulative");
			onVentilatorCurrently = obj.getInt("onVentilatorCurrently");
			onVentilatorCumulative = obj.getInt("onVentilatorCumulative");
			recovered = obj.getInt("recovered");
			dateChecked = obj.getString("dateChecked");
			death = obj.getInt("death");
			totalTestResults = obj.getInt("totalTestResults");
			deathIncrease = obj.getInt("deathIncrease");
			hospitalizedIncrease = obj.getInt("hospitalizedIncrease");
			negativeIncrease = obj.getInt("negativeIncrease");
			positiveIncrease = obj.getInt("positiveIncrease");
			totalTestResultsIncrease = obj.getInt("totalTestResultsIncrease");
		} catch (Exception e) {
		}
	}

	// formats request URL
	private String getFormattedURL() {
		// checks to see if it is first just the whole United States
		if (location.equalsIgnoreCase("us") || location.equalsIgnoreCase("UnitedStates")
				|| location.equalsIgnoreCase("UnitedStatesofAmerica")) {
			return "https://api.covidtracking.com/v1/us/current.json";
		}
		// if not goes through state list and acronyms to check for a match -> API DOES
		// NOT SUPPORT INDIVIDUAL STATE STATS AT THIS POINT... THIS CODE WILL WORK WHEN
		// IT DOES THOUGH.
//		for (int i = 0; i < states.length; i++) {
//			for (int j = 0; j < states[i].length; j++) {
//				if(location.equalsIgnoreCase(states[i][j])) {
//					//the second index of each array in the 2d array is the lowercase abbreviation which is what the API wants
//					return "https://api.covidtracking.com/v1/states/"+states[i][1]+"/info.json";
//				}
//			}
//		}

		return "bad request";
	}

	// code request code here
	private String doGetRequest(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	// post requests handled here -> Not used for this API
	private String doPostRequest(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
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