
public class User {
	private String cash;
	private String guildName;
	private String guildId;
	private String name;
	private String id;
	
	User(String cash, String guildName, String guildId, String name, String id){
		this.cash = cash;
		this.guildName = guildName;
		this.guildId = guildId;
		this.name = name;
		this.id = id;
	}
	
	public void setCash(String cash) {
		this.cash = cash;
	}
	
	//getters and setters
	public String getCash() {
		return this.cash;
	}
	
	public void addCash(int cash) {
		try {
			int tempCurrent = Integer.parseInt(this.cash);
			tempCurrent+=cash;
			if(tempCurrent<0) {
				tempCurrent=0;
			}
			this.cash = tempCurrent+"";
			System.out.println("cash now "+ this.cash);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void subtractCash(int cash) {
		try {
			int tempCurrent = Integer.parseInt(this.cash);
			tempCurrent-=cash;
			if(tempCurrent<0) {
				tempCurrent=0;
			}
			this.cash = tempCurrent+"";
			System.out.println("cash now "+ this.cash);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	
	public String getGuildName() {
		return this.guildName;
	}
	
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	
	public String getGuildId() {
		return this.guildId;
	}
	
//	
//	//update JSON
//	private void setJSON() {
//		//rewrite information here
//	}
//	
//	
//	//update Member Variables
//	private void updateInformationFromJSON() {
//		//loads JSON variables into variables
//	}
	
	
}
