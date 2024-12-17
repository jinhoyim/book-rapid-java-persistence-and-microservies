package book.apress.rapidjavapersistencemicroservice.eshop05;

import book.apress.rapidjavapersistencemicroservice.eshop05.model.CustomerOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

@SpringBootTest
@Slf4j
class EshopJdbcTemplateCh05ApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	void testThroughJdbcTemplate() {
		String sql = """
				SELECT
				c.customerId, o.orderId, p.productId, c.name as customerName, c.email as customerEmail,
				p.name as productName, p.quantity as quantity, p.price as price
				FROM Customer c inner join `Order` o on c.customerId = o.customerId
					inner join Product p on o.productId = p.productId
				""";
		List<CustomerOrder> customerOrders =
				jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CustomerOrder.class));
		log.info("Order Details: {}", customerOrders);
	}

	@Test
	void testJdbcTemplateWithParam() {
		String sql = """
				SELECT
				c.customerId, o.orderId, p.productId, c.name as customerName, c.email as customerEmail,
				p.name as productName, p.quantity as quantity, p.price as price
				FROM Customer c inner join `Order` o on c.customerId = o.customerId
					inner join Product p on o.productId = p.productId
				where c.name = ?
				""";
		CustomerOrder customerOrder = jdbcTemplate.queryForObject(sql, rowMapper(), "Raj");
		log.info("Order: {}", customerOrder);
	}

	@Test
	void testNamedParameterJdbcTemplate() {
		String sql = """
				SELECT
				c.customerId, o.orderId, p.productId, c.name as customerName, c.email as customerEmail,
				p.name as productName, p.quantity as quantity, p.price as price
				FROM Customer c inner join `Order` o on c.customerId = o.customerId
					inner join Product p on o.productId = p.productId
				where p.name = :productName and p.quantity > :quantity
				""";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("productName", "Laptop")
				.addValue("quantity", 5);
		CustomerOrder customerOrder = namedParameterJdbcTemplate.queryForObject(sql, param, rowMapper());
		log.info("Order: {}", customerOrder);
	}

	private RowMapper<CustomerOrder> rowMapper() {
		return (rs, rowNum) -> {
			CustomerOrder order = new CustomerOrder();
			order.setCustomerId(rs.getLong("customerId"));
			order.setOrderId(rs.getLong("orderId"));
			order.setProductId(rs.getLong("productId"));

			order.setCustomerName(rs.getString("customerName"));
			order.setCustomerEmail(rs.getString("customerEmail"));
			order.setProductName(rs.getString("productName"));
			order.setQuantity(rs.getInt("quantity"));
			order.setPrice(rs.getInt("price"));

			return order;
		};
	}
}
