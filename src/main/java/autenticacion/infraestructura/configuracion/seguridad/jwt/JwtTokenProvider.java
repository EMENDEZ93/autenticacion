package autenticacion.infraestructura.configuracion.seguridad.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import autenticacion.infraestructura.configuracion.seguridad.exception.SecurityJwtException;
import autenticacion.infraestructura.configuracion.seguridad.servicio.UserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long jwtExpiration;

	@Autowired
	private UserDetailService myUserDetails;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String username, List<SimpleGrantedAuthority> roles) {

		Claims claims = Jwts.claims().setSubject(username);		
		claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));
				
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new SecurityJwtException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
