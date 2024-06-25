package gift;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;

@Controller
public class GiftController {
	// InMemory 방식으로 저장. ConHashMap 사용
	private final Map<Long, Gift> giftMap = new ConcurrentHashMap<>();

}
