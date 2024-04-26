package hillsboro.philoarte.scalar.providerImpls;

import hillsboro.philoarte.scalar.providers.TokenProvider;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Log4j2
public class TokenProviderImpl implements TokenProvider {
    private final String token;
    private final String key;
    private int tokenExpirationMsec = 1800000; // 만료시간 30분
    private static final String AUTHORITIES_KEY = "role";

    public TokenProviderImpl(String key) {
        this.token = createToken();
        this.key = key;
    }

    @Override
    public String createToken() { // jwt.io 에서 보면서 작성
        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put("alg", "HS256");
            headers.put("typ", "JWT");

            Map<String, Object> payloads = new HashMap<>();
            payloads.put("data", "my First JWT"); // 현재는 더미값

            // long exirationTime = 1000 * 60L * 60L * 2L; // 토큰 유효시간 2시간
            long exirationTime = 1000 * 60L * 60L * 2000L; // 토큰 유효시간 2000시간
            Date ext = new Date();
            ext.setTime(ext.getTime() + exirationTime);
            return Jwts.builder().setHeader(headers).setClaims(payloads).setSubject("user").setExpiration(ext)
                    .signWith(SignatureAlgorithm.HS256, key.getBytes()).compact();
        } catch (SecurityException e) {
            log.info("Invalid JWT Signature");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.info("Expiration JWT token");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Signature");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid");
        }
        return null;
    }

    // retrieve username from jwt token
    // jwt token으로부터 username을 획득한다.
    @Override public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    // jwt token으로부터 만료일자를 알려준다.
    @Override public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        String secret = ""; // 일단 에러를 막기위한 로컬선언문
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    // 토큰이 만료되었는지 확인한다.
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token for user
    // 유저를 위한 토큰을 발급해준다.
    @Override public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // while creating the token -
    // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact
    // Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    // compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        int JWT_TOKEN_VALIDITY = 0;  // 일단 에러를 막기위한 로컬선언문
        String secret = ""; // 일단 에러를 막기위한 로컬선언문
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
