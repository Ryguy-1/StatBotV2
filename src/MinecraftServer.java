
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MinecraftServer {

	private String response = "";
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private String ip = "";
	private int port = 0;
	private String motd = "";
	private int playersOnline = 0;
	private int playersMax = 0;
	private String version = "";
	private boolean online = false;
	private String hostName = "";

	MinecraftServer() {
		
	}

	public void getStats(String serverAddress) {
		
		try {
			response = doGetRequest(serverAddress);
		} catch (IOException e) {
			response = "Minecraft API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}
///////////////////////
	public String getIp() {
		return this.ip;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public String getMOTD() {
		return this.motd;
	}
	
	public int getPlayersOnline() {
		return this.playersOnline;
	}
	
	public int getPlayersMax() {
		return this.playersMax;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public boolean getOnlineStatus() {
		return this.online;
	}
	
	public String getHostName() {
		return this.hostName;
	}
///////////////////////	
	

	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			this.ip = obj.getString("ip");
			this.port = obj.getInt("port");
			JSONObject obj2 = obj.getJSONObject("motd");
			JSONArray arr = obj2.getJSONArray("clean");
			this.motd = arr.get(1).toString();
			JSONObject obj3 = obj.getJSONObject("players");
			this.playersOnline = obj3.getInt("online");
			this.playersMax = obj3.getInt("max");
			this.version = obj.getString("version");
			this.online = obj.getBoolean("online");
			this.hostName = obj.getString("hostname");
			
			
		} catch (Exception e) {
			response = "Minecraft API Error";
		}
	}

	// code request code here
	private String doGetRequest(String serverAddress) throws IOException {
		//https://ipinfo.io/161.185.160.93/geo
		String url = "https://api.mcsrvstat.us/2/" + serverAddress;

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