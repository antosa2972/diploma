package by.bsuir.phoneshop.core;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import by.bsuir.phoneshop.core.dao.OrderDao;
import by.bsuir.phoneshop.core.dao.PhoneDao;
import by.bsuir.phoneshop.core.models.Order;
import by.bsuir.phoneshop.core.models.OrderItem;
import by.bsuir.phoneshop.core.models.Phone;
import by.bsuir.phoneshop.core.models.enums.OrderStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/context/applicationContext-core-test.xml")
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class
})
public class JdbcOrderDaoIntTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderDao jdbcOrderDao;

    @Autowired
    private PhoneDao jdbcPhoneDao;

    private static Long id = 1L;
    private static Long quantity = 1L;
    private static Order order;
    private static OrderItem orderItem;
    private static Phone phone;

    @BeforeClass
    public static void setupBeforeClass() {
        String brand = "Apple";
        String model = "Iphone 6";
        phone = new Phone();
        phone.setBrand(brand);
        phone.setModel(model);
        phone.setId(id);
        order = new Order();
        order.setStatus(OrderStatus.NEW);
        orderItem = new OrderItem(id, phone, order, quantity);
    }

    @Test
    public void saveTest() {
        deleteFromTables(jdbcTemplate, "orders");
        jdbcOrderDao.save(order);
        int rows = countRowsInTable(jdbcTemplate, "orders");
        assertEquals(rows, 1);
    }

    @Test
    public void getTest() {
        deleteFromTables(jdbcTemplate, "orders");
        order.setId(id);
        jdbcOrderDao.save(order);
        Optional<Order> orderFromTable = jdbcOrderDao.get(order.getId());
        orderFromTable.ifPresent(receivedOrder -> assertEquals(receivedOrder.getId(), order.getId()));
    }

    @Test
    public void getDeepTest() {
        deleteFromTables(jdbcTemplate, "orders");
        order.setId(id);
        order.getOrderItems().add(orderItem);
        jdbcPhoneDao.save(phone);
        jdbcOrderDao.save(order);
        Optional<Order> orderFromTable = jdbcOrderDao.get(order.getId());
        orderFromTable.ifPresent(receivedOrder -> assertEquals(receivedOrder.getOrderItems().get(0).getPhone().getId(),
                phone.getId()));
    }
}
