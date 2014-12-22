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
		/usr/bin/time -a -o 'outputShoppingCart.data' --format '%e' java ulb.ad.cassandra.performance.ShoppingCart.java M $N
		done
	done   
for N in 100 500 1000 10000 20000
	do
		for K in 'ALL' 'ONE' 'TWO' 'QUORUM'
		do
			for T in 1 2 3 4 5 
			do
				echo -n "Cassandra,$N,$K,$T,">>'outputShoppingCart.data'
				/usr/bin/time -a -o 'outputShoppingCart.data' --format '%e' java ulb.ad.cassandra.performance.ShoppingCart.java C $N $K
			done
		done		
	done   