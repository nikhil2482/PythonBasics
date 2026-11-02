/*
*
* Execution: java NBody
* 
* Simulate the universe and calculate certain values given the total time 
* and incremental time
*/


public class NBody {
    public static void main(String[] args) {

        // Declare command-line arguments
        double simulationTime = Double.parseDouble(args[0]);
        double timeStep = Double.parseDouble(args[1]);
        String filename = args[2];

        // Print statements to test command-line arguments
        // System.out.println(simulationTime);
        // System.out.println(timeStep);
        // System.out.println(filename);

        // Create a variable inStream of type In to read from the file
        In inStream = new In(filename);

        // Declare and initialize variables from the file
        int numBodies = inStream.readInt();
        double radius = inStream.readDouble();
    
        // Declare the double arrays from the file
        double[] m = new double[numBodies];
        double[] px = new double[numBodies];
        double[] py = new double[numBodies];
        double[] vx = new double[numBodies];
        double[] vy = new double[numBodies];
        String[] img = new String[numBodies];

        // Declare and initialize i for all for loops to start at 0
        int i = 0;

        // Set values for arrays from the file with a for loop
        for (i = 0; i < numBodies; i++) {
            m[i] = inStream.readDouble();
            px[i] = inStream.readDouble();
            py[i] = inStream.readDouble();
            vx[i] = inStream.readDouble();
            vy[i] = inStream.readDouble();
            img[i] = inStream.readString();
        }

        // Set the background size to the radius size
        PennDraw.setXscale(-radius, radius);
        PennDraw.setYscale(-radius, radius);
        
        // Enable animation to 30 fps
        PennDraw.enableAnimation(30);

        // Declare the time elasped and loop number to be used in for statement
        double elaspedTime;

        // Create for loop for when the universe should run
        for (elaspedTime = 0; elaspedTime < simulationTime; 
        elaspedTime += timeStep) {
            PennDraw.picture(0.5, 0.5, "starfield.jpg"); // Draw the background

            // Create outer loop for calculating new acceleration and velocity
            for (i = 0; i < numBodies; i++) {

                // Declare variables that are used in the outer loop
                double totalForceX = 0.0;
                double totalForceY = 0.0;
                double accelerationX;
                double accelerationY;

                /* Create inner loop for calculating the total force on a certain
                * x and y forces in relation to from all the other planets
                */
                for (int j = 0; j < numBodies; j++) {
                    if (i != j) { // A planet exerts no force on itself

                    // Declare variables that are used in the inner loop
                    double changeX = 0.0;
                    double changeY = 0.0;
                    double d;
                    double force;
                    double forceX = 0.0;
                    double forceY = 0.0;
                    double G = 6.67e-11;

                    // Calculate the change in the x and y position between planets
                    changeX = px[j] - px[i];
                    changeY = py[j] - py[i];

                    // Calculate the distance and forces
                    // Distance formula
                    d = Math.sqrt((changeX * changeX) + (changeY * changeY));
                    force = ((G * m[i]) / (d * d)) * m[j]; // Force formula
                    forceX = force * (changeX / d); // Force in x direction
                    forceY = force * (changeY / d); // Force in y direction
                    totalForceX += forceX;
                    totalForceY += forceY;
                    }

                // Calculate acceleration based on total force and then the velocity 
                accelerationX = totalForceX / m[i];
                accelerationY = totalForceY / m[i];
                vx[i] += timeStep * accelerationX;
                vy[i] += timeStep * accelerationY;

                // Set total Force back to zero so that it doesn't keep adding on
                totalForceX = 0;
                totalForceY = 0;
                }
            }

            // Create loop for changing the position after velocity is found
            for (i = 0; i < numBodies; i++) {
                px[i] += timeStep * vx[i];
                py[i] += timeStep * vy[i];
                
                // Draw the planet in its new position
                PennDraw.picture(px[i], py[i], img[i]);
            }

            // Advance the animation
            PennDraw.advance();
        }

        // Print out the contents of the six arrays
        System.out.printf("%d\n", numBodies);
            System.out.printf("%.2e\n", radius);
            for (i = 0; i < numBodies; i++) {
                System.out.printf("%12.5e %12.5e %12.5e %12.5e %12.5e %12s\n", m[i], 
                px[i], py[i], vx[i], vy[i], img[i]);
            }
 

    }
}