import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	private Socket socket = null;
	private Thread thread = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private Connection client = null;

	public Client(String host, int port) {

		System.out.println("Connecting");
		// TODO: initialize Socket
		// TODO: Start the Server Thread.
		try {
			socket = new Socket(host, port);
			System.out.println("Connected: " + socket);
			start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		while (thread != null) {

			// TODO: read line from console and send through stream out
			try {
				streamOut.writeUTF(console.readLine());
				streamOut.flush();
			} catch (IOException ioe) {
				System.out.println("Sending error: " + ioe.getMessage());
				try {
					stop();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void handle(String msg) {
		if (msg.equals(".bye")) {
			System.out.println("Good bye. Press RETURN to exit ...");
			try {
				stop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			System.out.println(msg);
	}

	public void start() throws IOException {
		// TODO: setup the input/output streams
		streamOut = new DataOutputStream(socket.getOutputStream());
		console = new DataInputStream(System.in);
		// TODO: Create new Connection
		Connection conn = new Connection(this, socket);
		conn.open();
		// TODO: Start new thread
		if (this.thread == null)
			this.thread = new Thread(this);
		this.thread.start();
	}

	public void stop() throws IOException {
		// TODO: Stop the thread
		if (thread != null)
			thread.interrupt();
		// TODO: Close the streams
		if (streamOut != null)
			streamOut.close();
		// TODO: Close the socket
		if (socket != null)
			socket.close();
		// TODO: Close the client
		if (client != null)
			client.close();
	}

	public static void main(String[] args) throws InterruptedException {
		Client client = new Client("127.0.0.1",27015);
	}
}