package IthacaRents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.csvreader.CsvWriter;

public class Consolidate{
	//Product Intro?	Interaction Note	F.U.D	Source of Lead	Company	Location(s)	Buildings	Units	URL	Notes	Contact Name	Role	Number	Email
	
	
	//Owner	Email	Phone	Area	Lease Start	Lease Length	Address
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Consolidation> consolidations = new ArrayList<Consolidation>();
		
		try{
			Connection con = null;
	        Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection("jdbc:sqlite:/Users/dvataksi/Desktop/Colleges.db");
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery("Select * from IthacaRentscom");
	        while(rs.next()){
	        	String owner = rs.getString("Owner");
	        	String email = rs.getString("Email");
	        	String phone = rs.getString("Phone");
	        	String area = rs.getString("Area");
	        	String leaseStart = rs.getString("LeaseStart");
	        	String leaseLength = rs.getString("LeaseLength");
	        	String address = rs.getString("Address");
	        	
	        	Consolidation consolidation = new Consolidation(owner, email, phone, area, leaseStart, leaseLength, address);
				consolidations.add(consolidation);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}	
		writeCsv(consolidations);
	}
	public static void writeCsv(ArrayList<Consolidation> consolidations){
		String csv = "/Users/dvataksi/Desktop/IthacaRentsConsolidation.csv";
		CsvWriter writer = null;
		
		try{
			writer = new CsvWriter(new FileWriter(csv,true),',');
			for(Consolidation consolidation: consolidations){
				writer.write(consolidation.productIntro);writer.write(consolidation.interactionNote);writer.write(consolidation.fud);
				writer.write(consolidation.sourceOfLead);writer.write(consolidation.company);writer.write(consolidation.area);writer.write(consolidation.buildings);;
				writer.write(consolidation.units + "");writer.write(consolidation.url); writer.write(consolidation.notes); writer.write(consolidation.owner);
				writer.write(consolidation.role); writer.write(consolidation.phone);writer.write(consolidation.email);
				writer.endRecord();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			writer.close();
			System.out.println("Ithacarents.com Consolidation done!");
		}
	}

}
