package kata.jpa.address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // TODO this is a bad idea
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
}
