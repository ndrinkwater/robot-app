package com.stem.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThreadListen implements Runnable {
	
	private int portListen;					// Port to listen on
	private PrintWriter out;				// Output stream
	private BufferedReader in;				// Input stream
	private Socket clientSocket;			// The client
	private ServerSocket serverSocket;		// The server socket
	private boolean isConnected = false;	// Flag for connection
	
	private int listenSleepTime = 1000;		// Listen time for client connections
	
	public SocketThreadListen(int port) {
		portListen = port;
	}
	
	
	public void init() {
		try {
			serverSocket = new ServerSocket(portListen);
		} catch(Exception e) {
			System.err.println("Unable to create ServerSocket on port " + portListen);
		}
	}
	
	/**
	 * Wait until the client connects
	 */
	public void createAndWait() {
		try {
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		} catch(IOException e) {
			// Just catch, this could be part of the waiting process
			isConnected = false;
			
		} catch(Exception e) {
			// Just catch, this could be part of the waiting process
			isConnected = false;
		}
	}

	@Override
	public void run() {
		while(isConnected == false) {
			createAndWait();
			System.out.println("Looking for a client");
			try {
				Thread.sleep(listenSleepTime);
			} catch (InterruptedException e) {
				System.err.println("SocketThreadListen -> InterrupedException caught!");
				e.printStackTrace();
			}
		}
	}
	
}
