package org.LambdaKai
import org.LambdaKai.Eratosthenes._
object Performance {

  def main(args: Array[String]): Unit = {
    def time[A](a: => A)={
      val now = System.nanoTime()
      val result = a
      val micros = (System.nanoTime() - now)
      println("%d microseconds".format(micros))
      result
    }
    
    println("50000 primes")
    print("unfaithful: ")
    time(unfaithfulPrimes take 50000)
    print("TD: ")
    time(trialDivisionPrimes take 50000)
    print("PQ: ")
    time(primesWithPriorityQ take 50000)
    print("wheel: ")
    time(primesWithWheel take 50000)
    
    println("100000 primes")
    print("unfaithful: ")
    time(unfaithfulPrimes take 100000)
    print("TD: ")
    time(trialDivisionPrimes take 100000)
    print("PQ: ")
    time(primesWithPriorityQ take 100000)
    print("wheel: ")
    time(primesWithWheel take 100000)
    
    println("500000 primes")
    print("unfaithful: ")
    time(unfaithfulPrimes take 500000)
    print("TD: ")
    time(trialDivisionPrimes take 500000)
    print("PQ: ")
    time(primesWithPriorityQ take 500000)
    print("wheel: ")
    time(primesWithWheel take 500000)
  }

}