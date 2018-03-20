package IthacaRents;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.nodes.Document;  
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.csvreader.CsvWriter;

import org.jsoup.Jsoup;

public class Spider {
	//apartments and parking/storage
	
	public static void main(String[] args) throws IOException{
		
		Document doc1 = Jsoup.connect("http://ithacarents.com/Apartment-House").get();
		
		Elements apartments = doc1.select("div.grid_list_table div.item div.more_link > a");
		
		ArrayList<String> ithacaRentsListingUrls = new ArrayList<String>();
		ArrayList<Listing> listings =  new ArrayList<Listing>();
		
		for(Element apartment: apartments){
			ithacaRentsListingUrls.add(apartment.attr("abs:href"));
		}
		
		for(String ithacaRentsListingUrl: ithacaRentsListingUrls){
			Document listingDoc = Jsoup.connect(ithacaRentsListingUrl).get();
			Listing listing = new Listing(listingDoc.toString());
			listings.add(listing);
		}
		writeCsv(listings);
		
		
	}
	public static void writeCsv(ArrayList<Listing> listings){
		String csv = "IthacaRents.csv";
		CsvWriter writer = null;
		
		try{
			writer = new CsvWriter(new FileWriter(csv,true),',');
			writer.write("Owner"); writer.write("Email"); writer.write("Phone"); writer.write("Area");
			writer.write("Lease Start"); writer.write("Lease Length"); writer.write("Address");writer.endRecord();
			
			for(Listing listing: listings){
				writer.write(listing.owner);writer.write(listing.email);writer.write(listing.phone);writer.write(listing.area);
				writer.write(listing.leaseStart);writer.write(listing.leaseLength);writer.write(listing.address);
				writer.endRecord();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			writer.close();
			System.out.println("Csv Done");
		}
	}
}
