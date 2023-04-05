package kata.jpa.product;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save_Given_NewProduct_Then_Success() {
        Product product = new Product();
        String productName = "shirt";
        product.setName(productName);

        productRepository.save(product);

        val actual =  productRepository.findById(product.getId());
        assertEquals(actual.get(), product);

    }
}
