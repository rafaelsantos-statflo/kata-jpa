package kata.jpa.address;

import kata.jpa.location.Location;
import kata.jpa.location.LocationRepository;
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
	private static final Country country = Country.Canada;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Test
	void save_Given_NewAddress_Then_Success() {
		val address = new Address();
		address.setLineOne(LINE_ONE);
		address.setCountry(country);
		addressRepository.save(address);
		val actual =  addressRepository.findById(address.getId());
		assertEquals(actual.get(), address);
	}

	@Test
	void save_Given_AddressWithLineOne_Then_Success() {
		val address = new Address();
		address.setLineOne(LINE_ONE);
		address.setCountry(country);
		addressRepository.save(address);
		val actual =  addressRepository.findById(address.getId());
		assertEquals(actual.get(), address);
	}

	@Test
	void save_Given_AddressWithLineTwo_Then_Success(){
		val address = new Address();
		address.setLineOne(LINE_ONE);
		address.setLineTwo(LINE_TWO);
		address.setCountry(country);
		addressRepository.save(address);
		val actual= addressRepository.findById(address.getId());
		assertEquals(actual.get(), address);
	}

	@Test
	void save_Given_MissingMandatoryLineOne_Then_Exception() {
		val address = new Address();
		address.setCountry(country);
		val actual = assertThrows(DataIntegrityViolationException.class, () -> addressRepository.save(address));
		assertEquals("not-null property references a null or transient value : kata.jpa.address.Address.lineOne", actual.getMessage());
	}

	@Test
	void save_Given_MissingMandatoryCountry_Then_Exception() {
		val address = new Address();
		address.setLineOne(LINE_ONE);
		val actual = assertThrows(DataIntegrityViolationException.class, () -> addressRepository.save(address));
		assertEquals("not-null property references a null or transient value : kata.jpa.address.Address.country", actual.getMessage());
	}

	@Test
	void findByLineOne_Given_ExistingAddress_Then_Success(){
		val address = new Address();
		val lineOne = LINE_ONE;
		address.setLineOne(lineOne);
		address.setCountry(country);
		addressRepository.save(address);
		val actual = addressRepository.findByLineOne(lineOne);
		assertTrue(actual.contains(address));
	}

	@Test
	void save_Country(){
		val address = new Address();
		address.setLineOne(LINE_ONE);
		address.setCountry(Country.Canada);
		addressRepository.save(address);
		assertEquals(addressRepository.findById(address.getId()).get(), address);
	}

	@Test
	void test_findByLocationWhereNameStartsWith(){
		val addressA = new Address();
		addressA.setLineOne("123 Main St A");
		addressA.setCountry(Country.Canada);
		val addressB = new Address();
		addressB.setLineOne("123 Main St B");
		addressB.setCountry(Country.Canada);
		val addressC = new Address();
		addressC.setLineOne("123 Main St C ");
		addressC.setCountry(Country.Canada);

		val locationA = new Location();
		locationA.setName("Apple Orchard");
		locationA.setAddress(addressA);
		val locationB = new Location();
		locationB.setName("Banana Store");
		locationB.setAddress(addressB);
		val locationC = new Location();
		locationC.setName("Cherry Store");
		locationC.setAddress(addressC);

		locationRepository.save(locationA);
		locationRepository.save(locationB);
		locationRepository.save(locationC);

		val actual = addressRepository.findByLocationWhereNameStartsWith("Apple Orchard");
		assertTrue(actual.contains(addressA));
		assertFalse(actual.contains(addressB));
		assertFalse(actual.contains(addressC));

	}

}
