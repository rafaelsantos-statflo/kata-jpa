package kata.jpa.product;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
        productRepository.deleteAll();
    }

    @Test
    void save_Given_NewProduct_Then_Success() {
        Product product = new Product();
        String productName = "shirt";
        product.setName(productName);

        productRepository.save(product);

        val actual =  productRepository.findById(product.getId());
        assertEquals(actual.get(), product);

    }

    @Test
    void save_Given_NewProduct_WithVariant_Then_Success() {
        Product product = new Product();
        String productName = "shirt";
        product.setName(productName);
        Variant variant = new Variant();
        variant.setName("pink");
        product.addVariant(variant);

        productRepository.save(product);

        val actual =  productRepository.findById(product.getId());
        assertEquals(actual.get().getId(), product.getId());
        assertEquals(1,product.getVariants().size());

    }

    @Test
    void pagination_Given_5ElementsAndPageSize2AndPageNumber1_Then_ReturnsElement1And2() {
        List<Product> products = IntStream
                .range(1, 5)
                .mapToObj(i -> {
                    Product product = new Product();
                    String productName = "p" + i;
                    product.setName(productName);
                    return product;
                })
                .map(productRepository::save)
                .toList();

        var page = PageRequest.of(0, 3, Sort.by("name"));
        var actual = productRepository.findAll(page).stream().toList();

        assertEquals(3, actual.size());
        assertEquals(products.get(0).getName(), actual.get(0).getName());
        assertEquals(products.get(1).getName(), actual.get(1).getName());
        assertEquals(products.get(2).getName(), actual.get(2).getName());
    }


}
