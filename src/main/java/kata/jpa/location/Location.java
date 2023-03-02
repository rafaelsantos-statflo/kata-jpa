package kata.jpa.location;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;
}
