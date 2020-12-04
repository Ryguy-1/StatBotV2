
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Stonks {

	private String priceResponse = "";
	private String otherInfoResponse = "";
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private double openPrice = 0;
	private double highPrice = 0;
	private double lowPrice = 0;
	private double currentPrice = 0;
	private double previousClosePrice = 0;
	
	//more response variables

	Stonks() {
		
	}

	public void getStats(String stockName) {
		
		try {
			priceResponse = getPrice(stockName);
			//otherInfoResponse = getInfo(stockName);
		} catch (IOException e) {
			priceResponse = "Stonk API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(priceResponse);
	}
	//returns Price Response
	public String getResponseRaw() {
		return this.priceResponse;
	}
///////////////////////
	public double getOpenPrice() {
		return this.openPrice;
	}
	
	public double getHighPrice() {
		return this.highPrice;
	}
	
	public double getLowPrice() {
		return this.lowPrice;
	}
	
	public double getCurrentPrice() {
		return this.currentPrice;
	}
	
	public double getPreviousClosePrice() {
		return this.previousClosePrice;
	}
///////////////////////	
	

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		//first responses
		try {
			String jsonString = this.priceResponse; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			this.openPrice = obj.getDouble("o");
			this.highPrice = obj.getDouble("h");
			this.lowPrice = obj.getDouble("l");
			this.currentPrice = obj.getDouble("c");
			this.previousClosePrice = obj.getDouble("pc");
			
			if(openPrice == 0) {
				priceResponse = "Stonk API Error";
			}
			
		} catch (Exception e) {
			priceResponse = "Stonk API Error";
		}
		//second responses
		
		
		
		
		
	}

	// code request code here
	private String getPrice(String stockName) throws IOException {
		//https://ipinfo.io/161.185.160.93/geo
		String url = "https://finnhub.io/api/v1/quote?symbol="+stockName.toUpperCase()+"&token=bv4m3e748v6qpate5vhg";

		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	private String getInfo(String stockName) throws IOException {
		//https://ipinfo.io/161.185.160.93/geo
		String url = "https://finnhub.io/api/v1/stock/metric?symbol="+stockName.toUpperCase()+"&metric=all&token=bv4m3e748v6qpate5vhg";

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