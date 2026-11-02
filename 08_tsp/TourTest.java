/**
 * Execution: JUnit to execute all
 *
 * Description: Tests for the Tour.java file 
**/
import org.junit.Test;
import static org.junit.Assert.*;

public class TourTest {
    
    // Do not change this!
    // Use DELTA as the tolerance for your assertEquals statements.
    public static final double DELTA = 1e-2;
    
    /*
    * REQUIRED TESTS
    */
    
    // toString tests
    @Test
    public void testToStringEmpty() {
        Tour t = new Tour();

        String actual = t.toString();
        String expected = ""; // should be blank if the string is empty 
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testToStringOnePoint() {
        Tour t = new Tour();

        Point p = new Point(0, 0);
        t.insertInOrder(p);
        String actual = t.toString();
        
        // This is provided for you
        String expected = "(0.0, 0.0)\n(0.0, 0.0)\n";
        assertEquals(expected, actual);

    }
    
    @Test
    public void testToStringSquare() {
        Tour t = new Tour();
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(0, 1);
        Point[] toInsert = {p0, p1, p2, p3};
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        
        String actual = t.toString();
        
        // This is provided for you
        String expected = 
            "(0.0, 0.0)\n(1.0, 0.0)\n(1.0, 1.0)\n(0.0, 1.0)\n(0.0, 0.0)\n";
        assertEquals(expected, actual);
    }
    
    // Distance tests
    @Test
    public void testDistanceEmpty() {
        Tour t = new Tour();
        double actual = t.distance();
        double expected = 0.0;
        assertEquals(expected, actual, DELTA);
    }
    
    @Test
    public void testDistanceSquare() {
        Tour t = new Tour();
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(0, 1);
        Point[] toInsert = {p0, p1, p2, p3};
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        
        double actual = t.distance();
        double expected = 4.0;
        assertEquals(expected, actual, DELTA);
    }
    
    // Size tests
    @Test
    public void testSizeEmpty() {
        Tour t = new Tour();
        int actual = t.size();
        int expected = 0;
        assertEquals(expected, actual);

    }
    
    @Test
    public void testSizeSquare() {
        Tour t = new Tour();
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(1, 1);
        Point[] toInsert = {p0, p1, p2, p3};
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }

        int actual = t.size();
        int expected = 4;
        assertEquals(expected, actual);
        
    }
    
    // insertInOrder tests
    @Test
    public void testInsertInOrderTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        
        int actual = t.size();
        int expected = 4;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInsertInOrderTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        /*
        * Study this test. It is completed for you.
        * We use two parallel arrays and a for loop to insert points a through e using
        * Tour.insertInOrder(). After each insertion, we check that the distance of the
        * tour was computed correctly. These are the points contained in the file
        * "tsp4.txt".
        */
        Point[] toInsert = { a, b, c, d };
        double[] distanceAfterInsert = { 0, 632.46, 832.46, 963.44 };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
            double expected = distanceAfterInsert[i];
            double actual = t.distance();
            // System.out.println(expected + " " + actual);
            assertEquals("mismatch after calling insertInOrder on point at index " + i,
            expected, actual, DELTA);
        }
    }
    
    // insertNearest tests
    @Test
    public void testInsertNearestTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = {a, b, c, d};

        for (int i = 0; i < toInsert.length; i++) {
            t.insertNearest(toInsert[i]);
        }
        
        int actual = t.size();
        int expected = 4;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInsertNearestTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = {a, b, c, d};
        double[] insertDistance = {0, 632.46, 832.46, 956.06};

        for (int i = 0; i < toInsert.length; i++) {
            t.insertNearest(toInsert[i]);
            double expected = insertDistance[i];
            double actual = t.distance();
            assertEquals("There's a mismatch at index" + i, expected, actual, DELTA);
        }

        
    }
    
    // insertSmallest tests
    @Test
    public void testInsertSmallestTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = {a, b, c, d};

        for (int i = 0; i < toInsert.length; i++) {
            t.insertSmallest(toInsert[i]);
        }
        int actual = t.size();
        int expected = 4;
        assertEquals(expected, actual);

    }
    
    @Test
    public void testInsertSmallestTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = {a, b, c, d};
        double[] insertDistance = {0, 632.46, 832.46, 839.83};

        for (int i = 0; i < toInsert.length; i++) {
            t.insertSmallest(toInsert[i]);
            double expected = insertDistance[i];
            double actual = t.distance();
            assertEquals("There's a mimatch at index" + i, expected, actual, DELTA);
        }
        
    }
    
}