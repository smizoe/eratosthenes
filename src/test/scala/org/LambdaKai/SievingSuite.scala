package org.LambdaKai.ertatosthenes
import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SievingSuite extends FunSuite {
	import org.LambdaKai.Eratosthenes._
	val firstTenPrimes = 2 :: 3 :: 5 :: 7 :: 11 :: 13 :: 17 :: 19 :: 23 ::29 :: Nil
	test("unfaithful sieve test"){
		assert((unfaithfulPrimes take 10) === firstTenPrimes, "first ten primes (unfaithful)")
	}
	
	test("trial division test"){
		assert((trialDivisionPrimes take 10) === firstTenPrimes, "first ten primes (trial div.)")
	}
	
	test("priority queue test"){
	  assert((primesWithPriorityQ take 10)=== firstTenPrimes , "first ten primes (PQ)")
	}
	
	test("wheel test"){
	  assert((primesWithWheel take 10) === firstTenPrimes, "first ten primes (wheel)")
	}
	
	test("Identity of the sequences"){
	  assert((primesWithPriorityQ take 1000).toList=== (unfaithfulPrimes take 1000).toList, "unfaithful == PQ")
	  assert((primesWithPriorityQ take 1000).toList=== (trialDivisionPrimes take 1000).toList, "trial division == PQ")
	  assert((primesWithPriorityQ take 1000).toList === (primesWithWheel take 1000).toList, "PQ == wheel")
	}
	
	test("implicit conversion"){
	  assert(Node(1,Stream.from(1)) > (Node(1,Stream.from(2))),"First condition.")
	}
}
