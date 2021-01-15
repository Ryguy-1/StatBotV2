
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONReadWrite {

	private String resourceName = "C:\\Users\\Ryland Birchmeier\\Desktop\\StatBotV2\\src\\UserInfo.json";
	private ArrayList<User> users = new ArrayList<User>();

	JSONReadWrite() {

	}

	public void readAndSet() {
		// clear before set again
		users.clear();

		// Read from a file one line at a time
		String jsonString = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(resourceName));

			jsonString = br.readLine();
			while (br.readLine() != null) {
				jsonString += br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject obj = new JSONObject(jsonString);

		JSONArray arr1 = obj.getJSONArray("users");

		try {
			for (int i = 0; i < arr1.length(); i++) {
				addUserArrayList(new JSONObject(arr1.get(i).toString()).getLong("cash") + "",
						new JSONObject(arr1.get(i).toString()).getString("guildName"),
						new JSONObject(arr1.get(i).toString()).getLong("guildId") + "",
						new JSONObject(arr1.get(i).toString()).getString("name"),
						new JSONObject(arr1.get(i).toString()).getLong("id") + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("Users name is " + users.get(0).getName());

	}

	public boolean userIsInList(String userId) {

		for (int i = 0; i < users.size(); i++) {
			if (Long.parseLong(users.get(i).getId()) == Long.parseLong(userId)) {

				return true;
			}
		}
		return false;

	}

	private void addUserArrayList(String startingAmount, String guildName, String guildId, String name, String id) {
		users.add(new User(startingAmount, guildName, guildId, name, id));
	}

	public void addUserJSON(String startingAmount, String guildName, String guildId, String name, String id) {
		users.add(new User(startingAmount, guildName, guildId, name, id));

		// Read from a file one line at a time
		String jsonString = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(resourceName));

			jsonString = br.readLine();
			while (br.readLine() != null) {
				jsonString += br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject(jsonString);

		JSONArray arr1 = obj.getJSONArray("users");

		// Left off here confused
		JSONObject tempObject;
		try{
			tempObject = new JSONObject("{\"cash\":" + startingAmount + ", \"guildName\":" + guildName
				+ ", \"guildId\":" + guildId + ", \"name\":" + name + ", \"id\":" + id + "}");
		}catch(Exception e) {
			tempObject = new JSONObject("{\"cash\":" + startingAmount + ", \"guildName\":" + "Error Guild"
					+ ", \"guildId\":" + guildId + ", \"name\":" + "Error Name" + ", \"id\":" + id + "}");
		}
		
		
		

		arr1.put(tempObject);

		try {
			FileWriter fw = new FileWriter(resourceName);
			fw.write(obj.toString());

			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// updates JSON for a particular user(updates the JSON file from ram)
	public void updateUserJSON(User user) {

		// UPDATES ALL VALUES IN THE JSON FOR ONE USER OBJECT

		// Read from a file one line at a time
		String jsonString = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(resourceName));

			jsonString = br.readLine();
			while (br.readLine() != null) {
				jsonString += br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject(jsonString);

		JSONArray arr1 = obj.getJSONArray("users");

		for (int i = 0; i < arr1.length(); i++) {
			if (new JSONObject(arr1.get(i).toString()).getLong("id") == Long.parseLong(user.getId())) {
				arr1.remove(i);
				// now cycle through users instead of arr1 to find correct user
				for (int j = 0; j < users.size(); j++) {
					if (users.get(j).getId().equals(user.getId())) {
						arr1.put(new JSONObject("{\"cash\":" + users.get(j).getCash() + ", \"guildName\":"
								+ users.get(j).getGuildName() + ", \"guildId\":" + users.get(j).getGuildId()
								+ ", \"name\":" + users.get(j).getName() + ", \"id\":" + users.get(j).getId() + "}"));
					}
				}
				break;
			}
		}
		try {
			FileWriter fw = new FileWriter(resourceName);
			fw.write(obj.toString());

			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<User> getUsers() {
		return this.users;
	}

}
