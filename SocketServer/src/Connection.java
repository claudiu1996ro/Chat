import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection extends Thread {
	private static final String String = null;
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
		BufferedReader br = null;
		// TODO: send message to output pipe
		try {
			String input = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			streamOut.writeBytes(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			streamOut.writeBytes("\n.\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			streamOut.writeBytes("QUIT");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getID() {
		return ID;
	}

	public void run() {
		// TODO: infinite loop that takes the console input and calls server.handle
		while(true){
			server.handle(ID,String);
			System.out.print("Enter String\n");
		}
	}

	public void open() throws IOException {
		// TODO: Start input/output pipes
		streamOut = new DataOutputStream(this.socket.getOutputStream());
		streamIn = new DataInputStream(this.socket.getInputStream());
	}

	public void close() throws IOException {
		// TODO: close soket and pipes
		streamOut.close();
		streamIn.close();
	}
}
