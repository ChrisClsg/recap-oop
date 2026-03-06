package de.clsg;

import java.math.BigDecimal;

public record Product(
  BigDecimal price,
  int stock,
  String brand,
  String category,
  String ean,
  String id,
  String name
) {

}
