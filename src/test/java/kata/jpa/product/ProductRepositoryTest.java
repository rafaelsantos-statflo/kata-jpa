package kata.jpa.product;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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


}
