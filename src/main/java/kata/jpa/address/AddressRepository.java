package kata.jpa.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer>{
    List<Address> findByLineOne(String lineOne);
}
