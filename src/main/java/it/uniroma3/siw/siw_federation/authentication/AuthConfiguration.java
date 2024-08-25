/*package it.uniroma3.siw.siw_federation.authentication;

import static it.uniroma3.siw.siw_federation.model.Credentials.GIOCATORE_ROLE;
import static it.uniroma3.siw.siw_federation.model.Credentials.PRESIDENTE_ROLE;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//public  class WebSecurityConfig {
	public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, role from credentials WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*@Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf().and().cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
            // chiunque (autenticato o no) può accedere alle pagine index, login, register, ai css e alle immagini
                .requestMatchers(HttpMethod.GET,"/", "/loginPage","/registrationPage", "/search", "/all/**", "/css/**", "/images/**", "/javascript/**", "favicon.ico").permitAll()
        	// chiunque (autenticato o no) può mandare richieste POST al punto di accesso per login e register 
                .requestMatchers(HttpMethod.POST,"/registrationData", "/loginPage").permitAll()
              
                /*
                .requestMatchers(HttpMethod.GET, "/presidente/**").hasAnyAuthority(PRESIDENTE_ROLE;)
                .requestMatchers(HttpMethod.POST, "/presidente/**").hasAnyAuthority(PRESIDENTE_ROLE;)
                .requestMatchers(HttpMethod.GET,"/presidente_editor/**").hasAnyAuthority(PRESIDENTE_ROLE;, GIOCATORE_ROLE;)
                .requestMatchers(HttpMethod.POST,"/presidente_editor/**").hasAnyAuthority(PRESIDENTE_ROLE;, GIOCATORE_ROLE;)
                .requestMatchers(HttpMethod.GET,"/editor/**").hasAnyAuthority(GIOCATORE_ROLE;)
                .requestMatchers(HttpMethod.POST,"/editor/**").hasAnyAuthority(GIOCATORE_ROLE;) 
                

        	// tutti gli utenti autenticati possono accere alle pagine rimanenti 
            //.anyRequest().authenticated()

                .anyRequest().permitAll()

                // LOGIN: qui definiamo il login
                .and().formLogin()
                .loginPage("/loginPage")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .failureUrl("/loginPage?error=true")
                // LOGOUT: qui definiamo il logout
                .and()
                .logout()
                // il logout è attivato con una richiesta GET a "/logout"
                .logoutUrl("/logout")
                // in caso di successo, si viene reindirizzati alla home
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true).permitAll();
        return httpSecurity.build();
    }*/
//}
