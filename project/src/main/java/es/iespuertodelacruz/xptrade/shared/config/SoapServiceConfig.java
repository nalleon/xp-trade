package es.iespuertodelacruz.xptrade.shared.config;

import es.iespuertodelacruz.xptrade.model.service.interfaces.IGameSoapService;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IRoleSoapService;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IUserSoapService;
import es.iespuertodelacruz.xptrade.shared.security.CxfAuthInterceptor;
import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapServiceConfig {

    private final Bus bus;
    private final IUserSoapService userSoapService;
    private final IRoleSoapService roleSoapService;
    private final IGameSoapService gameSoapService;


    public SoapServiceConfig(Bus bus, IUserSoapService userSoapService, IRoleSoapService roleSoapService, IGameSoapService gameSoapService) {
        this.bus = bus;
        this.userSoapService = userSoapService;
        this.roleSoapService = roleSoapService;
        this.gameSoapService = gameSoapService;
    }

    @Bean
    public CxfAuthInterceptor cxfAuthInterceptor(JwtService jwtService) {
        CxfAuthInterceptor interceptor = new CxfAuthInterceptor();
        interceptor.setJwtTokenManager(jwtService);
        return interceptor;
    }

    @Bean
    public Endpoint userEndpoint(CxfAuthInterceptor cxfAuthInterceptor) {
        EndpointImpl endpoint = new EndpointImpl(bus, userSoapService);
        endpoint.publish("/users");
        endpoint.getInInterceptors().add(cxfAuthInterceptor);
        return endpoint;
    }

    @Bean
    public Endpoint roleEndpoint(CxfAuthInterceptor cxfAuthInterceptor) {
        EndpointImpl endpoint = new EndpointImpl(bus, roleSoapService);
        endpoint.publish("/roles");
        endpoint.getInInterceptors().add(cxfAuthInterceptor);
        return endpoint;
    }


    @Bean
    public Endpoint gameEndpoint(CxfAuthInterceptor cxfAuthInterceptor) {
        EndpointImpl endpoint = new EndpointImpl(bus, gameSoapService);
        endpoint.publish("/games");
        endpoint.getInInterceptors().add(cxfAuthInterceptor);
        return endpoint;
    }
}