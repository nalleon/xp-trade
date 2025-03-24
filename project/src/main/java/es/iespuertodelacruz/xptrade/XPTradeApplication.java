package es.iespuertodelacruz.xptrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:cxf-service.xml")
public class XPTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(XPTradeApplication.class, args);
	}

}
