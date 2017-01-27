import java.net.*;
import java.io.*;
public class TCPServer1
{
    public static void main(String[] args)
    {
        try{
            String dataFromClient,upperCaseData;
            //6789 is the port number on the machine running the server side application. No ip mentioned.Http protocol always use port 80
            //because server and client are on the same system so the same mac address. We use a random port number that is not used by any other network application.
            ServerSocket serverSocket=new ServerSocket(6789);//Responsible for 3 way handshaking if any client connects
            //Wait for incoming connections from telnet client(TCP client)
            Socket connectionSocket=serverSocket.accept();//Responsible for data transfer(Blocking call-keep waiting at this method until any client connects to the server)
            //After the handhsaking is complete and the connnection has been established
            //We need to add an input stream to get message from the client
            BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //Now we can read the input from the client
        
            //We also need to add an output stream so that we can process the input and send some output to the Client.
            DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
            //Read the data and apply operation(converting to uppercase)
            dataFromClient=inFromClient.readLine();
            while(dataFromClient!=null)
            {
                upperCaseData=dataFromClient.toUpperCase();//Convert to uppercase
                outToClient.writeBytes(upperCaseData);//Send the server side message to the client
                dataFromClient=inFromClient.readLine();//Process the next line of input from the client
            }
            outToClient.flush();//Flush the output stream
            connectionSocket.close();//Close the connection socket
        }catch(Exception e){System.out.println("Some Error");}
    }//End Of Main
}
