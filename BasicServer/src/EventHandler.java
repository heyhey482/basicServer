import java.io.InputStream;

public interface EventHandler {
	
	// 키 값을 등록할 핸들러
	public String getHandler();
	
	// 데이터 바인딩할 함수
	public void handleEvent(InputStream inputStream);
}
