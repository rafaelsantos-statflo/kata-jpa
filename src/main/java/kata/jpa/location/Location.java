package kata.jpa.location;


import jakarta.persistence.*;
import kata.jpa.address.Address;
import lombok.Data;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
