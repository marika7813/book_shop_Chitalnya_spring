package com.kizmari.book_shop_Chitalnya_spring.repo;

import com.kizmari.book_shop_Chitalnya_spring.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <Product, Long> {
}
