package client;

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
	}

	public void run() {

		while (thread != null) {

			// TODO: read line from console and send through stream out
		}
	}

	public void handle(String msg) {
		if (msg.equals(".bye")) {
			System.out.println("Good bye. Press RETURN to exit ...");
			stop();
		} else
			System.out.println(msg);
	}

	public void start() throws IOException {
		// TODO: setup the input/output streams
		
		// TODO: Create new Connection
		 
		// TODO: Start new thread
	}

	public void stop() {
		// TODO: Stop the thread

		// TODO: Close the streams

		// TODO: Close the socket

		// TODO: Close the client
	}

	public static void main(String[] args) throws InterruptedException {
		// declaration section:
		// smtpClient: our client socket
		// os: output stream
		// is: input stream
		Socket smtpSocket = null;
		DataOutputStream os = null;
		DataInputStream is = null;
		// Initialization section:
		// Try to open a socket on port 25
		// Try to open input and output streams
		try {
			smtpSocket = new Socket("127.0.0.1", 27015);
			os = new DataOutputStream(smtpSocket.getOutputStream());
			is = new DataInputStream(smtpSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname");
			e.printStackTrace();
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to: hostname");
			e.printStackTrace();
		}
		Thread.sleep(1000);
		// If everything has been initialized then we want to write some data
		// to the socket we have opened a connection to on port 25
		if (smtpSocket != null && os != null && is != null) {
			try {
				// The capital string before each colon has a special meaning to
				// SMTP
				// you may want to read the SMTP specification, RFC1822/3
				// os.writeBytes("HELO\n");
				// os.writeBytes("MAIL From: minudragomirescu95@gmail.com.ca\n");
				// os.writeBytes("RCPT To: ionut96claudiu@gmail.com.ca\n");
				// os.writeBytes("DATA:\n");
				// os.writeBytes("From: minudragomirescu95@gmail.com.ca\n");
				// os.writeBytes("Subject: PRACTICA 22.07.2016\n");
				// os.writeBytes("Noroc tinere !\n"); // message body
				// os.writeBytes("\n.\n");
				// os.writeBytes("QUIT");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				System.out.print("Enter String\n");
				String s = br.readLine();
				os.writeBytes(s);
				os.writeBytes("\n.\n");
				os.writeBytes("QUIT");
				// keep on reading from/to the socket till we receive the "Ok"
				// from SMTP,
				// once we received that then we want to break.
				String responseLine;
				while ((responseLine = is.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("Ok") != -1) {
						break;
					}
				}

				// clean up:
				// close the output stream
				// close the input stream
				// close the socket
				os.close();
				is.close();

				smtpSocket.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}
}