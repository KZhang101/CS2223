import java.util.Scanner;
import java.util.*;

public class dijkstra {

    private final int[][] nodeMatrix = {
        {0, 53, 10, 12, 0, 0, 0, 0, 0, 0},
        {53, 0, 33, 0, 2, 0, 101, 0, 0, 0},
        {10, 33, 0, 9, 30, 18, 0, 0, 0, 0},
        {12, 0, 9, 0, 0, 17, 0, 0, 6, 0},
        {0, 2, 30, 0, 0, 14, 123, 122, 0, 0},
        {0, 0, 18, 17, 14, 0, 0, 137, 7, 0},
        {0, 101, 0, 0, 123, 0, 0, 8, 0, 71},
        {0, 0, 0, 0, 122, 137, 8, 0, 145, 66},
        {0, 0, 0, 6, 0, 7, 0, 145, 0, 212},
        {0, 0, 0, 0, 0, 0, 71, 66, 212, 0}
    };

    private int[] costTable = new int[nodeMatrix.length];
    private int[] pathTable = new int[nodeMatrix.length];
    private boolean[] visited = new boolean[nodeMatrix.length];
    private int nodeLen = nodeMatrix.length;
  


    public dijkstra(){
        initTables();

        Scanner input = new Scanner(System.in);
        System.out.println("Choose a starting node: " );
        int start = input.nextInt(); 
        input.nextLine();
        System.out.println("Choose a ending node: ");
        int end = input.nextInt();
        input.nextLine();
        runDijkstra(start);
        findPath(start, end);
    }

    private void initTables(){
        for(int i = 0; i < nodeLen; i++){
            this.costTable[i] = Integer.MAX_VALUE; // Highest cost possible
            this.pathTable[i] = -1; // Came from n/a
            this.visited[i] = false; // not visited
        }
    }




    private void runDijkstra(int start){
        ArrayList<Integer> visiting = new ArrayList<Integer>();
        visiting.add(start);
        this.costTable[start] = 0; // Starting at start node has cost 0
        int currentNode = start;
        int indexNext = 0;

        while(visiting.size() != 0){
            visiting.remove(indexNext);
            for(int i = 0; i < nodeLen; i++){
                this.visited[currentNode] = true;

                if(nodeMatrix[currentNode][i] != 0){
                    if(!this.visited[i] && !visiting.contains(i)) visiting.add(i); // Plan to visit but do not duplicate

                    if(this.costTable[i] > nodeMatrix[currentNode][i] + costTable[currentNode]){ // Check if new path is easier
                        this.costTable[i] = nodeMatrix[currentNode][i] + costTable[currentNode];
                        this.pathTable[i] = currentNode;
                    }
                }   
            }
            if(visiting.size() == 0){
                printTable();
                break;
            }
            indexNext = leastCostNode(visiting);
            //System.out.println("\nCurrent Node: " + currentNode);
            currentNode = visiting.get(indexNext);
            //System.out.println("Now visiting: " + currentNode);
            //printTable();
            //System.out.println('\n');
        }
        System.out.println("All starting paths found!");
    }

    private int leastCostNode(ArrayList<Integer> visiting){
        int least = this.costTable[visiting.get(0)];
        int index = 0;
        //System.out.println(least);
        //System.out.println("Current Stack: ");
        //System.out.print(visiting.get(0));

        for(int i = 1; i < visiting.size(); i++){
            int currentCost = this.costTable[visiting.get(i)];
            if(least > currentCost){
                least = currentCost;
                index = i;
            }
            //System.out.print(", " + visiting.get(i));
        }
        return index;
    }

    private void printTable(){
        for(int i = 0; i < nodeLen; i++){
            System.out.println("Node: " + i + " | Cost: " + this.costTable[i] + " | Path: " + this.pathTable[i] + " | Visited: " + this.visited[i]);
        }
    }

    
    private void findPath(int start, int end){
        ArrayList<Integer> path = new ArrayList<>();
        int currentNode = end;
        //.out.print(currentNode + " ");
        while(this.pathTable[currentNode] != -1){
            path.add(currentNode);
            currentNode = this.pathTable[currentNode];
           // System.out.print(currentNode + " ");
        }
        path.add(currentNode);
        System.out.print("\nPath: ");
        for(int i = path.size()-1; i > 0; i--){
            System.out.print(path.get(i) + " -> ");
        }
        System.out.print(path.get(0));
        System.out.println("Cost: " + this.costTable[currentNode]);

    }
}