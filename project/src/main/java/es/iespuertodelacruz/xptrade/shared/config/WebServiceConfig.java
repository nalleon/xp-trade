package es.iespuertodelacruz.xptrade.shared.config;

import es.iespuertodelacruz.xptrade.model.service.interfaces.IRoleSoapService;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IUserSoapService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebServiceConfig {

    private final Bus bus;
    private final IUserSoapService userSoapService;
    private final IRoleSoapService roleSoapService;


    public WebServiceConfig(
            Bus bus,
            IUserSoapService userSoapService,
            IRoleSoapService roleSoapService
    ) {
        this.bus = bus;
        this.userSoapService = userSoapService;
        this.roleSoapService = roleSoapService;
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userSoapService);
        endpoint.publish("/users");
        return endpoint;
    }

    @Bean
    public Endpoint roleEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, roleSoapService);
        endpoint.publish("/roles");
        return endpoint;
    }
}