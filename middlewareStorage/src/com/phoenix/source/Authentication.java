package com.phoenix.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Authentication { 
	
	PrintWriter print=null;
	
	public  Authentication(String myMachine,int portNo,int channelNo){
		try{
	 Socket myserviceProvider = new Socket(myMachine, 3333);
	 
	 this.print = new PrintWriter(myserviceProvider.getOutputStream(), true);	
	 Map<String, String> keyAuth=new HashMap<String,String>();
	 
	  keyAuth.put("1","ID001");
	  keyAuth.put("2","ID002");
	  keyAuth.put("3","ID003");
	  keyAuth.put("4","ID004");
	  keyAuth.put("5","ID005");
	  keyAuth.put("6","ID006");
	  keyAuth.put("7","ID007");
	  
	 String key= keyAuth.get(channelNo);
	 
	  
	 this.print.println(key);
	 
	 myserviceProvider.close();
	
		}

	catch(IOException e){
		System.out.println("Exception occured in opening connection with service Provider at "+myMachine);
		}	
	}
}
/*
{s
BufferedReader in = new BufferedReader(new InputStreamReader(myserviceProvider.getInputStream()));
System.out.print("Received string: '");
}
*/
/*

.........................................................................................
CLIENT ---


import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args)
	{
		try {
			Socket sock = new Socket("localhost",444);
			SendThread sendThread = new SendThread(sock);
			Thread thread = new Thread(sendThread);thread.start();
			RecieveThread recieveThread = new RecieveThread(sock);
			Thread thread2 =new Thread(recieveThread);thread2.start();
		} catch (Exception e) {System.out.println(e.getMessage());} 
	}
}
class RecieveThread implements Runnable
{
	Socket sock=null;
	BufferedReader recieve=null;
	
	public RecieveThread(Socket sock) {
		this.sock = sock;
	}//end constructor
	public void run() {
		try{
		recieve = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));//get inputstream
		String msgRecieved = null;
		while((msgRecieved = recieve.readLine())!= null)
		{
			System.out.println("From Server: " + msgRecieved);
			System.out.println("Please enter something to send to server..");
		}
		}catch(Exception e){System.out.println(e.getMessage());}
	}//end run
}//end class recievethread

class SendThread implements Runnable
{
	Socket sock=null;
	PrintWriter print=null;
	BufferedReader brinput=null;
	
	public SendThread(Socket sock)
	{
		this.sock = sock;
	}//end constructor
	public void run(){
		try{
		if(sock.isConnected())
		{
			System.out.println("Client connected to "+sock.getInetAddress() + " on port "+sock.getPort());
			this.print = new PrintWriter(sock.getOutputStream(), true);	
		while(true){
			System.out.println("Type your message to send to server..type 'EXIT' to exit");
			brinput = new BufferedReader(new InputStreamReader(System.in));
			String msgtoServerString=null;
			msgtoServerString = brinput.readLine();
			this.print.println(msgtoServerString);
			this.print.flush();
		
			if(msgtoServerString.equals("EXIT"))
			break;
			}//end while
		sock.close();}}catch(Exception e){System.out.println(e.getMessage());}
	}//end run method
}//end class
*/