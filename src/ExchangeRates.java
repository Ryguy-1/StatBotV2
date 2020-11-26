
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExchangeRates {

	private String response = "";
	private String convertTo;
	private String convertFrom;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private double exchangeRate;

	ExchangeRates(String convertFrom, String convertTo) {
		this.convertTo = convertTo;
		this.convertFrom = convertFrom;
		//getStats(name);
	}

	public void getStats(String convertFrom, String convertTo) {
		this.convertTo = convertTo;
		this.convertFrom = convertFrom;
		
		
		try {
			response = doGetRequest(convertFrom);
		} catch (IOException e) {
			response = "Exchange Rate API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}

	public double getExchangeRate() {
		return this.exchangeRate;
	}

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			JSONObject obj2 = obj.getJSONObject("rates");
			this.exchangeRate = obj2.getDouble(convertTo.toUpperCase());

		} catch (Exception e) {
			response = "Exchange Rate API Error";
			e.printStackTrace();
		}
	}

	// code request code here
	private String doGetRequest(String convertFrom) throws IOException {

		String url = "https://api.exchangerate-api.com/v4/latest/"+convertFrom.toUpperCase();
		
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