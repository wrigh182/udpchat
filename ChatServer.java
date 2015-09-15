/**
*	UDP Server Program
*	Listens on 2 UDP ports
*	Receives a line of input from one UDP client
*	Sends the line of input to the other UDP client
*
*	@author: Cole Wright
@	version: 2.0
*/

import java.io.*;
import java.net.*;

class UDPServer {
	
  public static void main(String args[]) throws Exception
    {
    DatagramSocket serverSocket = null;
	int portRed = 0;
	int portBlue = 0;
	InetAddress IPAddressRed;
	InetAddress IPAddressBlue;
	  
	try
		{
			serverSocket = new DatagramSocket(9876);
		}
	
	catch(Exception e)
		{
			System.out.println("Failed to open UDP socket");
			System.exit(0);
		}

      byte[] receiveData = new byte[1024];
      byte[] sendData  = new byte[1024];

      while(true)
        {
          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		  
          serverSocket.receive(receivePacket);
		  
          String sentence = new String(receivePacketRed.getData());
		  
		  if (sentence.equals("Hello"))
		  {
			if (IPAddressRed == NULL)
			{
				IPAddressRed = receivePacket.getAddress();
				portRed = receivePacket.getPort();
			}
			else if (IPAddressBlue == NULL)
			{
				IPAddressBlue = receivePacket.getAddress();
				portBlue = receivePacket.getPort();
			}
		  }
		  
		  else
		  {
			  
		  }

    
		  
		  System.out.println("from client: " + capitalizedSentence);

          sendData = capitalizedSentence.getBytes();

          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

          serverSocket.send(sendPacket);
        }
    }
}
