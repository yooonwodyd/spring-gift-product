package gift;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftController {
	// InMemory 방식으로 저장. ConHashMap 사용
	private final Map<Long, Gift> giftMap = new ConcurrentHashMap<>();

	// 선물 등록
	public ResponseEntity<?> registerGift(GiftRequestDto giftRequestDto) {
		// Id 중복의 경우 다음과 같은 로직을 추가할 수 있다.
		// if (giftMap.containsKey(giftRequestDto.id())) {
		//	return ResponseEntity.badRequest().build();
		// }
		giftMap.put(giftRequestDto.id(), giftRequestDto.toEntity());
		return ResponseEntity.ok().build();
	}

}
