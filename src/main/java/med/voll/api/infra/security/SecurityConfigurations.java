package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

@Bean
public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
  return  httpSecurity.csrf(csrf -> csrf.disable()).
    sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
}

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
     throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


@Bean
   public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
   }
}

       /*      @Bean  medodo original del video de la clase pero contiene metodos deprecados

    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        
            return httpSecurity.csrf().disable()
            .sessionManagement().
            sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().build();
      
    }*/



   /*     
   metodo enviado poun compañero 
    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers(HttpMethod.POST, "/login")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
}
  
   */

