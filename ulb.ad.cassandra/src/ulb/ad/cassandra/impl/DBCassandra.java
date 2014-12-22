package ulb.ad.cassandra.impl;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
public class DBCassandra {

	 private Cluster cluster;
	 private Session session;
	
	public void connect(String node, String keyspace) 
	{
		   cluster = Cluster.builder().addContactPoint(node).build();
		   
		   Metadata metadata = cluster.getMetadata();
		   System.out.println("Connected to cluster: %s\n"+
		         metadata.getClusterName());
		   for ( Host host : metadata.getAllHosts() ) 
		   {
		      System.out.println("Datacenter: %s; Host: %s; Rack: %s\n"+
		         host.getDatacenter()+ host.getAddress()+ host.getRack());
		   }
		   
		   session = cluster.connect(keyspace);
			
	}
	 
	
	public void close() {
	      cluster.close(); 
	  }
	
	

	public void getShoppingCart(String query, int readNext, String consistency)
	{		
		PreparedStatement ps=session.prepare(query);
		
		ps.setConsistencyLevel(ConsistencyLevel.valueOf(consistency));
		BoundStatement boundStatement = new BoundStatement(ps);
		
		session.execute( boundStatement.bind(""+readNext));
	}

	public static void main(String[] args) {
		System.out.println("Hola Mundo GitHub");
		DBCassandra tc=new DBCassandra();
		tc.connect("localhost","advancedb");
		tc.getShoppingCart("select * from shopping_cart where username=? and cart_name='Wish List'", 91125, "ONE");
		tc.close();
		
	}

}
