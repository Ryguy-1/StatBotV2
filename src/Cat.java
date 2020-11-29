
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Cat {

	private String response = "";
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String imageURL;

	Cat() {
		
	}

	public void getStats() {
		
		try {
			response = doGetRequest();
		} catch (IOException e) {
			response = "Cat API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	public String getImageURL() {
		return this.imageURL;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString.substring(1, jsonString.length()-1));
			this.imageURL = obj.getString("url");
			
		} catch (Exception e) {
			response = "Cat API Error";
			e.printStackTrace();
		}
	}

	// code request code here
	private String doGetRequest() throws IOException {
		//https://ipinfo.io/161.185.160.93/geo
		String url = "https://api.thecatapi.com/v1/images/search";

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