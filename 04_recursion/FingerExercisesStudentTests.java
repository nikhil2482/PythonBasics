/**
* Execution: java tests for all 6 functions created in FingerExercises
*
* Program Description: This code is my own test cases for the FingerExercises
* 1, 2, 2.1, 4, 5, 6
*/

// Imports for tests
import static org.junit.Assert.*;
import org.junit.*;

public class FingerExercisesStudentTests {

   // Test for 1. Greatest Common Denominator
   @Test
   public void TestGCDTwoAndTwo() {
    int expected = 2; // expected value of 2
    int actual = FingerExercises.gcd(2, 2); // implement the function
    assertEquals("gcd of 2 and 2", expected, actual);
   }

   // Test for 2. Cumulative Sum
   @Test
   public void TestSumBetweenOneThree() {
    int expected = 6; // expected value of 6
    int actual = FingerExercises.sumBetween(1, 3); // implement the function
    assertEquals("sum between 1 and 3", expected, actual);
   }

   // Test for 2.1 Sum to
   @Test
   public void TestSumToThree() {
    int expected = 6; // expected value of 6
    int actual = FingerExercises.sumTo(3); // implement the function
    assertEquals("sum to 3", expected, actual);
   }

   // Test for 4. Sum of Digits
   @Test
   public void TestSumOfDigits13678() {
    int expected = 25; // expected value of 25
    int actual = FingerExercises.sumOfDigits(13678); // implement the function
    assertEquals("sum of digits 13678", expected, actual);
   }

   // Test for 5. Count Ways to Climb Stairs
   @Test
   public void TestClimbStairsTwo() {
    int expected = 2; // expected value of 2
    int actual = FingerExercises.countWaysToClimb(2); // implement the function
    assertEquals("ways to climb 2 stairs", expected, actual);
   }

   // Test for 6. Log
   @Test
   public void TestLogBaseThreeOfTwentySeven() {
    int expected = 3; // expected value of 3
    int actual = FingerExercises.log(3, 27); // implement the function
    assertEquals("log base 3 of 27", expected, actual);
   }

   // This is for compilation purposes
   @Test
   public void TestIgnoreThis() {
     assertTrue(true);
   }
}
