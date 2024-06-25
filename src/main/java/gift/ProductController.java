package gift;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	// InMemory 방식으로 저장. ConHashMap 사용
	private final Map<Long, Product> productRepository = new ConcurrentHashMap<>();

	// 선물 등록
	@PostMapping("/api/product")
	public ResponseEntity<?> registerProduct(ProductRequestDto productRequestDto) {
		// Id 중복의 경우 다음과 같은 로직을 추가할 수 있다.
		// if (giftMap.containsKey(giftRequestDto.id())) {
		//	return ResponseEntity.badRequest().build();
		// }
		productRepository.put(productRequestDto.id(), productRequestDto.toEntity());
		return ResponseEntity.ok().build();
	}

	// 선물 조회
	public ResponseEntity<Product> getProduct(Long id) {
		return ResponseEntity.ok(productRepository.get(id));
	}
}
