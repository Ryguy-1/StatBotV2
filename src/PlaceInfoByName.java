
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PlaceInfoByName {

	private String response = "";
	private String place;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String currency;
	private String roadSide;
	private String speedIn;
	private String timeZone;
	private int callingCode;
	private String lnglat;

	PlaceInfoByName(String place) {
		this.place = place;
		//getStats(place);
	}

	public void getStats(String place) {

		try {
			response = doGetRequest(place);
		} catch (IOException e) {
			response = "Place Info API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	public String getCurrency() {
		return this.currency;
	}

	public String getRoadSide() {
		return this.roadSide;
	}

	public String getSpeedIn() {
		return this.speedIn;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public String getlnglat() {
		return this.lnglat;
	}

	public int getCallingCode() {
		return this.callingCode;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			// results array -> bounds object -> northeast object
			JSONArray results = (JSONArray) obj.get("results");
			String temp = results.get(0).toString();
			JSONObject objTemp = new JSONObject(temp);
			JSONObject objTemp2 = objTemp.getJSONObject("annotations");
			this.callingCode = objTemp2.getInt("callingcode");
			JSONObject objTemp3 = objTemp2.getJSONObject("currency");
			this.currency = objTemp3.getString("name");

			JSONObject objTemp4 = objTemp2.getJSONObject("roadinfo");
			this.roadSide = objTemp4.getString("drive_on");
			this.speedIn = objTemp4.getString("speed_in");

			JSONObject objTemp5 = objTemp2.getJSONObject("timezone");
			this.timeZone = objTemp5.getString("short_name");
			
			
			double lng;
			double lat;
			JSONObject objTemp7 = objTemp.getJSONObject("bounds");
			JSONObject objTemp6 = objTemp7.getJSONObject("northeast");
			lng=objTemp6.getDouble("lng");
			lat=objTemp6.getDouble("lat");
			
			this.lnglat = lng+","+lat;

		} catch (Exception e) {
			response = "Place Info API Error";
		}
	}

	// code request code here
	private String doGetRequest(String place) throws IOException {

		String url = "https://api.opencagedata.com/geocode/v1/json?q=" + place
				+ "&key=c0ff9885bd0c479ca5e82803d88dda44";

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