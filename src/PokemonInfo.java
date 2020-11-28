
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

public class PokemonInfo {

	private String response = "";
	private String pokemonName;
	OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// response variables
	private int height;
	private int weight;
	private ArrayList<String> moves = new ArrayList<String>();
	private String pictureURL;

	PokemonInfo(String pokemonName) {
		this.pokemonName = pokemonName;
		//getStats(longitude, latitude);
	}

	public void getStats(String pokemonName) {

		try {
			response = doGetRequest(pokemonName);
		} catch (IOException e) {
			response = "Pokemon API Error";
			e.printStackTrace();
		}
		formatResponse();
		System.out.println(response);
	}

	public String getResponseRaw() {
		return this.response;
	}
	
	public ArrayList<String> getMoves(){
		return moves;
	}

	public void clearMoves() {
		moves.clear();
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWeight() {
		return this.weight;
	}

	public String getImage() {
		return this.pictureURL;
	}
	
	// gets response and puts the data in variables by parsing the JSON
	private void formatResponse() {
		try {
			String jsonString = this.response; // assign your JSON String here
			JSONObject obj = new JSONObject(jsonString);
			JSONArray moves = (JSONArray) obj.get("moves");
			this.height = obj.getInt("height");
			this.weight = obj.getInt("weight");
			
			for (int i = 0; i < moves.length(); i++) {
				JSONObject obj2 = (JSONObject) moves.get(i);
				JSONObject obj3 = obj2.getJSONObject("move");
				this.moves.add(obj3.getString("name"));
			}
			
			JSONObject obj2 = obj.getJSONObject("sprites");
			JSONObject obj3 = obj2.getJSONObject("other");
			JSONObject obj4 = obj3.getJSONObject("official-artwork");
			this.pictureURL = obj4.getString("front_default");

		} catch (Exception e) {
			response = "Pokemon API Error";
			//e.printStackTrace();
		}
	}

	// code request code here
	private String doGetRequest(String pokemonName) throws IOException {

		String url = "https://pokeapi.co/api/v2/pokemon/"+pokemonName.toLowerCase();

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