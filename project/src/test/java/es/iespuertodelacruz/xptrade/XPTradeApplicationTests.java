package es.iespuertodelacruz.xptrade;

import es.iespuertodelacruz.xptrade.user.domain.port.primary.IUserService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/xptradetest.sql")
class XPTradeApplicationTests extends TestUtilities {

	@Autowired
	IUserService repository;

	@Test
	void contextLoads() {
	}

//	@Test
//	void databaseH2(){
//		List<User> list = repository.findAll();
//		Assertions.assertNotNull(list, MESSAGE_ERROR);
//		Assertions.assertEquals(1, list.size(), MESSAGE_ERROR);
//	}

}
