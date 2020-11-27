
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Jokes {

	private String response = "";
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String setup;
	private String punchline;

	Jokes() {
		
	}

	public void getStats() {
		
		try {
			response = doGetRequest();
		} catch (IOException e) {
			response = "Joke API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	public String getSetup() {
		return this.setup;
	}
	
	public String getPunchline() {
		return this.punchline;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject("{ responses: "+jsonString+"}");
			JSONArray array = obj.getJSONArray("responses");
			String temp = array.get(0).toString();
			JSONObject obj2 = new JSONObject(temp);
			this.setup = obj2.getString("setup");
			this.punchline = obj2.getString("punchline");
			
		} catch (Exception e) {
			response = "Joke API Error";
			e.printStackTrace();
		}
	}

	// code request code here
	private String doGetRequest() throws IOException {
		//https://ipinfo.io/161.185.160.93/geo
		String url = "https://official-joke-api.appspot.com/jokes/ten";

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