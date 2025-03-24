package es.iespuertodelacruz.xptrade.shared.security.interceptor;


import es.iespuertodelacruz.xptrade.model.repository.IUserEntityRepository;
import es.iespuertodelacruz.xptrade.shared.security.CustomUserDetails;
import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class CxfAuthInterceptor extends AbstractPhaseInterceptor<Message> {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String GET_BY_ID_PETITION = "getById";

    public static final String GET_ALL_PETITION = "getAll";
    /**
     * Properties
     */
    @Autowired
    private JwtService jwtTokenManager;
    @Autowired
    private IUserEntityRepository usuarioRepository;

    public static final String authHeader="Authorization";
    public static final String authHeaderTokenPrefix="Bearer ";

    /**
     * Default constructor of the class
     */
    public CxfAuthInterceptor() {
        super(Phase.PRE_INVOKE);
    }


    @Override
    public void handleMessage(Message message) throws Fault {
        Map<String, List<String>> protocolHeaders = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);


        if (protocolHeaders != null && protocolHeaders.containsKey(authHeader)) {
            List<String> authorizationHeaders = protocolHeaders.get(authHeader);

            if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
                String authHeader = authorizationHeaders.get(0);

                String token = authHeader;

                if (authHeader.startsWith(authHeaderTokenPrefix)) {
                    token = authHeader.substring(authHeaderTokenPrefix.length());
                }

                Map<String, String> mapInfoToken = jwtTokenManager.validateAndGetClaims(token);

                final String username = mapInfoToken.get("username");
                final String role = mapInfoToken.get("role");
/*

                String petition = message.get(Message.WSDL_OPERATION).toString();

                String[] typePetitionARR = petition.split("}");

                String typePetition = typePetitionARR[1];

                if(typePetition.equals(GET_ALL_PETITION) || typePetition.equals(GET_BY_ID_PETITION)){

                }


                System.out.println(typePetition);
*/




                if (!role.equals(ROLE_ADMIN)) {
                    throw new SecurityException("Access denied. Admin ONLY");
                }

                CustomUserDetails userDetails = new CustomUserDetails(username, role);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);


            }
        } else {
            throw new SecurityException("Protected route");
        }
    }
}