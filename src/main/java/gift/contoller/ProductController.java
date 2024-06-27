package gift.contoller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
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

import gift.dao.ProductDao;
import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

@Controller

public class ProductController {
	// InMemory 방식으로 저장. ConHashMap 사용
	// private final Map<Long, Product> productRepository = new ConcurrentHashMap<>();
	@Autowired
	private ProductDao productDAO;


	@GetMapping("/admin")
	public String viewProducts(Model model) {
		// model.addAttribute("products", productRepository.values());
		// productDAO.findAll() -> List<ProductResponseDto>, stream() -> Stream<ProductResponseDto>

		model.addAttribute("products", productDAO.findAll().stream().map(ProductResponseDto::from).toList());
		System.out.println(productDAO.findAll().stream().map(ProductResponseDto::from).toList());
		return "admin/mainPage";
	}

	// 상품 등록
	@PostMapping("/api/product")
	@ResponseBody
	public ResponseEntity<?> registerProduct(@RequestBody ProductRequestDto productRequestDto) {
		productDAO.save(productRequestDto.toEntity());
		// if (productRepository.containsKey(productRequestDto.id())) {
		// 	return ResponseEntity.badRequest().build();
		// }
		// productRepository.put(productRequestDto.id(), productRequestDto.toEntity());
		return ResponseEntity.ok().build();
	}

	// 상품 조회, id가 없으면 not found
	@ResponseBody
	@GetMapping("/api/product/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {

		Product product = productDAO.findById(id);
		// if (!productRepository.containsKey(id)) {
		// 	return ResponseEntity.notFound().build();
		// }
		return ResponseEntity.ok(ProductResponseDto.from(product));
	}

	// 상품 수정, id가 없으면 not found
	@PutMapping("/api/product/{id}")
	@ResponseBody
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
		// if (!productRepository.containsKey(id)) {
		// 	return ResponseEntity.notFound().build();
		// }
		// productRepository.put(id, productRequestDto.toEntity());
		productDAO.update(productRequestDto.toEntity());
		return ResponseEntity.ok().build();
	}

	// 상품 삭제 id가 없으면 not found
	@DeleteMapping("/api/product/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		// if (!productRepository.containsKey(id)) {
		// 	return ResponseEntity.notFound().build();
		// }
		// productRepository.remove(id);
		productDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}

	public ProductController() {
	}
}
