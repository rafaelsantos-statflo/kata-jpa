package kata.jpa.address;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.jpa.address.AddressRepository;
import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	void saveAddressWithLineOne() {
		val address = new Address();
		address.setLineOne("123 ABC Avenue");
		addressRepository.save(address);
		val actual =  addressRepository.findById(address.getId());

		assertEquals(actual.get(), address);
	}

	@Test
	void saveAddressWithoutMandatoryFields() {
		val address = new Address();
		assertThrows(DataIntegrityViolationException.class, () -> addressRepository.save(address));
	}


}
