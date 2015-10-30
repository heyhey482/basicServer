import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class simpleServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket server  = new ServerSocket(8080);
        System.out.println("server : "+ server);
       
        for(int i = 0; i < 10 ; i++){
            
             Socket client = server.accept();
            
             OutputStream out = client.getOutputStream();
            
             out.write("<h1>hello World</h1>".getBytes());
            
             out.flush();
            
            
             Thread.sleep(1000);
            
            
             out.close();
                           
             if(client != null){
                  client.close();
             }
        }
       
        server.close();
	
	}
}
