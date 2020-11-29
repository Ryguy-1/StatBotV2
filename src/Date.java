
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Date {

	
	private String date = "";
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String response = "";

	Date(String date) {
		this.date = date;
		//getStats(longitude, latitude);
	}

	public void getStats(String date) {

		try {
			response = doGetRequest(date);
		} catch (IOException e) {
			response = "Date API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	private void formatResponse() {
		if(response.contains("<!DOCTYPE html>")) {
			response = "Number API Error";
		}
	}
	
	// code request code here
	private String doGetRequest(String date) throws IOException {

		String url = "http://numbersapi.com/"+date+"/date";

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