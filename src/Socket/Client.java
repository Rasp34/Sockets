package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		
		if (args.length == 0){
			System.out.println("Error 101: the symbol is expected");
			System.exit(0);
			
		}
		
		OutputStream outbound = null;
		BufferedReader inbound = null;
		Socket clientSocket = null;
		
		try {
			clientSocket = new Socket("localhost", 3000);
			System.out.println("Client: " + clientSocket);
			
			outbound = clientSocket.getOutputStream();
			inbound = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			outbound.write((args[0] + "\n").getBytes());
			System.out.println("request " + args[0]);
//			outbound.write(("END\n").getBytes());
			
			String inPrice;
			
			while (true){
				
				inPrice = inbound.readLine();
				
				if ("END".equals(inPrice)) {
					break;
				}
				
				System.out.println(inPrice);
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				inbound.close();
				outbound.flush();
				outbound.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("Error closing stream :" + e.getMessage());
			}

		}
		
		
	}

}
