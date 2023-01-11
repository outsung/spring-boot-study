package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // httpSecurity.headers().frameOptions().sameOrigin()
    http.cors().and()
    .httpBasic().disable()
    .csrf().disable()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
        .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers("/auth/**", "/auth/**").permitAll() // 가입 및 인증 주소는 누구나 접근가능
            .antMatchers(HttpMethod.GET, "/exception/**", "/helloworld/**","/actuator/health", "/favicon.ico").permitAll() // 등록된 GET요청 리소스는 누구나 접근가능
            .antMatchers(HttpMethod.POST, "/*/board/*").hasRole("ADMIN")
            .anyRequest().hasRole("USER") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
    .and()
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣어라.
  }

  @Override // ignore swagger security config
  public void configure(WebSecurity web)  {
      web.ignoring().antMatchers("/v2/api-docs", "/h2-console/**", "/swagger-resources/**",
              "/swagger-ui.html", "/webjars/**", "/swagger/**").and().ignoring().antMatchers(HttpMethod.OPTIONS);
  }
}
