package kata.jpa.location;

import kata.jpa.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByAddress_Country(Country country);
}
