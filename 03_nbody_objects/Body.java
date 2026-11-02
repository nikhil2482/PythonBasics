/**
* Execution: Cannot execute Body by itself
*
* Description: Create methods to perform varius tasks including finding the new
* position and velocity doubles and calculating forces.
**/
public class Body {
    
    /**
    * DO NOT EDIT ANY CODE BELOW THIS LINE
    *
    * You will get a style warning that reads:
    * "Variable '[some name]' must be private and have get/set methods."
    * Ignore any style warnings of this form. Our tests rely on these
    * variables being public. We will discuss public vs. private next week
    * in class. You are not expected to know it now.
    */
    public double px, py; //position
    public double vx, vy; //velocity
    public double m; //mass
    public String img; //image file
    
    public static final double G = 6.67e-11; //gravity constant
    
    /*DO NOT EDIT ANY CODE ABOVE THIS LINE*/
    
    /**
    * Constructor: This creates a new instance of a body object.
    */
    public Body(double mass, double posX, double posY,
    double velX, double velY, String imageFile) {
        // Define each varaible as it relates to the inputs
        m = mass;
        px = posX;
        py = posY;
        vx = velX;
        vy = velY;
        img = imageFile;
    }
    
    /**
    * Description: returns a string representation of the body for the
    * purposes of printing. We will discuss toString methods in class.
    *
    * DO NOT EDIT THIS METHOD AT ALL!
    */
    public String toString() {
        return String.format("%12.5e %12.5e %12.5e %12.5e %12.5e %12s",
        m, px, py, vx, vy, img);
    }
    
    /**
    * Inputs: Body other
    * Outputs: x-axis distance between the other body and this distance
    * Description: Finds the distance between two bodies in the x direction with 
    * subtraction
    */
    public double distanceToX(Body other) {
        //Subtract both x values to find the distance between
        return other.px - this.px; // return statement
    }
    
    /**
    * Inputs: Body other
    * Outputs: y-axis distance between the other body and this distance
    * Description: Finds the distance between two bodies in the y direction with 
    * subtraction
    */
    public double distanceToY(Body other) {
        //Subtract both y values to find the distance between
        return other.py - this.py; // return statement
    }
    
    /**
    * Inputs: Body other
    * Outputs: overall distance between the two bodies
    * Description: finds the distance between two bodies using the distance formula
    */
    public double distanceTo(Body other) {
        //First find the distance in the x and y direction
        double deltaX = other.px - this.px;
        double deltaY = other.py - this.py;

        // Distance formula
        double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance; // return statement
    }
    
    /**
    * Inputs: Body other
    * Outputs: the force between the two bodies
    * Description: calculate the force between two bodies using the force equation
    */
    public double force(Body other) {
        double d = distanceTo(other); //define the distance
        return (G * this.m * other.m) / (d * d); // return statement
    }
    
    /**
    * Inputs: Body other
    * Outputs: the force between the two bodies in the x direction
    * Description: calculate the force between the two bodies in the x direction
    * using force over the distance
    */
    public double forceX(Body other) {
        // Multiply the total force by change in x over distance
        return force(other) * distanceToX(other) / distanceTo(other);
    }
    
    /**
    * Inputs: Body Other
    * Outputs: the force between the two bodies in the y direction
    * Description: calculate the force between the two bodies in the y direction
    * using force over the distance
    */
    public double forceY(Body other) {
        // Multiply the total force by change in y over distance
        return force(other) * distanceToY(other) / distanceTo(other);
    }
    
    /**
    * Inputs: none
    * Outputs: Draw a picture of the body in it's current state
    * Description: Use the method to draw the body
    */
    public void draw() {
        // Penn Draw feature to draw a picture
        PennDraw.picture(this.px, this.py, this.img);
    }
    
    /**
    * Inputs: double timeStep
    * Outputs: updated x and y position
    * Description: Finds the new positions by multiplying the velocity and timestep
    */
    public void move(double timeStep) {
        // position is the original position plus the v times timeStep
        this.px += this.vx * timeStep; // x position
        this.py += this.vy * timeStep; // y position
    }
    
    /**
    * Inputs: Body other and a double timeStep
    * Outputs: updated velocity in the x and y position
    * Description: Finds the new velocity using the force, mass and timeStep
    */
    public void getAffectedBy(Body other, double timeStep) {
        // velocity is the original v plus the force over mass times timeStep
        this.vx += (forceX(other) / this.m) * timeStep; // x velocity
        this.vy += (forceY(other) / this.m) * timeStep; // y velocity
    }
}
