package es.iespuertodelacruz.xptrade;

import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.Assertions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
//@ActiveProfiles("test")
//@Sql("/xptradetest.sql")
class XPTradeApplicationTests extends TestUtilities {

	@Mock
	private SpringApplicationBuilder springApplicationBuilder;
	@Test
	void contextLoads() {
//		ServletInitializer servletInitializer = new ServletInitializer();
//		when(springApplicationBuilder.sources(XPTradeApplication.class)).thenReturn(springApplicationBuilder);
//
//		SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);
//
//		verify(springApplicationBuilder).sources(XPTradeApplication.class);
//		Assertions.assertEquals(springApplicationBuilder,result, MESSAGE_ERROR);
	}

	@Test
	void mainTest(){
		XPTradeApplication.main(new String[]{});
	}
}
