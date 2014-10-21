package ulb.ad.cassandra.test;

import com.datastax.driver.core.*;

public class TestCassandra {

	public static void main(String[] args) {
		System.out.println("Hola Mundo GitHub");
		
		Cluster cluster;
		Session session;
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("demo");
	}

}
