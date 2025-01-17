package kata.jpa.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Variant> variants = new ArrayList<>();


    public void addVariant(Variant variant){
        variants.add(variant);
    }


}
