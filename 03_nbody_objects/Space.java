/**
* Execution: cannot execute space by itself
*
* Description: Contains all methods for space in order to simulate all the bodies
* movements
**/
public class Space {
    
    /**
    * DO NOT EDIT ANY CODE BELOW THIS LINE
    *
    * You will get a style warning that reads:
    * "Variable '[some name]' must be private and have get/set methods."
    * Ignore any style warnings of this form. Our tests rely on these
    * variables being public. We will discuss public vs. private next week
    * in class. You are not expected to know it now.
    */
    public Body[] bodies; //array of Body objects in the space
    public double radius; //radius of the universe
    
    /* DO NOT EDIT ANY CODE ABOVE THIS LINE */
    
    /**
    * Constructor: This creates a new instance of a space object.
    * Inputs: String filename
    * Outputs: stores values from table in bodies array
    * Description: reads the values in the data and stores some parts in the bodies
    * array
    */
    public Space(String filename) {
        // Read values from the table
        In inStream = new In(filename);
        int numBodies = inStream.readInt();
        bodies = new Body[numBodies]; // bodies length is the value of the numBodies
        radius = inStream.readDouble();
        // Set Scale
        PennDraw.setXscale(-radius, radius);
        PennDraw.setYscale(-radius, radius);
        // Create for loop to read through the data and store under each body in
        // in the bodies array
        for (int i = 0; i < bodies.length; i++) {
            bodies[i] = new Body(inStream.readDouble(), inStream.readDouble(),
            inStream.readDouble(), inStream.readDouble(),
            inStream.readDouble(), inStream.readString());
        }
        
    }
    
    /**
    * Description: returns a string representation of space for the purposes
    * of printing. We have discussed toString methods in class.
    *
    * DO NOT EDIT THIS METHOD AT ALL!
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("" + bodies.length + "\n");
        sb.append(String.format("%.2e\n", radius) + "\n");
        for (int i = 0; i < bodies.length; i++) {
            sb.append(bodies[i].toString() + "\n");
        }
        return sb.toString();
    }
    
    /**
    * Inputs: none
    * Outputs: Draws the bodies of the planet
    * Description: uses penn draw features to draw the planet
    */
    public void draw() {
        //TODO: Implement this method
        PennDraw.picture(0.0, 0.0, "starfield.jpg");
        for (int i = 0; i < bodies.length; i++) {
            PennDraw.picture(bodies[i].px, bodies[i].py, bodies[i].img);
        }
        
    }
    
    /**
    * Inputs: double timeStep
    * Outputs: updates the velocity of the planet
    * Description: Calls the getAffectedBy method to change the velocity in the X
    * and Y directions
    */
    public void simulate(double timeStep) {
        // Create a nested loop to find the affects of each planet on one another
        for (int i = 0; i < bodies.length; i++) {
            for (int j = 0; j < bodies.length; j++) {
                if (i != j) { // A planet cannot have an influence over itself
                    // Call getAffectedBy method to update velocity
                    bodies[i].getAffectedBy(bodies[j], timeStep);
                }
            }
        }
        // Update the positons of the planet using a for loop
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].move(timeStep);
        }
    }
}
