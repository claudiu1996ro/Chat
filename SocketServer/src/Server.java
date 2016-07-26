import java.io.IOException;
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
			System.out.println("Starting server on port: " + port);
			this.server = new ServerSocket(port);
			// TODO: Start the Server Thread.
			this.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
				stop();
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
		if (this.thread == null) {
			this.thread.stop();
			thread = null;
		}
	}

	private int findClient(int ID) {
		// TODO: iterate over the connections
		// TODO: check each connection id and return the index from clients
		// array
		for (int i = 0; i < this.clients.size(); i++)
			if (clients.get(i).getID() == ID)
				return i;
		return -1;
	}

	public synchronized void handle(int ID, String input) {
		// TODO: iterate over the connections and send the input
		if (input.equals(".bye")) {
			clients.get(findClient(ID)).send(".bye");
			remove(ID);
		} else {
			for (int i = 0; i < this.clients.size(); i++) {
				if (clients.get(i).getID() != ID)
					clients.get(i).send(ID + ": " + input);
			}
		}
		// notifyAll();
	}

	public synchronized void remove(int ID) {
		// TODO: find the client
		// TODO: stop the client
		// TODO: remove it from server
		int pos = findClient(ID);
		if (pos >= 0) {
			Connection conn = clients.get(pos);
			System.out.println("Removing client thread " + ID + " at " + pos);
			this.clients.remove(pos);
			try {
				conn.close();
			} catch (IOException ioe) {
				System.out.println("Error closing thread: " + ioe);
			}
			conn.stop();
		}
	}

	private void acceptConnection(Socket socket) {

		if (clients.size() == this.maxConnections) {
			System.out.println("Error connection number!");
			return;
		}

		System.out.println("Client: " + socket.getPort() + " connected.");
		// TODO: create new Connection
		Connection conn = new Connection(this, socket);
		try {
			conn.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.start();
		// TODO: store the Connection
		clients.add(conn);
	}

	public static void main(String args[]) {
		Server server = new Server(50, 27015);
	}
}