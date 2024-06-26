package gift;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
	// InMemory 방식으로 저장. ConHashMap 사용
	private final Map<Long, Product> productRepository = new ConcurrentHashMap<>();


	@GetMapping("/admin")
	public String viewProducts(Model model) {
		model.addAttribute("products", productRepository.values());
		return "admin/mainPage";
	}

	// 상품 등록
	@PostMapping("/api/product")
	@ResponseBody
	public ResponseEntity<?> registerProduct(@RequestBody ProductRequestDto productRequestDto) {
		if (productRepository.containsKey(productRequestDto.id())) {
			return ResponseEntity.badRequest().build();
		}
		productRepository.put(productRequestDto.id(), productRequestDto.toEntity());
		return ResponseEntity.ok().build();
	}

	// 상품 조회, id가 없으면 not found
	@ResponseBody
	@GetMapping("/api/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		if (!productRepository.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productRepository.get(id));
	}

	// 상품 전체 조회
	@GetMapping("/api/products")
	@ResponseBody
	public ResponseEntity<Map<Long, Product>> getProducts() {
		return ResponseEntity.ok(productRepository);
	}

	// 상품 수정, id가 없으면 not found
	@PutMapping("/api/product/{id}")
	@ResponseBody
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
		if (!productRepository.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		productRepository.put(id, productRequestDto.toEntity());
		return ResponseEntity.ok().build();
	}

	// 상품 삭제 id가 없으면 not found
	@DeleteMapping("/api/product/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		if (!productRepository.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		productRepository.remove(id);
		return ResponseEntity.ok().build();
	}

	public ProductController() {
	}
}
