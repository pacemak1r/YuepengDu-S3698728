package helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String line = "";  
		String splitBy = ",";  
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
		BufferedReader br = new BufferedReader(new FileReader("src/artist.csv"));  
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
		String[] artist = line.split(splitBy);    // use comma as separator  
		System.out.println("Person Name=" + artist[0] + ", Birth Date=" + artist[1] + ", BirthPlace=" + artist[2] +
				", Death Date=" + artist[3] + ", Field= " + artist[4] + ", Genre= " + artist[5] + 
				", Instrument= " + artist[6] + ", Nationality= " + artist[7] + 
				", Thumbnail= " + artist[8] + ", wikiPageID= " + artist[9] + ", Description= " + artist[10] + "]");  
		}  
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();  
		}
	}
	

}
