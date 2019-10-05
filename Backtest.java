import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.*;

class Backtest
{
	public static void main(String [] args)
	{
		String inline = "";
	
		try
		{
			URL url = new URL("https://api.nasa.gov/neo/rest/v1/feed?start_date=2019-09-01&api_key=BzB2orclxfvtyCtkKwX3PVr5xgdTQKSjo0JYQeVv");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			System.out.println("Response code is: " +responsecode);
			
			//Iterating condition to if response code is not 200 then throw a runtime exception
			//else continue the actual process of getting the JSON data
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
				//Scanner functionality will read the JSON data from the stream
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					inline+=sc.nextLine();
				}
				//System.out.println("\nJSON Response in String format"); 
				//System.out.println(inline);
				//Close the stream when reading the data has been finished
				sc.close();
			}
			
			
			JSONParser parse = new JSONParser();
			JSONObject jobj = (JSONObject)parse.parse(inline);
			System.out.println(jobj.keySet());
			jobj = (JSONObject) jobj.get("near_earth_objects");
			//This doesn't seem to be working for some reason. If we are able to fix this issue then we can procced to using the data.
			
	
	}
}
