package kata.jpa.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.jpa.address.AddressRepository;
import lombok.val;

@SpringBootTest
class AddressTest {

	@Autowired
	private AddressRepository addressRepository;

	@Test
	void saveAddress() {
		val address = new Address();
		addressRepository.save(address);
		val actual =  addressRepository.findById(address.getId());

		assertEquals(actual.get(), address);
	}


}
