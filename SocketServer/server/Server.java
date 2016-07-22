package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	// Connected clients
	private Connection clients[] = null;
	// Server Socket
	private ServerSocket server = null;
	// Accept clients in a separate thread.
	private Thread thread = null;
	
	public Server(int maxConnections, int port){
		this.clients = new Connection[maxConnections];
		
		//TODO: initialize ServerSocket
		//TODO: Start the Server Thread.
	}
	
	@Override
	public void run() {
		// Accept connections
	}
	
	public void start() {
		//TODO: start new server thread
	}

	public void stop() {
		//TODO: stop server thread
	}

	private int findClient(int ID) {
		//TODO: iterate over the connections 
		//TODO: check each connection id and return the index from clients array
		return -1;
	}

	public void handle(int ID, String input) {
		//TODO: iterate over the connections and send the input
	}

	public void remove(int ID) {
		//TODO: find the client
		//TODO: stop the client
		//TODO: remove it from server
	}

	private void acceptConnection(Socket socket) {
		//TODO: create new Connection
		//TODO: store the Connection
	}

	public static void main(String args[]) {
		// declaration section:
		// declare a server socket and a client socket for the server
		// declare an input and an output stream
		ServerSocket echoServer = null;
		String line;
		DataInputStream is;
		PrintStream os;
		Socket clientSocket = null;
		// Try to open a server socket on port 9999
		// Note that we can't choose a port less than 1023 if we are not
		// privileged users (root)
		try {
			echoServer = new ServerSocket(8080);
		} catch (IOException e) {
			System.out.println(e);
		}
		// Create a socket object from the ServerSocket to listen and accept
		// connections.
		// Open input and output streams
		try {
			clientSocket = echoServer.accept();
			System.out.println(clientSocket.toString());
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
			// As long as we receive data, echo that data back to the client.
			while (true) {
				line = is.readLine();
				os.println(line);
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}