/**
*	UDP Client Program
*	Connects to a UDP Server
*	Receives a line of input from the keyboard and sends it to the server
*	Receives a response from the server and displays it.
*
*	@author: Cole Wright
@	version: 2.1
*/

import java.io.*;
import java.net.*;

class BlueClient {
	
    public static void main(String args[]) throws Exception
    {
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

      DatagramSocket clientSocket = new DatagramSocket();

      InetAddress IPAddress = InetAddress.getByName("localhost");

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      String sentence;
      DatagramPacket sendPacket;
      DatagramPacket receivePacket;

      while (true)
      {
        sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        if (sentence == "Goodbye")
        {
          break;
        }

        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        sentence = new String(receivePacket.getData());
        System.out.println("FROM OTHER PARTICIPANT:" + sentence);
      }
      clientSocket.close();
    }
}
