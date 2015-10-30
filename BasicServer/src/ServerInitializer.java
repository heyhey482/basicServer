import java.io.File;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class ServerInitializer {
	
	public static void main(String[] args) {
		int port = 5000;
		System.out.println("Server ON : " + port);
		
		Reactor reactor = new Reactor(port);
		
		try {
			Serializer serializer =  new Persister();
			File source = new File("HandlerList.xml");
			ServerListData serverListData = serializer.read(ServerListData.class, source);
			
			for (HandlerListData handlerListData : serverListData.getServer()) {
				
				if("server1".equals(handlerListData.getName())) {
					List<HandlerData> handlerList = handlerListData.getHandler();
					for (HandlerData handler : handlerList) {
						try {
							reactor.registerHandler(handler.getHeader(), (EventHandler) Class.forName(handler.getHandler()).newInstance());
						} catch (Exception e) {
							e.getStackTrace();
						}
					}
					break;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
/*		reactor.registerHandler(new StreamSayHelloEventHandler());
		reactor.registerHandler(new StreamUpdateProfileEventHandler());*/
		
		reactor.startServer();
		
	}
}
