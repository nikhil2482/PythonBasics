/**
 * Execution: NBodyObj simulationTime timeStep Filename
 *
 * Description: Converts the arguments to variables and create a time loop
**/
public class NBodyObj {
    public static void main(String[] args) {
        // Finds the arguments and converts them to variables to use
        double simulationTime = Double.parseDouble(args[0]);
        double timeStep = Double.parseDouble(args[1]);
        String filename = args[2];
        
        Space sv = new Space(filename); // Declare variable for info from filename
        PennDraw.enableAnimation(25); // Enable enableAnimation

        // Create a for loop that represents time loop and simulate the universe
        for (int elaspedTime = 0; elaspedTime < simulationTime; elaspedTime +=
        timeStep) {
            PennDraw.advance();
            sv.simulate(timeStep); // get new position and velocity
            sv.draw(); // draw the planet
        }
        PennDraw.disableAnimation(); // disable the animation
        System.out.println(sv); // print
        
    }
}
