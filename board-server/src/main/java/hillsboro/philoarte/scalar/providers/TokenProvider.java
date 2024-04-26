package hillsboro.philoarte.scalar.providers;

import io.jsonwebtoken.Claims;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface TokenProvider {
    String createToken();
    String getUsernameFromToken(String token);
    Date getExpirationDateFromToken(String token);
    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDetails userDetails);

}