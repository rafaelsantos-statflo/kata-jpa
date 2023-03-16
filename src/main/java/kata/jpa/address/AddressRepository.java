package kata.jpa.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer>{
    List<Address> findByLineOne(String lineOne);

//    @Query(value = "SELECT a.* FROM location l LEFT JOIN address a ON l.address_id = a.id WHERE l.name = :name",
//            nativeQuery = true)
//    List<Address> findByLocationWhereNameStartsWith(@Param("name") String name);


    @Query(value = "SELECT l.address FROM Location l WHERE l.name = :name")
    List<Address> findByLocationWhereNameStartsWith(@Param("name") String name);
}
