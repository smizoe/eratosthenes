package org.LambdaKai
import scala.collection.mutable.PriorityQueue  
object Eratosthenes{
    // we have three sequences of primes where each sequence is obtained
    // with different algorithms.
    val numbers :Stream[Int] = Stream.from(2)
    
	lazy val unfaithfulPrimes = unfaithfulSieve(numbers)
	
	// we can't put LHS = numbers filter isPrime; this falls into an infinite loop
	lazy val trialDivisionPrimes = 2 #:: (numbers.tail filter isPrime)
	
	lazy val primesWithPriorityQ = sieve(numbers)
	
	lazy val primesWithWheel : Stream[Int] = 2 #:: 3 #:: 5 #:: 7 #:: sieve(spin (wheel2357,11)) 
  
	def unfaithfulSieve(candidates : Stream[Int]) : Stream[Int] =
		candidates.head #:: unfaithfulSieve(candidates.tail.filter (x=> x % candidates.head != 0)) 
    
	
	def isPrime(n: Int): Boolean =
		(trialDivisionPrimes takeWhile (p => p * p <= n )) forall (p => n % p != 0)
	
	// Priority queue returns a max element by head method; 
	// since we would like to find a element with smallest n,
	// we construct such a ordering.
	case class Node(mul :Int, multiples : Stream[Int]) extends Ordered[Node]{
      def compare(that: Node) = {
    	 val first = (this.mul, this.multiples.head) 
    	 val second = (that.mul, that.multiples.head)
    	 if(first._1 == second._1){
    	   second._2 - first._2 
    	 }else{
    	   second._1 - first._1 
    	 }
      	}
     }
    
	def sieve(candidates : Stream[Int]) : Stream[Int] = candidates match{
	  
	  case	x #:: xs =>
	    
	    def insertNode(prime : Int, numbers: Stream[Int],table :PriorityQueue[Node]): Unit ={
	      table.enqueue(Node(prime*prime,numbers map (n => n*prime)))
	    }
	    
	    def sieve2 (candidate2: Stream[Int], table: PriorityQueue[Node]) :Stream[Int] = candidate2 match{
	    	
	    	case candidate #:: rest =>
	    	  
	    	   
	    	   if(table.head.mul <= candidate){
	    	     def adjustTable(toBeDeleted :Int): Unit = table.head match{
	    			case Node(m1 ,m2 #:: ms) =>
	    	    		if(m1 <= toBeDeleted){
	    	    			table.dequeue
	    	    			table.enqueue(Node(m2,ms))
	    	    			adjustTable(toBeDeleted)
	    	    		}
	    			case (_) => new Error("A stream of multiples comes to an end.")
	    	      }
	    	     
	    	     
	    	     if(table.nonEmpty){
	    			   adjustTable(candidate)
	    		  }
	    	     sieve2(rest,table)
	    	   }else{
	    	     insertNode(candidate,rest,table)
	    	     candidate #:: sieve2(rest ,table)
	    	   }
	    	
	    	   
	    	
	    	case _ => candidate2
	    }
	    val PQ = new PriorityQueue[Node]()
	    insertNode(x,xs,PQ)
	    x #:: sieve2(xs,PQ)
	  case _ => candidates
	}
	
	// this wheel has 48 holes; this number is equivalent to the number of non-multiples of
	// 2, 3, 5 and 7 in the range from 1 to 210 (LCM of 2,3,5,7)
	lazy val wheel2357 : Stream[Int] = (2 #:: 4 #:: 2 #:: 4 #:: 6 #:: 2 #:: 6 #:: 4 #:: 2 #:: 4 #:: 6 #:: 6 #:: 2 #:: 6 #:: 4 #:: 2 #::6 #:: 4 #::6 #:: 8 #:: 4 #:: 2 #:: 4 #:: 2 #:: 4 #:: 8 #::
		6 #:: 4 #:: 6 #:: 2 #:: 4 #:: 6 #:: 2 #:: 6 #:: 6 #:: 4 #:: 2 #:: 4 #:: 6 #:: 2 #:: 6 #:: 4 #:: 2 #:: 4 #:: 2 #:: 10 #:: 2 #:: 10 #:: wheel2357)
	
	def spin(s : Stream[Int], n : Int) : Stream[Int] = {
	  n #:: spin(s.tail, (s.head + n))
	}
}
