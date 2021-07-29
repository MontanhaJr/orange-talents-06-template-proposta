package br.com.zupacademy.mauricio.desafioproposta.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests(authorizeRequest ->
                authorizeRequest
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_propostas:read")
                        .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_cartoes:read")
                        .antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_propostas:write")
                        .antMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }
}
