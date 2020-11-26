
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IpInfo {

	private String response = "";
	private String ip;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
//    "city": "New York City",
//    "region": "New York",
//    "country": "US",
//    "loc": "40.7143,-74.0060",
//    "postal": "10004",
//    "timezone": "America/New_York",
	private String city;
	private String region;
	private String country;
	private String loc; //in lat,lng, not lng,lat -> Swap before return
	private String postal;
	private String timezone;

	IpInfo(String ip) {
		this.ip = ip;
		//getStats(place);
	}

	public void getStats(String ip) {
		this.ip = ip;
		
		try {
			response = doGetRequest(ip);
		} catch (IOException e) {
			response = "Ip Info API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	public String getCity() {
		return this.city;
	}
	
	public String getRegion() {
		return this.region;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getLoc() {
		return this.loc;
	}
	
	public String getPostal() {
		return this.postal;
	}
	
	public String getTimeZone() {
		return this.timezone;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
//		    "city": "New York City",
//		    "region": "New York",
//		    "country": "US",
//		    "loc": "40.7143,-74.0060",
//		    "postal": "10004",
//		    "timezone": "America/New_York",
			this.city = obj.getString("city");
			this.region = obj.getString("region");
			this.country = obj.getString("country");
			this.loc = obj.getString("loc");
			this.postal = obj.getString("postal");
			this.timezone = obj.getString("timezone");
			
		} catch (Exception e) {
			response = "Ip Info API Error";
		}
	}

	// code request code here
	private String doGetRequest(String ip) throws IOException {
		//https://ipinfo.io/161.185.160.93/geo
		String url = "https://ipinfo.io/"+ip+"/geo";

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