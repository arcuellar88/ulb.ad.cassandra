package ulb.ad.cassandra.test;

import com.datastax.driver.core.*;

public class TestCassandra {

	public static void main(String[] args) {
		System.out.println("Hola Mundo GitHub");
		
		Cluster cluster;
		Session session;
		cluster = Cluster.builder().addContactPoint("cimabue.ulb.ac.be").build();
		session = cluster.connect("testks");
		session.execute("INSERT INTO users (lname, age, city, email, fname,id) "
				+ "VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob',1020)");
	}

}
