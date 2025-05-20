package es.iespuertodelacruz.xptrade.shared.security;

import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class CxfAuthInterceptor extends AbstractPhaseInterceptor<Message> {
    public static final String tokenPrefix="Bearer ";
    private JwtService jwtTokenManager;
    @Autowired
    public void setJwtTokenManager(JwtService jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    /**
     * Indica la fase en la que ejecuta el interceptor
     */

    public CxfAuthInterceptor() {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        Map<String, List<String>> protocolHeaders = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);

        if (protocolHeaders != null && protocolHeaders.containsKey("Authorization")) {
            List<String> authorizationHeaders = protocolHeaders.get("Authorization");

            if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
                String authHeader = authorizationHeaders.get(0);

                String token = authHeader;

                if (authHeader.startsWith(tokenPrefix)) {
                    token = authHeader.substring(tokenPrefix.length());
                }

                Map<String, String> mapInfoToken = jwtTokenManager.validateAndGetClaims(token);

                final String username = mapInfoToken.get("username");
                final String role = mapInfoToken.get("role");

                if (!role.equals("ROLE_ADMIN")) {
                    throw new SecurityException("Unauthorized");
                }

                CustomUserDetails userDetails = new CustomUserDetails(username, role);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }else {
            throw new SecurityException("No security headers included");
        }
    }
}
