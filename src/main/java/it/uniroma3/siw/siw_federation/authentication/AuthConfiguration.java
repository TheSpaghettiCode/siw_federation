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
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().and().cors().disable()
                .authorizeHttpRequests()

                // Pagine pubbliche accessibili senza login
                .requestMatchers(HttpMethod.GET, "/", "/loginPage", "/registrationPage", "/about", "/css/**", "/images/**", "/javascript/**", "favicon.ico").permitAll()
                .requestMatchers(HttpMethod.POST, "/registrationData", "/loginPage", "/registrationPage.html").permitAll()


                // Accesso basato sui ruoli
                .requestMatchers(HttpMethod.GET, "/presidente/**").hasAuthority(PRESIDENTE_ROLE)
                .requestMatchers(HttpMethod.POST, "/presidente/**").hasAuthority(PRESIDENTE_ROLE)
                .requestMatchers(HttpMethod.GET, "/presidente_editor/**").hasAnyAuthority(PRESIDENTE_ROLE, GIOCATORE_ROLE)
                .requestMatchers(HttpMethod.POST, "/presidente_editor/**").hasAnyAuthority(PRESIDENTE_ROLE, GIOCATORE_ROLE)
                .requestMatchers(HttpMethod.GET, "/editor/**").hasAuthority(GIOCATORE_ROLE)
                .requestMatchers(HttpMethod.POST, "/editor/**").hasAuthority(GIOCATORE_ROLE)

                // Tutti gli utenti autenticati possono accedere alle altre pagine
                .anyRequest().authenticated()

                // Configurazione del login
                .and().formLogin()
                .loginPage("/loginPage")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .failureUrl("/loginPage?error=true")

                // Configurazione del logout
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true).permitAll();

        return httpSecurity.build();
    }
}*/



package it.uniroma3.siw.siw_federation.authentication;

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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().and().cors().disable()
                .authorizeHttpRequests()

                // Pagine pubbliche accessibili senza login
                .requestMatchers(HttpMethod.GET, "/", "/loginPage", "/registrationPage", "/about","/squadre/all", "/css/**", "/images/**", "/javascript/**", "favicon.ico").permitAll()
                .requestMatchers(HttpMethod.POST, "/registrationData", "/loginPage", "/registrationPage").permitAll()

                // Accesso riservato agli utenti Giocatore (solo visualizzazione)
                .requestMatchers(HttpMethod.GET, "/giocatori/all",  "/presidenti/all", "/giocatori/dettagli/**", "/squadre/dettagli/**").hasAnyAuthority(PRESIDENTE_ROLE, GIOCATORE_ROLE)

                /*// Accesso riservato agli utenti Presidente (accesso completo)
                .requestMatchers(HttpMethod.GET, "/giocatori/**", "/squadre/**", "/presidenti/**", "/giocatori/dettagli/**", "/squadre/dettagli/**").hasAuthority(PRESIDENTE_ROLE)
                .requestMatchers(HttpMethod.POST, "/presidente/**").hasAuthority(PRESIDENTE_ROLE)
                
                // Accesso completo ai presidenti anche per la modifica o cancellazione
                .requestMatchers(HttpMethod.POST, "/giocatori/**", "/squadre/**", "/presidenti/**").hasAuthority(PRESIDENTE_ROLE)*/
                .requestMatchers(HttpMethod.GET, "/giocatori/nuovo", "/giocatori/modifica/**", "/giocatori/elimina/**", "/squadre/nuova", "/squadre/modifica/**", "/squadre/elemina/**").hasAnyAuthority(PRESIDENTE_ROLE)
                .requestMatchers(HttpMethod.POST, "/giocatori/**", "/squadre/**").hasAnyAuthority(PRESIDENTE_ROLE)

                // Tutti gli utenti autenticati possono accedere alle altre pagine
                .anyRequest().authenticated()

                // Configurazione del login e reindirizzamento
                .and().formLogin()
                .loginPage("/loginPage")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .failureUrl("/loginPage?error=true")
                
                // Gestione degli accessi negati
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler()) // Se l'utente ha il login ma non il permesso giusto
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/loginPage")) // Se l'utente non è autenticato

                // Configurazione del logout
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true).permitAll();

        return httpSecurity.build();
    }

    // Gestore per accesso negato
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> response.sendRedirect("/loginPage?accessDenied=true");
    }
}

