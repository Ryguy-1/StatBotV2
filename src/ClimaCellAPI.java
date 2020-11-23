
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClimaCellAPI {

	private String response = "";
	private int longitude = 0;
	private int latitude = 0;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	

	ClimaCellAPI(int longitude, int latitude) {
		getStats(longitude, latitude);
	}

	public void getStats(int longitude, int latitude) {

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

	

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response.substring(1, this.response.length() - 1); // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			
		} catch (Exception e) {
		}
	}

	// formats request URL
	private String getFormattedURL() {
		return "";
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