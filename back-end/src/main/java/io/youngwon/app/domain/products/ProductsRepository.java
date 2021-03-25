package io.youngwon.app.domain.products;

import io.youngwon.app.domain.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {


    public List<Products> findByCategories(Categories categories);

}
