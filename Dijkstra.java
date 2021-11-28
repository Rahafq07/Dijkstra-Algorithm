
import java.util.ArrayList;


/**
 * <h1> Compute The Length Of The Shortest Paths Using Dijkstra's Algorithm </h1>
 * 
 * The program will compute the length of the shortest paths from the chosen source to the rest of the vertices using Dijkstra's Algorithm and Compute the run duration .
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 27-11-2021
 */

public class Dijkstra {

    // global variables declaration 
    
    // array of vertices
    static String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
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
            System.out.println("\nTree Vertices (VT)");
            System.out.println(vertices[Newdis] + "(" + (vertices[source].equals(vertices[Newdis])?"-":vertices[Edges.get(Newdis).source]) + "," + distance[Newdis] + ")\n");
            
            // printing remaining vertices
            System.out.println("Remaining Vertices (V-VT)");
            
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
            
            if(!vertices[source].equals( vertices[i]))
            System.out.println(String.format("   >> Distance from %s to %s is %d km", vertices[source], vertices[i], distance[i]));
        
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
        
        // The given matrix
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


