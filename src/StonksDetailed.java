
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StonksDetailed {

	private String otherInfoResponse = "";
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private double ftWeekHigh = 0;
	private double ftWeekLow = 0;
	private double ftWeekPriceReturnDaily = 0;
	private double fiveDayPriceReturnDaily = 0;
	private double dividendYieldIndicatedAnnual = 0;
	private double currentEvOverFreeCashFlowAnnual = 0;
	private double epsGrowth3Y = 0;
	private double epsGrowth5Y = 0;
	private double epsGrowthTTMYoy = 0;
	private double marketCapitalizationInMillions = 0;
	private double operatingMargin5Y = 0;
	private double operatingMarginAnnual = 0;
	private double peBasicExclExtraTTM = 0;
	private double priceRelativeToSandP50052Week = 0;
	private double revenueGrowth3Y = 0;
	private double revenueGrowth5Y = 0;
	private double revenueGrowthTTMYoy = 0;

	// more response variables

	StonksDetailed() {

	}

	public void getStats(String stockName) {

		try {
			otherInfoResponse = getInfo(stockName);
		} catch (IOException e) {
			otherInfoResponse = "Stonk API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(otherInfoResponse);
	}

	// returns Price Response
	public String getResponseRaw() {
		return this.otherInfoResponse;
	}

///////////////////////
	public double get52WeekHigh() {
		return this.ftWeekHigh;
	}

	public double get52WeekLow() {
		return this.ftWeekLow;
	}

	public double get52WeekPriceReturnDaily() {
		return this.ftWeekPriceReturnDaily;
	}

	public double get5DayPriceReturnDaily() {
		return this.fiveDayPriceReturnDaily;
	}

	public double getDividendYieldIndicatedAnnual() {
		return this.dividendYieldIndicatedAnnual;
	}

	public double getCurrentEvOverFreeCashFlowAnnual() {
		return this.currentEvOverFreeCashFlowAnnual;
	}

	public double getEpsGrowth3Year() {
		return this.epsGrowth3Y;
	}

	public double getEpsGrowth5Year() {
		return this.epsGrowth5Y;
	}

	public double getEpsGrowthTTMYoy() {
		return this.epsGrowthTTMYoy;
	}

	public double getMarketCapitalizationInMillions() {
		return this.marketCapitalizationInMillions;
	}

	public double getOperatingMargin5Y() {
		return this.operatingMargin5Y;
	}

	public double getOperatingMarginAnnual() {
		return this.operatingMarginAnnual;
	}

	public double getPeBasicExclExtraTTM() {
		return this.peBasicExclExtraTTM;
	}

	public double getPriceRelativeToSandP50052Week() {
		return this.priceRelativeToSandP50052Week;
	}

	public double getRevenueGrowth3Y() {
		return this.revenueGrowth3Y;
	}

	public double getRevenueGrowth5Y() {
		return this.revenueGrowth5Y;
	}

	public double getRevenueGrowthTTMYoy() {
		return this.revenueGrowthTTMYoy;
	}

///////////////////////	

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		// first responses
		try {
			String jsonString = this.otherInfoResponse; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			JSONObject obj2 = obj.getJSONObject("metric");
			try {
				this.ftWeekHigh = obj2.getDouble("52WeekHigh");
			} catch (Exception e) {
				this.ftWeekHigh = 0;
			}
			try {
				this.ftWeekLow = obj2.getDouble("52WeekLow");
			} catch (Exception e) {
				this.ftWeekLow = 0;
			}
			try {
				this.ftWeekPriceReturnDaily = obj2.getDouble("52WeekPriceReturnDaily");
			} catch (Exception e) {
				this.ftWeekPriceReturnDaily = 0;
			}
			try {
				this.fiveDayPriceReturnDaily = obj2.getDouble("5DayPriceReturnDaily");
			} catch (Exception e) {
				this.fiveDayPriceReturnDaily = 0;
			}
			try {
				this.dividendYieldIndicatedAnnual = obj2.getDouble("dividendYieldIndicatedAnnual");
			} catch (Exception e) {
				this.dividendYieldIndicatedAnnual = 0;
			}
			try {
				this.currentEvOverFreeCashFlowAnnual = obj2.getDouble("currentEv/freeCashFlowAnnual");
			} catch (Exception e) {
				this.currentEvOverFreeCashFlowAnnual = 0;
			}
			try {
				this.epsGrowth3Y = obj2.getDouble("epsGrowth3Y");
			} catch (Exception e) {
				this.epsGrowth3Y = 0;
			}
			try {
				this.epsGrowth5Y = obj2.getDouble("epsGrowth5Y");
			} catch (Exception e) {
				this.epsGrowth5Y = 0;
			}
			try {
				this.epsGrowthTTMYoy = obj2.getDouble("epsGrowthTTMYoy");
			} catch (Exception e) {
				this.epsGrowthTTMYoy = 0;
			}
			// if doesn't have market cap won't show any information here... This is to show
			// you when you've entered a wrong ticker
			this.marketCapitalizationInMillions = obj2.getDouble("marketCapitalization");
			try {
				this.operatingMargin5Y = obj2.getDouble("operatingMargin5Y");
			} catch (Exception e) {
				this.operatingMargin5Y = 0;
			}
			try {
				this.operatingMarginAnnual = obj2.getDouble("operatingMarginAnnual");
			} catch (Exception e) {
				this.operatingMarginAnnual = 0;
			}
			try {
				this.peBasicExclExtraTTM = obj2.getDouble("peBasicExclExtraTTM");
			} catch (Exception e) {
				this.peBasicExclExtraTTM = 0;
			}
			try {
				this.priceRelativeToSandP50052Week = obj2.getDouble("priceRelativeToS&P50052Week");
			} catch (Exception e) {
				this.priceRelativeToSandP50052Week = 0;
			}
			try {
				this.revenueGrowth3Y = obj2.getDouble("revenueGrowth3Y");
			} catch (Exception e) {
				this.revenueGrowth3Y = 0;
			}
			try {
				this.revenueGrowth5Y = obj2.getDouble("revenueGrowth5Y");
			} catch (Exception e) {
				this.revenueGrowth5Y = 0;
			}
			try {
				this.revenueGrowthTTMYoy = obj2.getDouble("revenueGrowthTTMYoy");
			} catch (Exception e) {
				this.revenueGrowthTTMYoy = 0;
			}

		} catch (Exception e) {
			otherInfoResponse = "Stonk API Error";
		}
		// second responses

	}

	// code request code here
	private String getInfo(String stockName) throws IOException {
		// https://ipinfo.io/161.185.160.93/geo
		String url = "https://finnhub.io/api/v1/stock/metric?symbol=" + stockName.toUpperCase()
				+ "&metric=all&token=bv4m3e748v6qpate5vhg";
		try {
			Request request = new Request.Builder().url(url).build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			return null;
		}
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