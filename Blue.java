/**
*	UDP Client Program
*	Connects to a UDP Server and sends and receives messages from another client.
*   
*	@author: Jared Nagashima
*   @partner: Cole Wright
*/

import java.io.*;
import java.net.*;

class Blue {
	
    public static void main(String args[]) throws Exception
    {

      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

      DatagramSocket clientSocket = new DatagramSocket();

      InetAddress IPAddress = InetAddress.getByName("192.168.1.16");

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
	  
	  String sentence = new String("HELLO");
	  sendData = sentence.getBytes();
	  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
	  clientSocket.send(sendPacket);
	  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	  clientSocket.receive(receivePacket);
	  sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
	  if (sentence.equals("##100##"))
	  {
		System.out.println(sentence + ": Waiting for Red.");
	  }
	  while (!sentence.equals("##200##"))
	  {
		try
		{
		  receivePacket = new DatagramPacket(receiveData, receiveData.length);
		  clientSocket.receive(receivePacket);
		  sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
		}
		catch(Exception e)
		{
			
		}
	  }
	  if (sentence.equals("##200##"))
	  {
	    System.out.println(sentence + ": Red and Blue are connected");
	  }
      
	  while (true)
	  {
		receiveData = new byte[1024];
		receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);
		
        sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
		
		if (sentence.equals("##300##"))
		{
			break;
		}
		
		System.out.println("RED: " + sentence);
		
		sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
	    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        clientSocket.send(sendPacket);
      }
	  System.out.println(sentence + ": Disconnected.");
	  clientSocket.close();
    }
}
