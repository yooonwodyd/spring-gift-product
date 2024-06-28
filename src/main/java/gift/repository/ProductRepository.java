package gift.dao;

import java.util.List;

import gift.domain.Product;

public interface ProductDao {
	void save(Product product);
	Product findById(Long id);
	List<Product> findAll();
	void update(Product product);
	void deleteById(Long id);
}
