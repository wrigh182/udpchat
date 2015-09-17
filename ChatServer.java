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

class ChatServer {
	
  public static void main(String args[]) throws Exception
    {
    DatagramSocket serverSocket = null;
	int portRed = 0;
	int portBlue = 0;
	InetAddress IPAddressRed = InetAddress.getByName("0");
	InetAddress IPAddressBlue = InetAddress.getByName("0");
	String sentence;
	DatagramPacket sendPacket;
	  
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
			// Receive message
          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
          serverSocket.receive(receivePacket);
          sentence = new String(receivePacket.getData());
		  
		  System.out.println(sentence);
		  
			// assign a client to red
			if (portRed == 0)
			{
				System.out.println("New Red");	
				IPAddressRed = receivePacket.getAddress();
				portRed = receivePacket.getPort();

				// send other user is connected code to both clients
				if (portBlue != 0)
				{
					sentence = "##200##";
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressRed, portRed);
					serverSocket.send(sendPacket);
					
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressBlue, portBlue);
					serverSocket.send(sendPacket);
				}
				// send waiting for other user code
				else
				{
					sentence = "##100##";
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressRed, portRed);
					serverSocket.send(sendPacket);
				}
			}
			
			// assign a client to blue
			else if (portBlue == 0)
			{
				System.out.println("New Blue");	
				IPAddressBlue = receivePacket.getAddress();
				portBlue = receivePacket.getPort();
				
				// Send other user is connected code to both clients
				if (portRed != 0)
				{
					sentence = "##200##";
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressRed, portRed);
					serverSocket.send(sendPacket);
					
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressBlue, portBlue);
					serverSocket.send(sendPacket);
				}
				// Send waiting for other user code
				else
				{
					sentence = "##100##";
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressBlue, portBlue);
					serverSocket.send(sendPacket);
				}
			}
			
			// Both clients are ejected if one exits
			else if (sentence.equals("##EXIT##"))
			{
				
				sentence = "##300##";
				sendData = sentence.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressRed, portRed);
				serverSocket.send(sendPacket);

				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressBlue, portBlue);
				serverSocket.send(sendPacket);

				IPAddressRed = InetAddress.getByName("0");
				portRed = 0;
				IPAddressBlue = InetAddress.getByName("0");
				portBlue = 0;
				
			}			 
			  
			// Conversation between clients  
			else if (portBlue != 0 && portRed != 0)
			{
				// If red send to blue
				if (receivePacket.getAddress() == IPAddressRed && receivePacket.getPort() == portRed)
				{
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressBlue, portBlue);
					serverSocket.send(sendPacket);
				}
				// If blue send to red
				else if (receivePacket.getAddress() == IPAddressBlue && receivePacket.getPort() == portBlue)
				{
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressRed, portRed);
					serverSocket.send(sendPacket);
				}
			}
        }
    }
}
