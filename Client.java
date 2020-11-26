package client;

import java.net.*;
import java.io.*;
import java.util.*;


//public class Client {
//
//    private Socket socket = null;
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private BufferedReader inputFromServer = null;
//    private DataOutputStream outputStreamToServer = null;
//
//    public Client(String address, int port) {
//        try {
//            socket = new Socket(address, port);
//            outputStreamToServer = new DataOutputStream(socket.getOutputStream());
//            inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println("Enter matrix");
//            int size = 5;
//            outputStreamToServer.writeInt(size);
//            for (int i = 0; i < size; i++) {
//                String numLine = reader.readLine();
//                String[] numbers = numLine.split(" ");
//                for (String num : numbers) {
//                    outputStreamToServer.writeInt(Integer.parseInt(num));
//                }
//            }
//            System.out.println("Enter n:");
//            outputStreamToServer.writeInt(Integer.parseInt(reader.readLine()));
//            System.out.println("Enter node1");
//            outputStreamToServer.writeChar(reader.readLine().charAt(0));
//            System.out.println("Enter node2");
//            outputStreamToServer.writeChar(reader.readLine().charAt(0));
//            System.out.println(inputFromServer.readLine());
//
//            reader.close();
//            outputStreamToServer.close();
//            socket.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        Client client = new Client("localhost", 6789);
//    }
//}

public class Client {
    static int N = 3;
    public Client(String address, int port) {
        Scanner inputObject = new Scanner(System.in);
        
        try {
            Socket clients = new Socket("localhost", 4200);
            DataInputStream input = new DataInputStream(clients.getInputStream());
            DataOutputStream output = new DataOutputStream(clients.getOutputStream());
            
            System.out.println("PLEASE ENTER THE MATRIX");
            
            int[][] adjMatrixClient =
            
             {{0, 1, 0},{1, 0, 1}, {0, 0, 0}};
            
//            for(int i=0; i<N; i++) {
//                for(int j=0; j<N; j++) {
//                    adjMatrixClient[i][j] = inputObject.nextInt();
//                }
//            }
            
//            for(int i=0; i<2; i++) {
//                for(int j=0; j<2; j++) {
//                    System.out.print(adjMatrixClient[i][j]);
//                    
//                }
//            }
            
            int length;
            
            System.out.println("PLEASE ENTER THE LENGTH");
            length = inputObject.nextInt();
            
            output.writeInt(length); //ssend length
            
            String node1;
            String node2;
            
            System.out.println("PLEASE ENTER NODE 1: ");
            node1 = inputObject.next();
            
            System.out.println("PLEASE ENTER NODE 2: ");
            node2 = inputObject.next();
            
            output.writeChar(node1.charAt(0)); //send node1
            output.writeChar(node2.charAt(0)); //send node 2
            
             for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                     output.writeInt(adjMatrixClient[i][j]);
                    
                }
            }
             
             char response;
             response = input.readChar();
             System.out.println(response);
            
            clients.close();
        } 
        catch(IOException e) {
            System.out.println(e);
        }
    }
    
    
   public static void main(String[] args) {
        Client client = new Client("localhost", 4200);
    }
}

