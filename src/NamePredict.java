
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NamePredict {

	private String response = "";
	private String name;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private int predictedAge;
	private int numPeopleWithName;

	NamePredict(String name) {
		this.name = name;
		getStats(name);
	}

	public void getStats(String name) {

		try {
			response = doGetRequest(name);
		} catch (IOException e) {
			response = "Name API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	public int getPredictedAge() {
		return this.predictedAge;
	}

	public int getNumPeopleWithName() {
		return this.numPeopleWithName;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			predictedAge = obj.getInt("age");
			numPeopleWithName = obj.getInt("count");

		} catch (Exception e) {
			response = "Name API Error";
		}
	}

	// code request code here
	private String doGetRequest(String name) throws IOException {

		String url = "https://api.agify.io?name=" + name;

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