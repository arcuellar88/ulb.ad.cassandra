package ulb.ad.cassandra.performance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoadCustomers {

	/**
	 * File for reading
	 */
	private File f;
	/**
	 * Inputstream to read file
	 */
	private FileInputStream in;

	/**
	 * DataInputStream for reading
	 */
	private BufferedReader d;
	
	public LoadCustomers()
	{
		f = new File("./data/users.txt");
		try
		{
		 in = new FileInputStream(f);
		  d= new BufferedReader(new InputStreamReader(in));
		  //Read Header
		  d.readLine();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int readNext()
	{
		int num=-1;
		try
		{
			if(d.ready())
			{
				String line=d.readLine().trim();
				num= Integer.parseInt(line);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return num;
	}
	
	
	
	
}
