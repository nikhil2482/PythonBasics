/**
 * Execution: Tools to JUnit to execute all
 *
 * Description: Tests the methods in Body.java
**/
import org.junit.Test;
import static org.junit.Assert.*;

public class BodyTest {
    
    // Do not change this!
    // Use DELTA as the tolerance for your assertEquals statements.
    public static final double DELTA = 1e-4;
    
    /*
    * REQUIRED TESTS
    */
    
    /////////////////////////////////////
    // DISTANCE TESTS ///////////////////
    /////////////////////////////////////

    // Test for DistanceToX
    @Test
    public void testDistanceToX() {
        Body sun = new Body(1, 3, 4, 0, 0, "sun.gif");
        Body earth = new Body(1, 7, 7, 0, 0, "earth.gif");
        
        double actual = sun.distanceToX(earth);
        double expected = 4.0;
        
        assertEquals(expected, actual, DELTA);
    }


    // Test for DistanceToY
    @Test
    public void testDistanceToY() {
        Body sun = new Body(1, 3, 4, 0, 0, "sun.gif");
        Body earth = new Body(1, 7, 7, 0, 0, "earth.gif");
        
        double actual = sun.distanceToY(earth);
        double expected = 3.0;
        
        assertEquals(expected, actual, DELTA);
        
    }
    
    // Test for DistanceTo
    @Test
    public void testDistanceTo() {
        Body sun = new Body(1, 3, 4, 0, 0, "sun.gif");
        Body earth = new Body(1, 7, 7, 0, 0, "earth.gif");
        
        double actual = sun.distanceTo(earth);
        double expected = 5.0;
        
        assertEquals(expected, actual, DELTA); 
        
    }
    
    /////////////////////////////////////
    // FORCE TESTS //////////////////////
    /////////////////////////////////////
    
    // Test for Force
    @Test
    public void testForce() {
        Body mars = new Body(10, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(50, 7, 10, 0, 0, "mercury.gif");
        
        double actual = mars.force(mercury);
        double expected = 4.4672e-10;
        
        assertEquals(expected, actual, DELTA);
        
    }
    
    // Test for ForceX
    @Test
    public void testForceX() {
        Body mars = new Body(10, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(50, 7, 10, 0, 0, "mercury.gif");
        
        double actual = mars.forceX(mercury);
        double expected = 3.500e-10;
        // 3.500e-10
        
        assertEquals(expected, actual, DELTA);
        
    }
    
    // Test for ForceY
    @Test
    public void testForceY() {
        Body mars = new Body(10, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(50, 7, 10, 0, 0, "mercury.gif");
        
        double actual = mars.forceY(mercury);
        double expected = 4.200e-10;
        
        assertEquals(expected, actual, DELTA);
        
    }
    
    /////////////////////////////////////
    // VELOCITY TESTS ///////////////////
    /////////////////////////////////////
    
    // Test for Move
    @Test
    public void testMove() {
        Body mercury = new Body(5000, 7, 10, -3, 4, "mercury.gif");
        mercury.move(10);
        
        // Values in the x position
        double actualXValue = mercury.px;
        double expectedXValue = -23;
        
        // Values in the y position
        double actualYValue = mercury.py;
        double expectedYValue = 50;
        
        assertEquals(expectedXValue, actualXValue, DELTA);
        assertEquals(expectedYValue, actualYValue, DELTA);
        
    }
    
    // Test for GetAffectedBy
    @Test
    public void testGetAffectedBy() {
        Body mars = new Body(1000, 2, 4, 0, 0, "mars.gif");
        Body mercury = new Body(5000, 7, 10, -3, 4, "mercury.gif");
        
        mars.getAffectedBy(mercury, 100000.0);

        // Values in the Velocity X for mercury
        double actualMercuryX = mercury.vx;
        double expectedMercuryX = -3.0;

        // Values in the Velocity Y for mercury
        double actualMercuryY = mercury.vy;
        double expectedMercuryY = 4.0;
        
        // Values in the Velocity X for mars
        double actualMarsX = mars.vx;
        double expectedMarsX = 3.500024546e-4;
        
        // Values in the Velocity Y for mars
        double actualMarsY = mars.vy;
        double expectedMarsY = 4.200029455e-4;
        
        assertEquals(expectedMercuryX, actualMercuryX, DELTA);
        assertEquals(expectedMercuryY, actualMercuryY, DELTA);
        assertEquals(expectedMarsX, actualMarsX, DELTA);
        assertEquals(expectedMarsY, actualMarsY, DELTA);
        
    }
    
    /*
    * You should feel free to write more tests than the ones required.
    * Please keep them below this line.
    */
}
