
import java.util.ArrayList;


/**
 * <h1> Compute The Length Of The Shortest Paths Using Dijkstra's Algorithm </h1>
 * 
 * The program will compute the length of the shortest paths from the chosen source to the rest of the vertices using Dijkstra's Algorithm and Compute the run duration .
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 21-12-2021
 */

public class Dijkstra {

    // global variables declaration 
    
    // array of vertices for graph 1
    static String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
    // array of vertices for graph 2
    //static String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};

    // array of vertices for graph 3
    //static String[] vertices={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
    
    // array of vertices for graph 4
    //static String[] vertices={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
    
    // array of vertices for graph 5
    //static String[] vertices={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A1", "B1", "C1", "D1"};
    
    //to save the tree
    static String tree = "";
    //to save the visited nodes labels
    static String visited = "";
    //Edges from tybe ArrayList<edge>
    static ArrayList<edge> Edges= new ArrayList();
    // infinite number ' ∞ '
    static int INF = Integer.MAX_VALUE;
    
    /**
     * This is dijkstra method which present Tree Vertices(VT) and the Remaining Vertices(V-VT) 
     * for for Directed Weighted Graph,then calculate the time duration.
     * 
     * @param matrix The given matrix.
     * @param source The index of source vertex.
     * 
     */
    public static void dijkstra(int matrix [][], int source) {
        
        double startTime = System.currentTimeMillis(); //Start time
        
        // matrix length
        int matrixLen = matrix.length;
        
        // boolean array to check if the vertex is visited
        boolean[] isvisited = new boolean[matrixLen];
        
        //int array to store the distance
        int[] distance = new int[matrixLen];
        
        // initialize isvisited and distance arrays
        for (int i = 0; i < matrixLen; i++) {
            
            isvisited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            Edges.add(new edge(source));
            
        }

        // let the Distance of self loop = zero
        distance[source] = 0;
        
        for (int i = 0; i < matrixLen; i++) {
            
            // Update the distance between neighbouring vertex and source vertex
            int Newdis = findMinDis(distance, isvisited);
            
            if (Newdis == -1) { //if vertex isolated
                break;
            }

            isvisited[Newdis] = true;
            
            
            // Update all the neighbouring vertex distances
            for (int v = 0; v < matrixLen; v++) {
                
                if (!isvisited[v] && matrix[Newdis][v] != 0 && matrix[Newdis][v] != INF &&(distance[Newdis] + matrix[Newdis][v] < distance[v])) {
                    
                    distance[v] = distance[Newdis] + matrix[Newdis][v]; // Update distances
                    Edges.get(v).setSrc(Newdis); // save the intermediate source
                    visited+=vertices[v];  // save the visited nodes labels
                   
                }
            }
            
            // printing tree vertex 
            System.out.println("\nTree Vertices (VT)\n");
            
            tree += vertices[Newdis] + "(" + (vertices[source].equals(vertices[Newdis]) ? "-" : vertices[Edges.get(Newdis).source]) + "," + distance[Newdis] + ")\n";
            System.out.println(tree);

            // printing remaining vertices
            System.out.println("Remaining Vertices (V-VT)\n");
            
            for (int j = 0; j < matrixLen; j++) {
                
                if (!isvisited[j]) {
                    System.out.println(vertices[j] + "("+(visited.contains(vertices[j])?vertices[Edges.get(j).source]:"-")+","+(distance[j]==INF?"∞":distance[j])+")");
                }
            }
            System.out.println("\n-----------------------------------------");

        }
        
        // printing final distances
        System.out.println("\nShortest distances from "+ vertices[source] +" to all other vertices are:\n");
        for (int i = 0; i < distance.length; i++) {
            
            if(!vertices[source].equals( vertices[i])){
                if (distance[i] == Integer.MAX_VALUE) {
                    System.out.println(String.format("   >> There is no Distance from %s to %s ", vertices[source], vertices[i]));
                } else {
                    System.out.println(String.format("   >> Distance from %s to %s is %d ", vertices[source], vertices[i], distance[i]));
                }
            }
        }
        System.out.println();//New Line
        
        double endTime = System.currentTimeMillis(); //End time
        double totalTime = endTime - startTime; //Total time
        
        //print Running time
        System.out.println("\nRunning time of Dijkstra algorithm is: " + totalTime + " milli seconds\n");

    }

    /**
     * This is findMinDis method which find the Minimum Distance and return it .
     * 
     * @param distance distance array
     * @param isvisited boolean array to know if the Vertex is visited or not
     * @return min Distance Vertex
     */
    private static int findMinDis(int[] distance, boolean[] isvisited) {
        
        // let minDis = infinite number
        int minDis = Integer.MAX_VALUE;
        
        //let minDisVertex = -1
        int minDisVertex = -1;
        
        for (int i = 0; i < distance.length; i++) {
            if (!isvisited[i] && distance[i] < minDis) {
                minDis = distance[i];
                minDisVertex = i;
               
            }
        }
        return minDisVertex;
    }

    /**
     * This is the main method which print the start sentence 
     * and send the source vertex and matrix to the dijkstra method.
     * 
     * @param args is the parameter to the main Method.
     */
    public static void main(String[] args) {
        
        // The First graph (The given matrix - 10 Vertices)
                                 //  A   B   C    D    E   F   G    H    I    J 
        int matrix[][] = new int[][]{{0, 10, INF, INF, INF, 5, INF, INF, INF, INF},    // A
                                    {INF, 0, 3, INF, 3, INF, INF, INF, INF, INF},     // B
                                    {INF, INF, 0, 4, INF, INF, INF, 5, INF, INF},     // C
                                    {INF, INF, INF, 0, INF, INF, INF, INF, 4, INF},   // D
                                    {INF, INF, 4, INF, 0, INF, 2, INF, INF, INF},     // E
                                    {INF, 3, INF, INF, INF, 0, INF, INF, INF, 2},     // F
                                    {INF, INF, INF, 7, INF, INF, 0, INF, INF, INF},   // G
                                    {INF, INF, INF, 4, INF, INF, INF, 0, 3, INF},     // H
                                    {INF, INF, INF, INF, INF, INF, INF, INF, 0, INF}, // I
                                    {INF, 6, INF, INF, INF, INF, 8, INF, INF, 0},     // J    
                                    };
        
        //--------------------------------------------------------------------------
        
        /*  
        // The second graph (2 - 15 Vertices)
        
                        //   A   B    C    D    E    F    G    H    I    G    K    L    M    N    O
        int matrix[][] = {{  0, INF, INF, INF, INF,   3, INF, INF, INF, INF, INF, INF, INF, INF,   6}, // A
                          {  8,   0, INF, INF, INF, INF, INF, INF, INF, INF,   2, INF,   5, INF,   3}, // B
                          {INF, INF,   0, INF, INF, INF, INF,   9, INF, INF, INF, INF,   7,   4, INF}, // C
                          {  2, INF, INF,   0, INF,   1, INF, INF, INF,   3, INF, INF, INF,   7,   9}, // D
                          {INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // E
                          {INF, INF, INF, INF, INF,   0, INF,   9, INF, INF, INF, INF, INF, INF,   2}, // F
                          {INF, INF,   3, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF}, // G
                          {INF, INF, INF, INF, INF, INF, INF,   0,   4, INF, INF,   2, INF, INF, INF}, // H
                          {  1, INF, INF, INF, INF, INF, INF,   6,   0, INF, INF, INF, INF,   2, INF}, // I
                          {INF, INF, INF, INF, INF,   9, INF, INF, INF,   0, INF,   1, INF, INF,   4}, // J
                          {INF, INF, INF, INF, INF, INF,   6,   2, INF, INF,   0, INF, INF, INF, INF}, // K
                          {INF, INF, INF,  10,   3, INF, INF, INF,   5, INF, INF,   0, INF,   1,   2}, // L
                          {INF, INF, INF,   9, INF, INF, INF,   1,   4, INF,   7, INF,   0, INF, INF}, // M
                          {INF, INF, INF,   3, INF, INF, INF, INF,   8,   2, INF, INF, INF,   0, INF}, // N
                          {  4, INF,   8, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0}, // O
                         }; */
        
        //--------------------------------------------------------------------------
        
        /*
        // The third graph (3 - 20 Vertices)
        
                        //   A   B    C    D    E    F    G    H    I    G    K    L    M    N    O    P    Q    R    S    T
        int matrix[][] = {{  0, INF, INF, INF, INF, INF, INF, INF,   1, INF, INF, INF, INF, INF, INF, INF, INF,   4, INF, INF}, // A
                          {INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF,   3, INF, INF,   8,   2, INF, INF, INF, INF}, // B
                          {INF, INF,   0, INF, INF,   1, INF, INF, INF, INF, INF, INF, INF,   2, INF, INF, INF, INF, INF, INF}, // C
                          {INF, INF, INF,   0, INF, INF, INF, INF,   5, INF, INF,   9, INF, INF, INF, INF, INF, INF, INF, INF}, // D
                          {INF, INF, INF, INF,   0, INF, INF, INF,   1, INF, INF, INF,   2,  10, INF, INF, INF, INF, INF, INF}, // E
                          {  1, INF, INF, INF, INF,   0, INF,   4, INF, INF, INF, INF,   1, INF, INF, INF, INF, INF, INF, INF}, // F
                          {INF, INF, INF, INF, INF, INF,   0,   7, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   5, INF}, // G
                          {INF, INF, INF, INF,   3,   1, INF,   0, INF, INF, INF, INF,   9, INF, INF, INF, INF,   1, INF, INF}, // H
                          {INF, INF,   7, INF, INF, INF, INF, INF,   0, INF, INF, INF,   1, INF, INF, INF, INF, INF, INF, INF}, // I
                          {INF, INF, INF,   6,   1, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // G
                          {INF, INF, INF, INF, INF, INF,   5, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // K
                          {INF, INF, INF, INF,   2, INF, INF, INF, INF, INF,   9,   0, INF, INF, INF,   4,   2,   1, INF, INF}, // L
                          {INF, INF,   4, INF, INF, INF, INF, INF, INF, INF,   3, INF,   0, INF, INF, INF, INF, INF, INF, INF}, // M
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF}, // N
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF}, // O
                          {INF, INF,   7, INF, INF, INF, INF,   2, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF}, // P
                          {INF, INF, INF, INF, INF, INF, INF,   1, INF, INF, INF, INF, INF, INF,  10, INF,   0, INF,   1, INF}, // Q
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   3,   0, INF, INF}, // R
                          {INF, INF, INF, INF, INF, INF, INF,  10, INF, INF,   4, INF, INF, INF, INF, INF, INF, INF,   0, INF}, // S
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   1,   6, INF, INF, INF, INF,   9,   0}, // T
                         }; */
        
        //--------------------------------------------------------------------------

        /*
        // The fourth graph (4 - 25 Vertices)
        
                        //   A   B    C    D    E    F    G    H    I    G    K    L    M    N    O    P    Q    R    S    T    U    V    W    X    Y
        int matrix[][] = {{  0, INF, INF, INF, INF,   4, INF, INF,   2,   9, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // A
                          {INF,   0, INF, INF, INF, INF, INF,   2, INF, INF,   5, INF, INF, INF, INF, INF, INF, INF,   3, INF, INF, INF, INF, INF, INF}, // B
                          {INF,  10,   0, INF, INF, INF, INF,   3, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   8, INF, INF, INF, INF}, // C
                          {INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF,   2, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // D
                          {INF, INF, INF,   9,   0, INF,   5, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   1, INF, INF}, // E
                          {INF, INF,   7, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   2, INF, INF, INF}, // F
                          {INF, INF, INF, INF, INF, INF,   0, INF, INF,   6, INF, INF,   3, INF,   1, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // G
                          {INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // H
                          {INF, INF, INF, INF, INF, INF, INF,  10,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // I
                          {INF, INF, INF,   2, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF,   8, INF, INF,   1, INF, INF, INF}, // J
                          {INF, INF, INF, INF,   4, INF, INF, INF, INF, INF,   0, INF, INF,   5, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // K
                          {INF,   8, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF,   7, INF, INF, INF, INF,   7, INF}, // L
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // M
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF,  10, INF, INF, INF, INF}, // N
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   1, INF,   2,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // O
                          {INF,   8, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   4,   0, INF, INF, INF, INF, INF, INF,   2, INF, INF}, // P
                          {INF, INF,   1, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   7, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF}, // Q
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF,   3, INF, INF, INF, INF}, // R
                          {INF, INF, INF, INF, INF,  10, INF, INF, INF, INF, INF, INF, INF,   4, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF}, // S
                          {INF, INF, INF, INF, INF, INF, INF,   5, INF,   3, INF, INF, INF, INF, INF, INF,   8, INF, INF,   0, INF, INF, INF, INF, INF}, // T
                          {INF, INF, INF, INF, INF, INF, INF, INF,   2, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF}, // U
                          {  4,   2, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF}, // V
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   8, INF, INF, INF, INF,   0, INF, INF}, // W
                          {INF,   7, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF}, // X
                          {INF, INF, INF, INF, INF, INF, INF,   1, INF, INF, INF, INF, INF, INF, INF, INF, INF,   9, INF, INF, INF, INF, INF,   3,   0}, // Y
                         }; */
        
        //--------------------------------------------------------------------------

        /*
        // The fifth graph (5 - 30 Vertices)
        
                        //   A   B    C    D    E    F    G    H    I    G    K    L    M    N    O    P    Q    R    S    T    U    V    W    X    Y    Z    A1   B1   C1   D1  
        int matrix[][] = {{  0, INF, INF, INF, INF, INF, INF, INF, INF,   5, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // A
                          {INF,   0, INF, INF, INF, INF,   3, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // B
                          {INF,   7,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   9, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // C
                          {INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // D
                          {INF, INF, INF, INF,   0,   4, INF, INF, INF, INF, INF, INF, INF,   2, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // E
                          {INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   1, INF, INF, INF, INF, INF}, // F
                          {INF, INF, INF, INF, INF,   2,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // G
                          {INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   3, INF,   2}, // H
                          {  5, INF, INF, INF, INF,   6, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF,   1, INF, INF,   6, INF, INF, INF,   5, INF,   4, INF, INF, INF}, // I
                          {INF, INF, INF, INF, INF, INF, INF,   8, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // J
                          {INF, INF, INF,   2, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // K
                          {INF, INF, INF, INF, INF, INF, INF, INF,   1, INF, INF,   0, INF, INF,   5, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   7, INF,   9, INF, INF}, // L
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // M
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   1,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // N
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF,   2, INF, INF, INF, INF, INF,   9, INF, INF, INF, INF}, // O
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF,   4, INF, INF, INF, INF, INF}, // P
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0,   7, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   5, INF}, // Q
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   3, INF, INF, INF, INF,   0, INF,   1, INF, INF, INF, INF,   8, INF, INF, INF, INF, INF}, // R
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF,   6, INF, INF, INF, INF,   1, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // S
                          {INF, INF, INF, INF,   3,   8, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // T
                          {INF,   8, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF, INF}, // U
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF,   7, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF, INF}, // V
                          {INF, INF,   5, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF, INF, INF, INF}, // W
                          {INF, INF, INF, INF, INF, INF, INF, INF,   9, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   9,   0, INF, INF, INF, INF, INF, INF}, // X
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   7, INF,   0, INF, INF, INF, INF, INF}, // Y
                          {INF, INF,   2, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   1, INF, INF, INF, INF, INF, INF,   3, INF, INF,   0, INF, INF, INF, INF}, // Z
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   2, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF, INF}, // A1
                          {INF, INF,   3, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   7, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF, INF}, // B1
                          {  9, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   0, INF}, // C1
                          {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF,   2, INF, INF,   0}, // D1
                        }; */
        
        //--------------------------------------------------------------------------
        
        // start sentence 
        System.out.println("------------------- WELCOME TO Dijkstra ALGORITHM -------------------\n\n");
        System.out.println(">> The following list shows the Tree Vertices(VT) and the Remaining Vertices(V-VT) for for Directed Weighted Graph:\n");
        
        int source=0;//source vertex
        
        dijkstra(matrix, source); // send the source vertex and matrix to the dijkstra method
        
    }
    
    /**
     * class edge which has ertex and source.
     */
    static class edge{
        int vert;
        int source;
        edge(int source){
            this.source=source;
        }

        // getter and setter
        
        public int getVert() {
            return vert;
        }

        public void setVert(int vert) {
            this.vert = vert;
        }

        public int getSrc() {
            return source;
        }

        public void setSrc(int source) {
            this.source = source;
        }
    }
}


