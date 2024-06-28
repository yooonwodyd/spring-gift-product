package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import gift.dao.ProductDao;
import gift.dao.ProductRowMapper;
import gift.domain.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Product product) {
		String sql = "INSERT INTO products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
	}

	@Override
	public Product findById(Long id) {
		String sql = "SELECT * FROM products WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
	}

	@Override
	public List<Product> findAll() {
		String sql = "SELECT * FROM products";
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
		jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
	}

	@Override
	public void deleteById(Long id) {
		String sql = "DELETE FROM products WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}