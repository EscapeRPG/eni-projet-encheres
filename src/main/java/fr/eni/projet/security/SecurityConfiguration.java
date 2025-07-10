package fr.eni.projet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final MyUserDetailsService userDetailsService;


    /**
     * Création d'un encodeur de mots de passe Bcrypt
     */
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SecurityConfiguration(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    /**
     * Configuration de l'accès des pages aux utilisateurs anonymes et avec un rôle.
     *
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception in case of any configuration error
     *
     * form login gère la page de login custom "connexion" en cas de succès, il renvoie vers la fonction succes qui set l'utilisateur en Session
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationProvider authentication) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorize -> {
                            // Permit access to static resources and login, home, and error pages
                            authorize
                                    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                    .requestMatchers("/", "/home", "/connexion", "/login", "/inscription", "/succes", "/profil", "/detail-vente").permitAll()
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .requestMatchers("/user/**","/acquisition","/achatCredit","/profil").hasRole("USER")
                                    .anyRequest().authenticated();
                        }
                ).formLogin(form -> form
                        .loginPage("/connexion")
                        .loginProcessingUrl("/login")// Custom login page
                        .defaultSuccessUrl("/succes", true)  // Redirect to home after successful login
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/connexion?logout")  // Redirect to login page after logout
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for simplicity (not recommended for production)
                .build();
    }


//   @Bean
//   UserDetailsService userDetailsService(DataSource dataSource) {
//       JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//       jdbcUserDetailsManager.setUsersByUsernameQuery("select pseudo, motDePasse, 1 from utilisateur where pseudo = ?");
//       jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select pseudo, roles from utilisateur where pseudo = ?");
//
//       return jdbcUserDetailsManager;
//   }

    /**
     * Configures the password encoder.
     *
     * @return the configured BCryptPasswordEncoder
     */
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
