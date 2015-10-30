import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher {
	
	private final int HEADER_SIZE = 6;
	
	public void dispatch(ServerSocket serverSocket){
		
		try {
			Socket socket = serverSocket.accept();
			demultiplex(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void dispatch(ServerSocket serverSocket,HandleMap handleMap){
		
		try {
			Socket socket = serverSocket.accept();
			demultiplex(socket, handleMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void demultiplex(Socket socket) {
		
		try {
			InputStream inputStream = socket.getInputStream();
			
			byte[] buffer = new byte[HEADER_SIZE];
			inputStream.read(buffer);
			String header = new String(buffer);
			
			switch (header) {
			case "0x5001":
				StreamSayHelloEventHandler sayHelloProtocol = new StreamSayHelloEventHandler();
				sayHelloProtocol.handleEvent(inputStream);
				break;
			case "0x6001":
				StreamUpdateProfileEventHandler updateProfileProtocol = new StreamUpdateProfileEventHandler();
				updateProfileProtocol.handleEvent(inputStream);
				break;
			default:
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void demultiplex(Socket socket, HandleMap handleMap) {
		
		try {
			InputStream inputStream = socket.getInputStream();
			
			byte[] buffer = new byte[HEADER_SIZE];
			inputStream.read(buffer);
			String header = new String(buffer);
			
			handleMap.get(header).handleEvent(inputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
