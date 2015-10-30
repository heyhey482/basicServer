import java.net.ServerSocket;

/**
 * @author user-18
 * @설명 HandleMap 관리
 */
public class Reactor {
	
	private ServerSocket serverSocket;
	private HandleMap handleMap;

	public Reactor(int port) {
		handleMap = new HandleMap();
		try {
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startServer() {
		
		Dispatcher dispatcher = new Dispatcher();
		
		while (true) {
			dispatcher.dispatch(serverSocket,handleMap);
		}
	}
	
	public void registerHandler(EventHandler handler) {
		
		// 핸들러 해더, 핸들러 등록
		handleMap.put(handler.getHandler(), handler);
	}
	
	public void registerHandler(String header, EventHandler handler) {
		handleMap.put(header, handler);
	}
	
	public void removeHandler(EventHandler handler) {
		handleMap.remove(handler.getHandler());
	}
	
}
