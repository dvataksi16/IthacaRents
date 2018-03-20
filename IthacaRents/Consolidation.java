package IthacaRents;

public class Consolidation {
	
	String owner;
	String email;
	String phone;
	String area;
	String leaseStart; 
	String leaseLength;
	String address;
	
	String productIntro = "";
	String interactionNote = "";
	String fud = "";
	String sourceOfLead = "Ithacarents.com";
	String company = "";
	String buildings = "";
	int units = 0;
	String url = "";
	String notes; 
	String role = "";
	
	//ithacarents.com spreadsheet
	Consolidation(String owner, String email, String phone, String area, String leaseStart, String leaseLength, String address){
		this.owner = owner;
		this.email = email;
		this.phone = phone;
		this.area = area;
		this.leaseStart = leaseStart;
		this.leaseLength = leaseLength;
		this.address = address;
	}
	
}
