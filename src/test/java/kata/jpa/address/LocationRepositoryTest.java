package kata.jpa.address;

import kata.jpa.location.Location;
import kata.jpa.location.LocationRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationRepositoryTest {

    private static final String LINE_ONE = "123 ABC Avenue";
    private static final String LINE_TWO = "Unit 2";
    private static final Country country = Country.Canada;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void save_Given_NewAddress_Then_Success() {
        val location = new Location();
        location.setName("Mall");
        locationRepository.save(location);
        val actual =  locationRepository.findById(location.getId());
        assertEquals(actual.get(), location);
    }


    @Test
    void save_With_NewAddress_Then_Success(){
        val location = new Location();
        location.setName("Mall");
        val address = new Address();
        address.setLineOne("123 Main St");
        address.setCountry(Country.Canada);

        location.setAddress(address);

//        addressRepository.save(address);
        locationRepository.save(location);

        val actual = locationRepository.findById(location.getId());
        assertEquals(actual.get(),location);

    }

}
