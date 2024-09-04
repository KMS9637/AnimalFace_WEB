package com.project.animalface_web.security.filter;

import com.project.animalface_web.security.APIUserDetailsService;
import com.project.animalface_web.security.exception.AccessTokenException;
import com.project.animalface_web.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
    //JWT 토큰을 검사하는 역할
    // OncePerRequestFilter : 하나의 요청에 대해서 한번씩 동작하는 필터

    private final APIUserDetailsService apiUserDetailsService;
    private final JWTUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 토큰 검사를 건너뛸 경로를 설정합니다.
        String path = request.getRequestURI();
        return path.startsWith("/api/notices");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();


        if (!path.startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("lsy Token Check Filter..........................");
        log.info("lsy JWTUtil: " + jwtUtil);



        try{
//            validateAccessToken(request);

            Map<String, Object> payload = validateAccessToken(request);
            log.info("payload: " + payload);
            //username
            String memberId = (String)payload.get("memberId");

            log.info("memberId: " + memberId);

            UserDetails userDetails = apiUserDetailsService.loadUserByUsername(memberId);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request,response);
        }catch(AccessTokenException accessTokenException){
            accessTokenException.sendResponseError(response);
        }


//        filterChain.doFilter(request, response);
    }

    private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {

        String headerStr = request.getHeader("Authorization");

        if(headerStr == null  || headerStr.length() < 8){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }

        //Bearer 생략
        String tokenType = headerStr.substring(0,6);
        String tokenStr =  headerStr.substring(7);

        if(tokenType.equalsIgnoreCase("Bearer") == false){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
        }

        log.info("TOKENSTR-----------------------------");
        log.info(tokenStr);
        log.info("----------------------------------------");

        try{
            Map<String, Object> values = jwtUtil.validateToken(tokenStr);
            log.info("TokenCheckFilter 의 jwtUtil.validateToken(tokenStr) values-----------------------------");
            log.info(values);
            log.info("----------------------------------------");
            return values;
        }catch(MalformedJwtException malformedJwtException){
            log.error("MalformedJwtException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
        }catch(SignatureException signatureException){
            log.error("SignatureException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
        }catch(ExpiredJwtException expiredJwtException){
            log.error("ExpiredJwtException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
        }
    }

}
