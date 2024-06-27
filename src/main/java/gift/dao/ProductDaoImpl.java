package gift.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gift.domain.Product;
import gift.utils.JdbcUtils;

@Repository
public class ProductDAO {

	@Autowired
	private JdbcUtils jdbcUtils;

	public void save(Product product) {
		String sql = "INSERT INTO products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";

		try (Connection conn = jdbcUtils.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setLong(3, product.getPrice());
			pstmt.setString(4, product.getImageUrl());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error saving product", e);
		}
	}

	public Product findById(Long id) {
		String sql = "SELECT * FROM products WHERE id = ?";

		try (Connection conn = jdbcUtils.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return Product.of(rs.getLong("id"), rs.getString("name"), rs.getLong("price"), rs.getString("imageUrl"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding product", e);
		}
	}

	public List<Product> findAll() {
		String sql = "SELECT * FROM products";
		List<Product> products = new ArrayList<>();

		try (Connection conn = jdbcUtils.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				products.add(Product.of(rs.getLong("id"), rs.getString("name"), rs.getLong("price"), rs.getString("imageUrl")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error retrieving products", e);
		}

		return products;
	}

	public void update(Product product) {
		String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";

		try (Connection conn = jdbcUtils.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, product.getName());
			pstmt.setLong(2, product.getPrice());
			pstmt.setString(3, product.getImageUrl());
			pstmt.setLong(4, product.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error updating product", e);
		}
	}
}