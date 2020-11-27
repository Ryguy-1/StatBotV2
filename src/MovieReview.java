
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MovieReview {

	private String response = "";
	private String movieTitle;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String displayTitle;
	private String rating;
	private String summary;
	private String openingDate;
	
	

	MovieReview(String movieTitle) {
		this.movieTitle = movieTitle;
		//getStats(place);
	}

	public void getStats(String movieTitle) {
		this.movieTitle = movieTitle;
		
		try {
			response = doGetRequest(movieTitle);
		} catch (IOException e) {
			response = "Movie API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}
	
	public String getTitle() {
		return this.displayTitle;
	}
	
	public String getRating() {
		return this.rating;
	}
	
	public String getSummary() {
		return this.summary;
	}
	
	public String getOpeningDate() {
		return this.openingDate;
	}


	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			JSONArray array = obj.getJSONArray("results");
			JSONObject obj2 = (JSONObject) array.get(0);
			try {this.displayTitle = obj2.getString("display_title");}catch(Exception e){}
			try {this.rating = obj2.getString("mpaa_rating");}catch(Exception e){}
			try {this.summary = obj2.getString("summary_short");}catch(Exception e){}
			try {this.openingDate = obj2.getString("opening_date");}catch(Exception e){}
			
			
		} catch (Exception e) {
			response = "Movie API Error";
			e.printStackTrace();
		}
	}

	// code request code here
	private String doGetRequest(String movieTitle) throws IOException {
		//https://ipinfo.io/161.185.160.93/geo
		String url = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?query="+movieTitle+"&api-key=6AIbpeepLyKf2f4SRaOhtgYiLFM65ViG";

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