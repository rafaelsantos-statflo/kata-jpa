package kata.jpa.address;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressRepositoryTest {
	private static final String LINE_ONE = "123 ABC Avenue";
	private static final String LINE_TWO = "Unit 2";

	@Autowired
	private AddressRepository addressRepository;

	@Test
	void save_Given_NewAddress_Then_Success() {
		val address = new Address();
		address.setLineOne(LINE_ONE);
		addressRepository.save(address);
		val actual =  addressRepository.findById(address.getId());
		assertEquals(actual.get(), address);
	}

	@Test
	void save_Given_AddressWithLineOne_Then_Success() {
		val address = new Address();
		address.setLineOne(LINE_ONE);
		addressRepository.save(address);
		val actual =  addressRepository.findById(address.getId());
		assertEquals(actual.get(), address);
	}

	@Test
	void save_Given_AddressWithLineTwo_Then_Success(){
		val address = new Address();
		address.setLineOne(LINE_ONE);
		address.setLineTwo(LINE_TWO);
		addressRepository.save(address);
		val actual= addressRepository.findById(address.getId());
		assertEquals(actual.get(), address);
	}

	@Test
	void save_Given_MissingMandatoryFields_Then_Exception() {
		val address = new Address();
		val actual = assertThrows(DataIntegrityViolationException.class, () -> addressRepository.save(address));
		assertEquals("not-null property references a null or transient value : kata.jpa.address.Address.lineOne", actual.getMessage());
	}

	@Test
	void findByLineOne_Given_ExistingAddress_Then_Success(){
		val address = new Address();
		val lineOne = LINE_ONE;
		address.setLineOne(lineOne);
		addressRepository.save(address);
		val actual = addressRepository.findByLineOne(lineOne);
		assertTrue(actual.contains(address));
	}

}
