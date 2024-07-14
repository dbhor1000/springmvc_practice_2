package javacode_dk.springmvc_practice.DTOTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javacode_dk.springmvc_practice.DTO.UserViews;
import javacode_dk.springmvc_practice.model.Order;
import javacode_dk.springmvc_practice.model.OrderStatus;
import javacode_dk.springmvc_practice.model.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserEntityJsonViewTest {

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    @Test
    public void testUserDtoNameAndAddressView() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setOrders(new ArrayList<>());

        String json = mapper.writerWithView(UserViews.UserDtoNameAndAddress.class).writeValueAsString(user);
        assertTrue(json.contains("John Doe"));
        assertTrue(json.contains("123 Main St"));
        assertFalse(json.contains("john.doe@example.com"));
        assertFalse(json.contains("orders"));
    }

    @Test
    public void testUserDtoWithoutOrdersView() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setOrders(new ArrayList<>());

        String json = mapper.writerWithView(UserViews.UserDtoWithoutOrders.class).writeValueAsString(user);
        assertTrue(json.contains("John Doe"));
        assertTrue(json.contains("123 Main St"));
        assertTrue(json.contains("john.doe@example.com"));
        assertFalse(json.contains("orders"));
    }

    @Test
    public void testUserDtoWithOrdersView() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");

        Order order = new Order();
        order.setId(1L);
        order.setGoodsOrdered("Product 1");
        order.setTotalAmount(new BigDecimal("100.00"));
        order.setOrderStatus(OrderStatus.COLLECTED);
        order.setBuyer(user);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        user.setOrders(orders);

        String json = mapper.writerWithView(UserViews.UserDtoWithOrders.class).writeValueAsString(user);
        assertTrue(json.contains("John Doe"));
        assertTrue(json.contains("123 Main St"));
        assertTrue(json.contains("john.doe@example.com"));
        assertTrue(json.contains("orders"));
        assertTrue(json.contains("Product 1"));
        assertTrue(json.contains("100.00"));
        assertTrue(json.contains("COLLECTED"));
    }

    @Test
    public void testDeserializationWithUserDtoNameAndAddressView() throws Exception {
        String json = "{\"name\":\"John Doe\", \"address\":\"123 Main St\"}";

        UserEntity user = mapper.readerWithView(UserViews.UserDtoNameAndAddress.class).forType(UserEntity.class).readValue(json);
        assertEquals("John Doe", user.getName());
        assertEquals("123 Main St", user.getAddress());
        assertNull(user.getEmail());
        assertTrue(user.getOrders().isEmpty());
    }

    @Test
    public void testDeserializationWithUserDtoWithoutOrdersView() throws Exception {
        String json = "{\"name\":\"John Doe\", \"email\":\"john.doe@example.com\", \"address\":\"123 Main St\"}";

        UserEntity user = mapper.readerWithView(UserViews.UserDtoWithoutOrders.class).forType(UserEntity.class).readValue(json);
        assertEquals("John Doe", user.getName());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("john.doe@example.com", user.getEmail());
        assertTrue(user.getOrders().isEmpty());
    }

    @Test
    public void testDeserializationWithUserDtoWithOrdersView() throws Exception {
        String json = "{\"name\":\"John Doe\", \"email\":\"john.doe@example.com\", \"address\":\"123 Main St\", \"orders\":[{\"id\":1, \"goodsOrdered\":\"Product 1\", \"totalAmount\":100.00, \"orderStatus\":\"COLLECTED\"}]}";

        UserEntity user = mapper.readerWithView(UserViews.UserDtoWithOrders.class).forType(UserEntity.class).readValue(json);
        assertEquals("John Doe", user.getName());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("john.doe@example.com", user.getEmail());
        assertNotNull(user.getOrders());
        assertEquals(1, user.getOrders().size());
        assertEquals("Product 1", user.getOrders().get(0).getGoodsOrdered());
        assertEquals(new BigDecimal("100.00"), user.getOrders().get(0).getTotalAmount());
        assertEquals(OrderStatus.COLLECTED, user.getOrders().get(0).getOrderStatus());
    }
}