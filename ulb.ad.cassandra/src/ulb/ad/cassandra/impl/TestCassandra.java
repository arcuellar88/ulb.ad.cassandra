package ulb.ad.cassandra.impl;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
public class TestCassandra {

	 private Cluster cluster;
	
	public void connect(String node) 
	{
		   cluster = Cluster.builder().addContactPoint(node).build();
		   
		   
		   
		   Metadata metadata = cluster.getMetadata();
		   System.out.println("Connected to cluster: %s\n"+
		         metadata.getClusterName());
		   for ( Host host : metadata.getAllHosts() ) {
		      System.out.println("Datacenter: %s; Host: %s; Rack: %s\n"+
		         host.getDatacenter()+ host.getAddress()+ host.getRack());
		   }
	}
	 
	public void close() {
	      cluster.close(); 
	  }
	
	
	public static void main(String[] args) {
		System.out.println("Hola Mundo GitHub");
		TestCassandra tc=new TestCassandra();
		tc.connect("localhost");
		/*
		 * 
		 
		Cluster cluster;
		Session session;
		cluster = Cluster.builder().addContactPoint("cimabue.ulb.ac.be").build();
		
		session = cluster.connect("testks");
		session.execute("INSERT INTO users (lname, age, city, email, fname,id) "
				+ "VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob',1020)");
		
		System.out.println("Inser end");
		 */
	}

}
