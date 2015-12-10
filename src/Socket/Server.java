package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		
		BufferedReader inbound = null;
		OutputStream outbound = null;
		
		try {
			
			serverSocket = new ServerSocket(3000);
			
//			Waiting client request
			System.out.println("waiting for client request...");
			while (true){
				
//				while request
				clientSocket = serverSocket.accept();
				
//				get input stream
				inbound = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
				
				outbound = clientSocket.getOutputStream();
				
				String symbol = inbound.readLine();
				
				String price = (new Double(Math.random()*10).toString());
				outbound.write(("The price of " + symbol + " is " + price + "\n").getBytes());
				
				System.out.println("request for " + clientSocket.getPort() + " has been processed");
				
				outbound.write("END\n".getBytes());
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				
//				Closing streams
				inbound.close();
				outbound.flush();
				outbound.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("ERROR: " + e.getMessage());
			}
			
		}
		
	}

}
