package ulb.ad.cassandra.performance;

import ulb.ad.cassandra.impl.DBCassandra;
import ulb.ad.cassandra.mysql.DBConnection;

public class ShoppingCart 
{

	private DBConnection mysql;
	private DBCassandra cassandra;
	private LoadCustomers load;
	
	private String query;
	
	public ShoppingCart()
	{
		mysql= new DBConnection();
		cassandra = new DBCassandra();
		load= new LoadCustomers();
	}
	public static void printMainArgsError() {
		System.out
				.println("Please add the required parameters:\n"
						+ "- C or M (for Cassandra or MYSQL)\n"
						+ "- number of shopping cart queries\n"
						+ "- Concistency level\n"
						+ "Example: java ShippingCart C 1000");
	}
	
	private void runCassandraTest(int numQ, String consistency) 
	{
		query=mysql.readQuery("./data/getShoppingCartCassandra.cql");

		cassandra.connect("164.15.78.17","advancedb");
		for (int i = 0; i < numQ; i++) 
		{
			cassandra.getShoppingCart(query, load.readNext(),consistency );
		}
		cassandra.close();
	}

	private void runMYSQLTest(int numQ) 
	{
		query=mysql.readQuery("./data/getShoppingCartMYSQL.sql");
		mysql.connect("164.15.78.17","advancedb","advancedb","live");
		
		for (int i = 0; i < numQ; i++) 
		{
			mysql.getShoppingCart(query, load.readNext());
		}
		mysql.close();
	}
	public static void main(String[] args)
	{
		if(args.length>=2)
		{
		 ShoppingCart sc= new ShoppingCart();
		 if(args[0].equals("M"))
			{
			 sc.runMYSQLTest(Integer.parseInt(args[1]));
			}
		 else if(args[0].equals("C"))
		 {
			 if(args.length==3)
			 sc.runCassandraTest(Integer.parseInt(args[1]),args[2]);
			 else
				 sc.runCassandraTest(Integer.parseInt(args[1]),"ONE");
	
				 
		 }
		}
		else
		{
			printMainArgsError();
		}
		
	}

}
