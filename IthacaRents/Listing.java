package IthacaRents;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Listing {

	String owner = ""; 
	String email = "";
	String phone = "";
	String area = "";
	String leaseStart = "";
	String leaseLength = "";
	String address = "";
	
	Listing(){};
	Listing(String html){
		Document doc = Jsoup.parse(html);
		Elements labels = doc.select("table > tbody > tr > td:nth-child(1) > div.add_info > table > tbody > tr > td.label");
		Elements values = doc.select("table > tbody > tr > td:nth-child(1) > div.add_info > table > tbody > tr > td.value");
		
		for(int td = 0; td<labels.size(); td++){
			String label = labels.get(td).text();
			String value = values.get(td).text();
			
			switch(label){
				case "Owner": 
					owner = value; break;
				case "Email": 
					email = value; break;
				case "Phone":
					phone = value; break;
				case "Area": 
					area = value; break;
				case "Lease Start": 
					leaseStart = value; break;
				case "Lease Length": 
					leaseLength = value; break;
				case "Address": 
					address = value; break;
			}
		}
		System.out.println("Listing added");

	}
	
}
