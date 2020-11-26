package server;

import java.net.*;
import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;

//public final class Server {
//
//    Socket socket = null;
//    ServerSocket socketServer = null;
//    DataInputStream inputStream = null;
//    ArrayList<Integer>[] adjacencyList;
//    List<List<Integer>> paths = new ArrayList<>();
//    PrintStream sendToClient = null;
//    HashMap<Character, Integer> characterMappings = new HashMap<>();
//
//
//    public Server(int port) {     
//        try {
//            characterMappings.put('A', 0);
//            characterMappings.put('B', 1);
//            characterMappings.put('C', 2);
//            characterMappings.put('D', 3);
//            characterMappings.put('E', 4);
//            
//            socketServer = new ServerSocket(port);
//            socket = socketServer.accept(); 
//            inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//            sendToClient = new PrintStream(socket.getOutputStream());
//            int length = inputStream.readInt();
//            adjacencyList = new ArrayList[length];
//            for (int i = 0; i < length; i++) {
//                adjacencyList[i] = new ArrayList<>();
//            }
//            for (int i = 0; i < length; i++) {
//                for (int j = 0; j < length; j++) {
//                    int num = inputStream.readInt();
//                    if (num == 1) {
//                        adjacencyList[i].add(j);
//                    }
//                }
//            }
//
//            int size = inputStream.readInt();
//            Character node1 = inputStream.readChar();
//            Character node2 = inputStream.readChar();
//            
//            solveGraph(characterMappings.get(node1), characterMappings.get(node2), length, size);
//
//            if (paths.size() > 0) {
//                sendToClient.println("Yes there exists a path of length " + size + " from node " + node1 + " to node " + node2);
//            } else {
//                sendToClient.println("No there is no path of length " + size + " from node " + node1 + " to node " + node2);
//            }
//            
//            socket.close();
//            inputStream.close();
//            sendToClient.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
//
//    public void solveGraph(int node1, int node2, int length, int size) {
//        boolean[] vis = new boolean[length];
//        ArrayList<Integer> pathList = new ArrayList<>();
//        pathList.add(node1);
//        graphUtil(node1, node2, vis, pathList, size);
//    }
//
//    private void graphUtil(Integer node1, Integer node2, boolean[] vis, List<Integer> myCurrentPath, int size) {
//        if (node1.equals(node2) && myCurrentPath.size()-1 == size) {
//            paths.add(myCurrentPath);
//            return;
//        }
//        vis[node1] = true;
//
//        adjacencyList[node1].stream().filter((i) -> (!vis[i])).map((i) -> {
//            myCurrentPath.add(i);
//            return i;
//        }).map((i) -> {
//            graphUtil(i, node2, vis, myCurrentPath, size);
//            return i;
//        }).forEachOrdered((i) -> {
//            myCurrentPath.remove(i);
//        });
//        vis[node1] = false;
//    }
//
//    public static void main(String[] args) {
//        Server server = new Server(6789);
//    }
//}



public class Server {
    static int N = 3;
    
    //MULTIPLICATION
    static void mult(int matrixA[][], int matrixB[][], int multiplyRes[][]) {
    int [][]mul = new int[N][N]; 
    for (int i = 0; i < N; i++) 
    { 
        for (int j = 0; j < N; j++)  
        { 
            mul[i][j] = 0; 
            for (int k = 0; k < N; k++) 
                mul[i][j] += matrixA[i][k] * matrixB[k][j]; 
        } 
    } 
  
    // Storing the multiplication result in res[][] 
    for (int i = 0; i < N; i++) 
        for (int j = 0; j < N; j++) 
            multiplyRes[i][j] = mul[i][j]; 
        
    }
    
    
    //POWER FUNCTION
    static void exponential(int G[][], int res[][], int n) { 
  
    // Base condition 
    if (n == 1) { 
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                res[i][j] = G[i][j]; 
        return; 
    } 
  
    // Recursion call for first half 
    exponential(G, res, n / 2); 
  
    // Multiply two halves 
    mult(G, G, res); 
  
    // If n is odd 
    if (n % 2 != 0) 
        mult(res, G, res);
  }
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws Exception{
    try {
    ServerSocket serverSocket = new ServerSocket(4200);
    System.out.println("SERVER HAS BEEN SET UP");
    
    while(1==1) {
        Socket socket = serverSocket.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        
        int length = input.readInt(); //receive length
        Character node1 = input.readChar();
        Character node2 = input.readChar();
        //int N = 2;
        int[][] adjMatrixServer = new int[N][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                adjMatrixServer[i][j] = input.readInt();
            }
        }
        
//        System.out.println(node1);
//        System.out.println(node2);
//        System.out.print(length);
//        
//        for(int i=0; i<N; i++) {
//            for(int j=0; j<N; j++) {
//               System.out.print(adjMatrixServer[i][j] + " ");
//            }
//            System.out.print("");
//        }
        
        int [][]res = new int[N][N]; 
  
        exponential(adjMatrixServer, res, length); 
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
               System.out.print(adjMatrixServer[i][j] + " ");
            }
            System.out.print("\n");
        }
        
        String alphabet = "ABCDE";
        
        int nodeI = alphabet.indexOf(node1);
        int nodeJ = alphabet.indexOf(node2);
        
//        System.out.print(nodeI);
//        System.out.print(nodeJ);
        
        if(res[nodeI][nodeJ] > 0) {
            output.writeChar('Y');
        }
        else 
            output.writeChar('N');
    }
} catch(IOException e) {
    System.out.println(e);
}
    }
}