package kata.jpa.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // TODO this is a bad idea
public class Address {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String lineOne;

    private String lineTwo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;
}

