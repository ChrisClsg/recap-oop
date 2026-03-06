package de.clsg;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

  @Test
  void placeOrder_success_reducesStock_andStoresOrder() {
    ProductRepo pr = new ProductRepo();
    Product prod = new Product(new BigDecimal("10.00"), 10, "Bosch", "Werkzeuge", "400...", "P001", "Akkuschrauber");
    pr.addProduct(prod);

    OrderListRepo or = new OrderListRepo();
    ShopService shop = new ShopService(pr, or);

    Order o = shop.placeOrder(3, "P001");

    assertNotNull(o);
    assertEquals(1, or.getAll().size());
    assertEquals(7, pr.getById("P001").stock());
  }

  @Test
  void placeOrder_returnsNull_whenProductNotFound() {
    ProductRepo pr = new ProductRepo();
    OrderListRepo or = new OrderListRepo();
    ShopService shop = new ShopService(pr, or);

    Order o = shop.placeOrder(1, "NOPE");

    assertNull(o);
    assertEquals(0, or.getAll().size());
  }

  @Test
  void placeOrder_returnsNull_whenNotEnoughStock() {
    ProductRepo pr = new ProductRepo();
    Product prod = new Product(new BigDecimal("10.00"), 2, "Bosch", "Werkzeuge", "400...", "P001", "Akkuschrauber");
    pr.addProduct(prod);

    OrderListRepo or = new OrderListRepo();
    ShopService shop = new ShopService(pr, or);

    Order o = shop.placeOrder(5, "P001");

    assertNull(o);
    assertEquals(0, or.getAll().size());
    assertEquals(2, pr.getById("P001").stock());
  }
}
