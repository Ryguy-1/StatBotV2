
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WeatherAPI {

	private String response = "";
	private double longitude = 0;
	private double latitude = 0;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String[][] responses = new String[8][7];

	WeatherAPI(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		//getStats(longitude, latitude);
	}

	public void getStats(double longitude, double latitude) {

		try {
			response = doGetRequest(longitude, latitude);
		} catch (IOException e) {
			response = "Weather API Error";
			e.printStackTrace();
		}
		formatResponse();
	}

	public String getResponseRaw() {
		return this.response;
	}
	
	public String[][] getResponseArray(){
		return this.responses;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			JSONArray dataseries = (JSONArray) obj.get("dataseries");
			
			for (int i = 0; i < 8; i++) {
				String temp = dataseries.get(i).toString();
				JSONObject objTemp = new JSONObject(temp);
				// index 0 = timestamp
				responses[i][0] = objTemp.getInt("timepoint") + "";
				// index 1 = prec_type
				responses[i][1] = objTemp.getString("prec_type");
				// index 2 = wind direction
				JSONObject objTemp2 = objTemp.getJSONObject("wind10m");
				responses[i][2] = objTemp2.getString("direction");
				// index 3 = wind speed
				int windSpeedTemp = objTemp2.getInt("speed");
				switch (windSpeedTemp) {
				case 1:
					responses[i][3] = "Below 0.3m/s (calm)";
					break;
				case 2:
					responses[i][3] = "0.3-3.4m/s (light)";
					break;
				case 3:
					responses[i][3] = "3.4-8.0m/s (moderate)";
					break;
				case 4:
					responses[i][3] = "8.0-10.8m/s (fresh)";
					break;
				case 5:
					responses[i][3] = "10.8-17.2m/s (strong)";
					break;
				case 6:
					responses[i][3] = "17.2-24.5m/s (gale)";
					break;
				case 7:
					responses[i][3] = "24.5-32.6m/s (storm)";
					break;
				case 8:
					responses[i][3] = "Over 32.6m/s (hurricane)";
					break;
				}
				// index 4 = prec_amount
				switch (objTemp.getInt("prec_amount")) {
				case 0:
					responses[i][4] = "None";
					break;
				case 1:
					responses[i][4] = "0-0.25mm/hr";
					break;
				case 2:
					responses[i][4] = "0.25-1mm/hr";
					break;
				case 3:
					responses[i][4] = "1-4mm/hr";
					break;
				case 4:
					responses[i][4] = "4-10mm/hr";
					break;
				case 5:
					responses[i][4] = "10-16mm/hr";
					break;
				case 6:
					responses[i][4] = "16-30mm/hr";
					break;
				case 7:
					responses[i][4] = "30-50mm/hr";
					break;
				case 8:
					responses[i][4] = "50-75mm/hr";
					break;
				case 9:
					responses[i][4] = "Over 75mm/hr";
					break;

				}
				// index 5 = weather
				responses[i][5] = objTemp.getString("weather");
				//index 6 = temperature celcius
				responses[i][6] = objTemp.getInt("temp2m")+"";
			}

		} catch (Exception e) {
			e.printStackTrace();
			response = "Weather API Error";
		}
	}

	// code request code here
	private String doGetRequest(double lon, double lat) throws IOException {

		String url = "http://www.7timer.info/bin/api.pl?lon=" + lon + "&lat=" + lat + "+&product=civil&output=json";

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