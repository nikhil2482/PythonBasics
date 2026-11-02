/*
*
*  A class that represents a target to be hit in
*  Irate Avians. Can update its own position based
*  on velocity and time.
*/

public class Target {
    
    // variables for width and height of screen
    private double width, height;
    
    // Position and radius
    private double xPos, yPos, radius;
    
    // Velocity components
    private double xVel, yVel;
    
    /**
    * When a target's hit points reach zero,
    * it has been destroyed by the bird.
    */
    private int hitPoints;
    
    // Track if target has been hit this shot.
    private boolean hitThisShot;
    
    /**
    * Inputs: double width, height, xPos, yPos, radius, xVel, yVel, int hitPoints
    * Outputs: none (gets the data)
    * Description:Given a position, a radius, a velocity, and a number of hit points,
    * construct a Target.
    */
    public Target(double width, double height, double xPos, double yPos,
    double radius, double xVel, double yVel, int hitPoints) {
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.xVel = xVel;
        this.yVel = yVel;
        this.hitPoints = hitPoints;
        hitThisShot = false;
    }
    
    /**
    * Input: none
    * Output: void (draws something)
    * Description: Draw a circle centered at the target's position
    * with a radius equal to the target's radius.
    * Only draw a Target if it has more than zero
    * hit points.
    */
    int redChange = 255; // extra credit: red value that changes as target is hit
    public void draw() {
        if (hitPoints > 0) {
            PennDraw.setPenColor(redChange, 0, 0); // originally set to red
            PennDraw.filledCircle(xPos, yPos, radius);
            // Orange square inside of the circle 
            PennDraw.setPenColor(PennDraw.ORANGE);
            PennDraw.filledSquare(xPos, yPos, radius * 1 / 2);
            // Yellow sqaure inside of the circle that is even smaller
            PennDraw.setPenColor(PennDraw.YELLOW);
            PennDraw.filledSquare(xPos, yPos, radius * 1 / 4);
            // Number in the center that draws the hitPoints
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.text(xPos, yPos, Integer.toString(hitPoints));

        }
    }
    
    /**
    * Input: double timeStep
    * Output: void
    * Description: Given the change in time, update the target's
    * position based on its x and y velocity. When
    * a target is completely offscreen horizontally,
    * its position should wrap back around to the opposite
    * horizontal side. For example, if the target moves off the
    * right side of the screen, its xPos should be set to the
    * left side of the screen minus the target's radius.
    * The same logic should apply to the target's vertical
    * position with respect to the vertical screen boundaries.
    */
    public void update(double timeStep) {
        // x/y position is the original x/yPos plus the x/yVel * time
        xPos += xVel * timeStep;
        yPos += yVel * timeStep;

        // transport target to other side of the screen if it passes the boundaries
        // horizontally
        if (xPos + radius < 0.0) { // too far left
            xPos = width + radius;
        } else if (xPos - radius > width) { // too far right
            xPos = 0 - radius;
        }

        // transport target to the other side of the screen if it passes the
        // boundaries vertically
        if (yPos + radius < 0.0) { // too far low
            yPos = height + radius;
        } else if (yPos - radius > height) { // too far high
            yPos = 0 - radius;
        }
    }
    
    /**
     * Inputs: none
     * Outputs: void
     * Description: Decrement the target's hit points by 1
    */
    public void decreaseHP() {
        --hitPoints; // decrease hitPoints by 1
        // Extra credit part that changes the color the circle for every hit
        redChange -= 30;
        if (redChange < 0) {
            redChange = 255;
        }

    }
    
    /**
    * Input: boolean hit
    * Output: void
    * Description: Setter function for whether or not target hit this round.
    */
    public void setHitThisShot(boolean hit) {
        // Set hitThisShot equal to the boolean hit
        this.hitThisShot = hit;
    }
    
    /**
    * Input: none
    * Output: boolean
    * Description: Return whether or not this target is hit this round.
    */
    public boolean isHit() {
        // return hitThisShot which represents if the target was hit
        return hitThisShot;
    }
    
    /**
    * Input: none
    * Output: int hitPoints
    * Description: Getter functions that return a copy of the
    * indicated member variable.
    */
    public int getHitPoints() {
        return hitPoints;
    }
    
    /**
     * Inputs: none
     * Outputs: double xPos
     * Description: Getter function for the x position
    */
    public double getXpos() {
        return xPos;
    }
    
    /**
     * Inputs: none
     * Outputs: double yPos
     * Description: Getter function for the y position
    */
    public double getYpos() {
        return yPos;
    }
    
    /**
     * Inputs: none
     * Outputs: double radius
     * Description: Getter function for the radius
    */
    public double getRadius() {
        return radius;
    }
    
}
