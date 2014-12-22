package ulb.ad.cassandra.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ulb.ad.cassandra.msc.Log;

public class DBConnection 
{
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public DBConnection()
	{
		
	}
	
	public void connect(String host, String user, String pwd, String db)
	{
		try
		{
		 // this will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // setup the connection with the DB.
	      connect = DriverManager.getConnection("jdbc:mysql://"+host+"/"+db,user,pwd);
	      Log.println("CONNECTED");
		}
	    catch (Exception e)
		{ 
			e.printStackTrace();
		}
	}
	
	public void exportCassandraShoppingCart(PrintWriter pw)
	{
		// statements allow to issue SQL queries to the database
	      try 
	      {
			statement = connect.createStatement();
			// resultSet gets the result of the SQL query
		    resultSet = statement.executeQuery(readQuery("./data/loadDataFromMysql.sql"));
		    while(resultSet.next())
		    {
		  		    	pw.println("insert into shopping_cart (username, cart_name,item_id,item_name,description,price,item_detail) VALUES ("
		    			+"'"+resultSet.getString("username")+"',"
		    			+"'"+resultSet.getString("cart_name")+"',"
		    			+resultSet.getInt("item_id")+","
		    			+"'"+resultSet.getString("item_name")+"',"
		    			+"'"+resultSet.getString("description")+"',"
		    			+resultSet.getDouble("price")+","
		    			+"{'Category':'"+resultSet.getString("category")+"',"
		    			+"'category_tree':'"+resultSet.getString("category_tree")+"',"
		    			+"'brand':'"+resultSet.getString("brand")+"'});");
		    }
		    
	      }
	      catch (SQLException e) 
	      {
			e.printStackTrace();
		  }
	      
	      
	}
	
	/**
	 * Reads a query from a file
	 * @param fileName
	 * @return
	 */
	public String readQuery(String fileName)
	{
		String query="";
		File f = new File(fileName);

		try 
		{
			FileInputStream in = new FileInputStream(f);
			 BufferedReader d= new BufferedReader(new InputStreamReader(in));
			while(d.ready())
			{
				query+=" "+d.readLine();
			}
			d.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return query;
	}
	
	public void getShoppingCart(String query, int customerid)
	{
		// statements allow to issue SQL queries to the database
	      try 
	      {
	    	  preparedStatement= connect.prepareStatement(query);
	    	  preparedStatement.setInt(1, customerid);
	    	  preparedStatement.executeQuery();
			/* resultSet gets the result of the SQL query
		    while(resultSet.next())
		    {
		    }
		    */
		    
	      }
	      catch (SQLException e) 
	      {
			e.printStackTrace();
		  }
	      
	      
	}

	public static void main(String[] args)
	{
		DBConnection db = new DBConnection();
		db.connect("localhost","root","","live");
		PrintWriter pw;
		try {
			pw = new PrintWriter(new File("./data/loadShoppingCartCassandra.txt"));
			db.exportCassandraShoppingCart(pw);
			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void close() {
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
