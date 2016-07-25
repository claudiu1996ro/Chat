package client;

import java.io.DataInputStream;
import java.net.Socket;

public class Connection extends Thread {

	private Socket socket = null;
	private Client client = null;
	private DataInputStream streamIn = null;

	public Connection(Client client, Socket socket) {
		this.client = client;
		this.socket = socket;
		open();
		start();
	}

	public void open() {
		// TODO: open stream in
	}

	public void close() {
		// TODO: close stream in
	}

	public void run() {		
		// TODO: infinite loop that takes the console input and calls
		// client.handle
	}
}
