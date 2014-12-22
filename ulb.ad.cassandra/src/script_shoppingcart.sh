#!/bin/bash

# repeating the tests to get a reliable result

echo DB,N,consistency,numTest,time>'outputShoppingCart.data' #creating or overwriting file

#testing the 4 different implementations
      
		# testing with N from 1 to 1000000
for N in 100 500 1000 10000 20000
	do
		for T in 1 2 3 4 5 
		do
			echo -n "MYSQL,$N,,$T,">>'outputShoppingCart.data'
		/usr/bin/time -a -o 'outputShoppingCart.data' --format '%e' java -cp ":/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/mysql-connector-java-5.1.34-bin.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/cassandra-driver-core-2.0.2.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/bsh-2.0b4.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/guava-16.0.1.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/jcommander-1.27.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/log4j-1.2.17.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/lz4-1.2.0.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/metrics-core-3.0.2.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/netty-3.9.0.Final.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/slf4j-api-1.7.5.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/slf4j-log4j12-1.7.6.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/snappy-java-1.0.5.jar" ulb.ad.cassandra.performance.ShoppingCart.java M $N
		done
	done   
for N in 100 500 1000 10000 20000
	do
		for K in 'ALL' 'ONE' 'TWO' 'QUORUM'
		do
			for T in 1 2 3 4 5 
			do
				echo -n "Cassandra,$N,$K,$T,">>'outputShoppingCart.data'
				/usr/bin/time -a -o 'outputShoppingCart.data' --format '%e' java -cp ":/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/mysql-connector-java-5.1.34-bin.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/cassandra-driver-core-2.0.2.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/bsh-2.0b4.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/guava-16.0.1.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/jcommander-1.27.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/log4j-1.2.17.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/lz4-1.2.0.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/metrics-core-3.0.2.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/netty-3.9.0.Final.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/slf4j-api-1.7.5.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/slf4j-log4j12-1.7.6.jar:/root/ulb.ad.cassandra/ulb.ad.cassandra/lib/cassandra-java-driver-2.0.2/lib/snappy-java-1.0.5.jar" ulb.ad.cassandra.performance.ShoppingCart.java C $N $K
			done
		done		
	done   