
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

	// Connected clients
	private List<Connection> clients = null;
	// Server Socket
	private ServerSocket server = null;
	// Accept clients in a separate thread.
	private Thread thread = null;

	private int maxConnections;

	public Server(int maxConnections, int port) {

		this.maxConnections = maxConnections;
		this.clients = new ArrayList<>();

		// TODO: initialize ServerSocket
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: Start the Server Thread.
		this.start();

	}

	@Override
	public void run() {
		// TODO: Accept connections
		while (true) {
			try {
				Socket client = server.accept();
				acceptConnection(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void start() {
		// TODO: start new server thread
		if (this.thread == null)
			this.thread = new Thread(this);
		this.thread.start();
	}

	public void stop() {
		// TODO: stop server thread
		if (this.thread == null)
			this.thread.stop();
		thread = null;
	}

	private int findClient(int ID) {
		// TODO: iterate over the connections
		// TODO: check each connection id and return the index from clients
		// array
		for (int i = 0; i < this.maxConnections; i++)
			if (clients.get(i).getID() == ID)
				return i;
		return -1;
	}

	public void handle(int ID, String input) {
		// TODO: iterate over the connections and send the input
		if (input.equals(".bye")) {
			clients.get(findClient(ID)).send(".bye");
			remove(ID);
		} else
			for (int i = 0; i < this.maxConnections; i++) {
				if (clients.get(i).getID() != ID)
					clients.get(i).send(ID + ": " + input);
			}
		notifyAll();
	}

	public void remove(int ID) {
		// TODO: find the client
		// TODO: stop the client
		// TODO: remove it from server
		int pos = findClient(ID);
		if (pos >= 0) {
			System.out.println("Removing client thread " + ID + " at " + pos);
		}
	}

	private void acceptConnection(Socket socket) {

		if (clients.size() == this.maxConnections) {
			System.out.println("Error connection number!");
			return;
		}

		// TODO: create new Connection

		// TODO: store the Connection
		clients.add(new Connection(this, socket));

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