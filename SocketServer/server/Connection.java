package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
	private Server server = null;
	private Socket socket = null;
	private int ID = -1;
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;

	public Connection(Server server, Socket socket) {
		super();
		this.server = server;
		this.socket = socket;
		ID = socket.getPort();
	}

	public void send(String msg) {
		// TODO: send message to output pipe
	}

	public int getID() {
		return ID;
	}

	public void run() {
		// TODO: infinite loop that takes the console input and calls server.handle
	}

	public void open() throws IOException {
		// TODO: Start input/output pipes
	}

	public void close() throws IOException {
		// TODO: close soket and pipes
	}
}
