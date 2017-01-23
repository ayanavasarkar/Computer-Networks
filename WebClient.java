import java.io.*;
import java.net.*;
import java.util.*;
public class WebClient
{
    public static void main(String[] args)
    {
        //Create a client socket and ask it to do 3 way handshake with server socket of oracle server
        try
        {
            Socket clientSocket=new Socket("23.38.230.138",80);
            //Attach ouput and input stream to the socket of our client
            DataOutputStream dataToServer=new DataOutputStream(clientSocket.getOutputStream());
            //InputStreamReader to get the char data from raw input stream
            InputStreamReader dataFromServer=new InputStreamReader(clientSocket.getInputStream());
            //To buffer the input stream line by line
            BufferedReader stringInputFromServer=new BufferedReader(dataFromServer);
            //The http request headers to be sent to the server
            String GetHeader="Get  /index.html  HTTP/1.1"+"\n";
            String hostHeader="Host: www.oracle.com"+"\n";
            String endOfHeader="\n";
            //Writing the HTTP REQUEST headers to the output stream
            dataToServer.writeBytes(GetHeader);
            dataToServer.writeBytes(hostHeader);
            dataToServer.writeBytes(endOfHeader);
            //Start getting the HTTP response headers from the server side
            String lineFromServer=stringInputFromServer.readLine();
            //Create a file to save the headers and html
            PrintWriter out = new PrintWriter("lol.txt");
            while(lineFromServer!=null)
            {
                //Write to the file
                out.println(lineFromServer);
                System.out.println(lineFromServer);
                lineFromServer=stringInputFromServer.readLine();
                
            }
            out.close();
        }catch(Exception e){}
    }
}
