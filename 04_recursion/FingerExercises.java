/**
* Execution: java FingerExercises gcd a b
*            java FingerExercises sumBetween a b
*            java FingerExercises sumTo x
*            java FingerExercises sumOfDigits x
*            java FingerExercises countWaysToClimb stairs
*            java FingerExercises log base n
*
* Program Description: This code will call one of the 6 given recursive finger 
* exercises and use recursive elements to spit out an ouput. Each finger exercises
* is different and takes in different inputs and ouputs.
*/

public class FingerExercises {
    // 1. Greated Common Denominator
    /*
    * Description: finds the greatest common Denominator between two different v
    *              values. Note that a and b are non negative integers and note that
    *              both a and b cannot equal 0 but one of the values can be 0
    * Input:  two integers a and b
    * Output: an integer that is the GCD
    */
    public static int gcd(int a, int b) {  
        // Base case: when a is less than b switch the variables, if a = b then b is
        // the answer, and if one of the values if 0 then return the other value.
        if (a < b) {
            return gcd(b, a);
        } else if (a == b) {
            return b;         
        } else if (b == 0) {
            return a;
        } else {
            // Recursive element: to get the mod of a and b and keep on doing so
            // similar to subtracting the largest value over and over by smaller int
            return gcd(b, a % b);
        } 
    }
    // 2. Cumulative Sum
    /*
    * Description: finds the sum between two different values a and b. 
    *              Note that a and b are included in the final answer and note that
    *              b must be larger than or equal to a for all inputs.
    * Input:  two integers a and b
    * Output: an integer that is the sum of the numbers between a and b
    */
    public static int sumBetween(int a, int b) {
        // Base case: if both numbers are equal to each other than return a
        if (a == b) {
            return a;
        } else {
            // The total sum is taking the sum of the b-1 subset and adding b to it
            return sumBetween(a, b - 1) + b;
        }
    }

    // 2.1 Sum to
    /*
    * Description: finds the sum from 1 to the given value x. Note that x must be 
    *              greater than or equal to 1.
    * Input:  an integer x
    * Output: an integer that is the sum from one to x
    */
    public static int sumTo(int x) {
        // Base case: if x is 1 then the sum must be 1
        if (x == 1) {
            return 1;
        } else {
            // Recursive Case: find the sum from 1 to x-1 then add x to it
            return sumTo(x - 1) + x;
        }
    }

    // 4. Sum of Digits
    /*
    * Description: finds the sum of the digits x. Note that x must be greater than
    *              or equal to 0.
    * Input:  an integer x
    * Output: an integer that is the sum of all the digits in x
    */
    public static int sumOfDigits(int x) {
        // Base case: if x equals 0 then the value is just x
        if (x == 0) {
            return x;
        } else {
            // Recursive Case: The value is the sum of digits divided 10 which gets
            // rid of the last digit in x and then add that digit with mod 10 to the
            // total value
            return sumOfDigits(x / 10) + (x % 10);
        }
    }

    // 5. Count Ways to Climb Stairs
    /*
    * Description: finds the number of ways to climb stairs either taking 1 or 2 
    *              steps at a time given the number of stairs to climb. Note that 
    *              stairs must be greater than or equal to 0 and if stairs = 0 then 
    *              return 1.
    * Input:  an integer stairs
    * Output: an integer that is the total number of ways to climb stairs
    */
    public static int countWaysToClimb(int stairs) {
        // Base case: if stairs equals 0 or 1 then the number of ways is 1
        if (stairs == 0 || stairs == 1) {
            return 1;
        } else {
            // Recursive case: the total value is the number of stairs to climb -1
            // plus the total number of stairs to climb -2. I noticed this pattern 
            // when drawing it out.
            return countWaysToClimb(stairs - 1) + countWaysToClimb(stairs - 2);
        }
    }

    // 6. Log
    /*
    * Description: finds the log given two different integers the base and n. Note
    *              that base must be less than or equal to n and both values are
    *              greater than 1. Also it should return the truncated answer.
    * Input:  two integers base and n
    * Output: an integer that is the log
    */
    public static int log(int base, int n) {
        // Base case: if base is less than n then the return must be 0
        if (base > n) {
            return 0;
        } else {
            // Recursive case: the log value is the log value of base and n divided
            // by base + 1 because logs are like division and how many times one
            // value can go into another value.
            return log(base, n / base) + 1;
        }
    }
}
