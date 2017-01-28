import java.io.*;
import java.net.*;
//Handles communication from client and to client.
class ClientThread implements Runnable
{   Socket connectionSocket;
    public ClientThread(Socket client)
    {//Get the connection socket of the client connected
        connectionSocket=client;
    }
    public void run()
    {   
        try{
         String dataFromClient,upperCaseData;
         BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
         DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
            //Read the data and apply operation(converting to uppercase)
            dataFromClient=inFromClient.readLine();
            while(!dataFromClient.equals("lol"))
            {
                upperCaseData=dataFromClient.toUpperCase();//Convert to uppercase
                outToClient.writeBytes(upperCaseData);//Send the server side message to the client
                dataFromClient=inFromClient.readLine();//Process the next line of input from the client
            }
            outToClient.flush();//Flush the output stream
            connectionSocket.close();//Close the connection socket
    
        }catch(Exception e){System.out.println("Some error");}
    }
}
public class MultiThreadedServer
{
    public static void main(String[] args)
    {
        try{
            //Welcome Socket only for the server to listen for 3 way handshake between server.
            ServerSocket serverSocket=new ServerSocket(6789);
            while(true)
            {
                //Client Socket to connect to the server. For transfer of all data between client and server
                Socket connectionSocket=serverSocket.accept();
                Thread temp=new Thread(new ClientThread(connectionSocket));//Pass the clientSocket connected to the ClientThreadClass as a connection to server
                temp.start();//Start the client thread
            }
        }catch(Exception e){System.out.println("Another error");}
    }//End of main
}
