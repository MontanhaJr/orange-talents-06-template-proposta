package br.com.zupacademy.mauricio.desafioproposta.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@Profile("prod")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests(authorizeRequest ->
                authorizeRequest
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_propostas:read")
                        .antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_cartoes:read")
                        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_propostas:write")
                        .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/bloqueio/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/aviso/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/carteira/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }
}
