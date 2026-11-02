/**
 * Execution: n/a
 *
 * Description: Traveling salesperson problem that uses point objects.
 * It finds a close to the best path for the salesperson traveling to a certain
 * specified places. 
**/


public class Tour implements TourInterface {
    private Node head;
    private Node lastNode;
    
    public Tour() {
        head = null;
        lastNode = null;
    }
    
    /**
     * Inputs: n/a
     * Outputs: String name that shows the points
     * Description: this method givest he string that represents the points
    */
    @Override
    public String toString() {
        String name = "";
        // If the head is null then there are no points to go to
        if (head == null) {
            return "";
        } else {
            Node curr = head;
            while (curr.next != null) { // what to do if curr.next is not null
                // new string is the old plus the new part
                name += curr.point.toString();
                curr = curr.next;
            }
            name += lastNode.point.toString();
            return name;
        }
        
    }
    
    /**
     * Inputs: n/a
     * Outputs: Draws the points
     * Description: This method draws the tour and where the salesperson should go
    */
    @Override
    public void draw(Point p) {
        Node curr = head;
        // If the head is null then there are no points to go to
        if (head == null) {
            return;
        }
        // Iteration for when it's not null
        while (curr.next != null) {
            if (p == null) {
                PennDraw.setPenColor(PennDraw.BLACK);
            } else if (curr.point == p || curr.next.point == p) {
                PennDraw.setPenColor(PennDraw.RED);
            } else {
                PennDraw.setPenColor(PennDraw.BLACK);
            }
            curr.point.drawTo(curr.next.point);
            curr = curr.next;
        }
    }
    
    /**
     * Inputs: n/a
     * Outputs: int length of the points in the tour
     * Description: Uses the data to find the length of the points
    */
    @Override
    public int size() {
        int length = 0;
        // If the head is null then there are no points to go to
        if (head == null) {
            return 0;
        } else {
            Node curr = head;
            while (curr.next != null) {
                length++; // increase length by 1
                curr = curr.next;
            }
            return length;
        }
    }
    
    /**
     * Inputs: n/a
     * Outputs: double distance that's the distance the salesperson travels
     * Description: This method gives the total distance traveled
    */
    @Override
    public double distance() {
        double distance = 0.0;
        // If the head is null then there are no points to go to
        if (head == null) {
            return 0.0;
        } else {
            Node curr = head;
            while (curr.next != null) {
                // New distance is the old plus the new distance
                distance += curr.point.distanceTo(curr.next.point);
                curr = curr.next;
            }
        }
        return distance;
    }
    
    /**
     * Inputs: Point p
     * Outputs: n/a
     * Description: Uses the point p to insert at the end of the tour
    */
    @Override
    public void insertInOrder(Point p) {
        Node curr = head;
        // If the head is null then there are no points to go to
        if (head == null) {
            head = new Node(lastNode, p);
            lastNode = new Node(p);
        } else { // what to do if the head is not null
            while (curr.next.next != null) { // iterate over the path
                curr = curr.next;
            }
            curr.next = new Node(lastNode, p);
        }
    }
    
    /**
     * Inputs: Point p
     * Outputs: n/a
     * Description: Insert the point p to the next closest one
    */
    @Override
    public void insertNearest(Point p) {
        Node curr = head;
        // If the head is null then there are no points to go to
        if (curr == null) {
            lastNode = new Node(p);
            head = new Node(lastNode, p);
        } else { // what to do if curr does not equal null
            double minimum = Double.MAX_VALUE;
            Node s = null;
            while (curr.next != null) { 
                if (curr.point.distanceTo(p) < minimum) {
                    minimum = curr.point.distanceTo(p); // set distance to the min
                    s = curr;
                }
                curr = curr.next;
            }
        }
        
    }
    
    /**
     * Inputs: Point p
     * Outputs: n/a
     * Description: Put Point p in the places that causes the least distance detour
    */
    @Override
    public void insertSmallest(Point p) {
        Node curr = head;
        // If the head is null then there are no points to go to
        if (curr == null) {
            lastNode = new Node(p);
            head = new Node(lastNode, p);
        } else { // what to do if the distance does not equal null
            Node s = null;
            while (curr.next != null) {
                // Use the max value and decrease from there
                double minimum = Double.MAX_VALUE;
                double d = curr.point.distanceTo(p) + curr.next.point.distanceTo(p)
                - curr.point.distanceTo(curr.next.point);
                if (d < minimum) { // if the distance if less then set new min value
                    minimum = d;
                    s = curr;
                }
                curr = curr.next;
            }
            s.next = new Node(s.next, p);
        }
        
    }
    
}

