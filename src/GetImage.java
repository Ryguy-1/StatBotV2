
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetImage {

	private String response = "";
	private String imageName;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String imageURL;

	GetImage(String imageName) {
		this.imageName = imageName;
		//getStats(imageName);
	}

	public void getStats(String imageName) {

		try {
			response = doGetRequest(imageName);
		} catch (IOException e) {
			response = "Image API Error";
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
			JSONObject obj = new JSONObject(jsonString);
			JSONArray results = (JSONArray) obj.get("results");
			String temp = results.get(0).toString();
			JSONObject objTemp = new JSONObject(temp);
			JSONObject objTemp2 = objTemp.getJSONObject("urls");
			this.imageURL=objTemp2.getString("regular");

		} catch (Exception e) {
			response = "Image API Error";
		}
	}

	// code request code here
	private String doGetRequest(String imageName) throws IOException {

		String url = "https://api.unsplash.com/search/photos?query="+imageName;

		Request request = new Request.Builder().url(url).header("Authorization", "Client-ID IxyJqBE-TTv97tL-jRzfDjPfx3EGfMMTjhHRAOZK6WM").build();
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