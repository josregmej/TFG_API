package com.tfg.sotocafe.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	//Aquí generamos, validamos el token y obtenemos los claims
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	public String generarToken(Authentication auth) {
		String username = auth.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);

		//Creamos el token con el username, la fecha de firma, la fecha de expiracion y la firma.
		String token =Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(fechaExpiracion).signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
		return token;
	}
	
	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		//Obtenemos el username del token
		return claims.getSubject();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Firma JWT no válida.");
		} catch (MalformedJwtException ex) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Token JWT no válido.");
		} catch (ExpiredJwtException ex) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Token JWT caducado.");
		} catch (UnsupportedJwtException ex) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Token JWT no compatible.");
		} catch (IllegalArgumentException ex) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "La cadena claims JWT está vacía.");
		}
	}
}
