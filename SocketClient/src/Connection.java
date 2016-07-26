import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

	private Socket socket = null;
	private Client client = null;
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;

	public Connection(Client client, Socket socket) {
		this.client = client;
		this.socket = socket;
		try {
			open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start();
	}

	public void open() throws IOException {
		// TODO: open stream in
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}

	public void close() throws IOException {
		// TODO: close stream in
		if (streamIn != null)
			streamIn.close();
	}

	public void run() {
		// TODO: infinite loop that takes the console input and calls
		// client.handle
		while (true) {
			try {
				client.handle(streamIn.readUTF());
			} catch (IOException ioe) {
				System.out.println("Listening error: " + ioe.getMessage());
				try {
					client.stop();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
