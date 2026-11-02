/**
 * Execution: Tools to JUnit to execute all
 *
 * Description: Contains the tests for the Space.java file
**/
import org.junit.Test;
import static org.junit.Assert.*;

public class SpaceTest {
    
    /*
    * REQUIRED TESTS
    */
    
    // Test for SpaceConstructorFields
    @Test
    public void testSpaceConstructorFields() {
        Space s = new Space("binary.txt");
        
        // Check the length of bodies
        double actualLength = s.bodies.length;
        double expectedLength = 2;
        
        // Check the radius
        double actualRadius = s.radius;
        double expectedRadius = 5.00e+10;
        
        assertEquals(actualLength, expectedLength, 1e6);
        assertEquals(actualRadius, expectedRadius, 1e6);
        
    }
    
    // Test SpaceConstructorBodies
    @Test
    public void testSpaceConstructorBodies() {
        Space s = new Space("binary.txt");
        
        // Position in x direction
        double actualPosition = s.bodies[0].px;
        double expectedPosition = 0.0e00;
        
        // Velocity in Y direction
        double actualVelocity = s.bodies[1].vy;
        double expectedVelocity = -1.0e04;
        
        assertEquals(actualPosition, expectedPosition, 1e6);
        assertEquals(actualVelocity, expectedVelocity, 1e6);
        
    }
    
    // Test SpaceSimulate
    @Test
    public void testSpaceSimulate() {
        Space s = new Space("solarSystem.txt");
        s.simulate(25000.0);
        
        // Position in the X direction
        double actualPositionX = s.bodies[0].px;
        double expectedPositionX = 1.49596e+11;

        // Position in the Y Direction
        double actualPositionY = s.bodies[0].py;
        double expectedPositionY = 7.45000e+08;
        
        // Velocity in the X direciton
        double actualVelocityX = s.bodies[0].vx;
        double expectedVelocityX = -1.48201e+02;
        
        // Velocity in the Y direciton
        double actualVelocityY = s.bodies[0].vy;
        double expectedVelocityY = 2.98000e+04;
        
        assertEquals(actualPositionX, expectedPositionX, 1e6);
        assertEquals(actualPositionY, expectedPositionY, 1e6);
        assertEquals(actualVelocityX, expectedVelocityY, 1e6);
        assertEquals(actualVelocityY, expectedVelocityY, 1e6);
        
    }
    
    /*
    * You should feel free to write more tests than the ones required.
    * Please keep them below this line.
    */
}
