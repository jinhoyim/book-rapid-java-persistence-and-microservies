package book.apress.rapidjavapersistencemicroservice.adminservice.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

// Signleton으로 동작하지 않음
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    private final AdminServerProperties adminServer;

    public SecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminServer = adminServerProperties;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

        CookieClearingLogoutHandler cookies = new CookieClearingLogoutHandler("our-custom-cookie");


        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                .requestMatchers(
                        this.adminServer.path("/login"),
                        this.adminServer.path("/assets/**"),
                        this.adminServer.path("/actuator/info"),
                        this.adminServer.path("/actuator/health"))
                .permitAll()
                .dispatcherTypeMatchers(DispatcherType.ASYNC)
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin(formLogin ->
                        formLogin.loginPage(this.adminServer.path("/login"))
                                .successHandler(successHandler))
                .logout(logout -> logout.logoutUrl(this.adminServer.path("/logout"))
                                .addLogoutHandler(cookies)
                                .logoutSuccessUrl(this.adminServer.path("/login"))
                                .permitAll()
                        )
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.path("/instances"), POST.name()),
                                new AntPathRequestMatcher(this.adminServer.path("/instances/*"), DELETE.name()),
                                new AntPathRequestMatcher(this.adminServer.path("/actuator/**")),
                                new AntPathRequestMatcher(this.adminServer.path("/logout"))
                        ))
                .httpBasic(Customizer.withDefaults());

        http.rememberMe(rememberMe -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(
            PasswordEncoder passwordEncoder,
            @Value("${spring.security.user.name}") String username,
            @Value("${spring.security.user.password}") String password) {
        UserDetails user = User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("USER").build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode(password))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
