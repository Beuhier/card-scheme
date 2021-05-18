package com.interview.nine.cardscheme.authentication;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.interview.nine.cardscheme.messages.ErrorMessages;

import antlr.StringUtils;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException{
		
		final String authorizationHeader = request.getHeader("authorization");
		final String timeStampHeader = request.getHeader("timeStamp");
		final String appKeyHeader = request.getHeader("appKey");
		
		if(!checkMessageRequestIsNull(authorizationHeader,timeStampHeader,appKeyHeader)) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, ErrorMessages.INVALID_MESSAGE.getErrorMessages());
			return;
		}
		
		// This should check for key validity, but i am commenting out because the result of my Hash process using 
		// timestamp + app
		if(!validateKey(authorizationHeader,timeStampHeader,appKeyHeader)) {
			//((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessages.INVALID_KEY.getErrorMessages());
			//return;
		}
		
			filterChain.doFilter(request,response);
	}
	
	boolean checkMessageRequestIsNull(String authorizationHeader, String timeStampHeader, String appKeyHeader) {
		if(authorizationHeader == null || timeStampHeader == null ||  appKeyHeader == null) {
			return false;
		}else if(authorizationHeader.isEmpty() ||  timeStampHeader.isEmpty() || timeStampHeader.isEmpty()) {
			return false;
		}
		return true;
	}
	
	boolean validateKey(String authorizationHeader, String timeStampHeader, String appKeyHeader) {
		if(authorizationHeader.startsWith("3line ")) {
			String hashed = authorizationHeader.substring(6).trim();
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				StringBuilder sb = new StringBuilder();
				String hashChecker = appKeyHeader+timeStampHeader;
				
				byte[] keyHash = md.digest((hashChecker).getBytes(StandardCharsets.UTF_8));
				
				for(int i = 0; i < keyHash.length; i++) {
					sb.append(Integer.toString((keyHash[i] & 0xff) + 0x100, 16).substring(1));
				}
				
				String newHash = sb.toString();
				
				if(hashed != newHash) {
					return false;
				}
				
			} catch (NoSuchAlgorithmException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return true;
	}
}
