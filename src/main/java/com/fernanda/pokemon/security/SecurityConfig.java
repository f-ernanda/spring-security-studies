package com.fernanda.pokemon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //para permitir o preAuthorize()
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                /*
                * O CSRF significa cross site request forgery, e acontece quando uma solicitação HTTP não autorizada é enviada
                * para um site de confiança do usuário, e para garantir que isso não aconteça, o Spring habilita o CSRF por padrão.
                * Para então que o frontend consiga fazer o CRUD, o Spring envia um token CSRF por meio de um cookie que o frontend usa
                * nos forms. Com isso, quando a solicitação HTTP é feita, o Spring valida esse token, e se for o mesmo, a solicitação é aceita.
                * */
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")//não precisa de senha nesses patterns...
                .permitAll() //...graças a essa permissão
                .antMatchers("/pokemon/myteam").hasRole(UserRole.USER.name()) //a ordem dos matchers importa
//                .antMatchers(HttpMethod.DELETE,"/admin/**").hasAuthority(UserPermission.POKEMON_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/admin/**").hasAuthority(UserPermission.POKEMON_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/admin/**").hasAuthority(UserPermission.POKEMON_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/admin/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/team", true);
//                .httpBasic(); //autenticação básica, não é possível fazer logout
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails fernandaUser = User.builder()
                .username("fernanda")
                .password(passwordEncoder.encode("password1"))
//                .roles(UserRole.ADMIN.name()) //ROLE_ADMIN é como o security entende e usa esse role
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails venicioUser = User.builder()
                .username("venicio")
                .password(passwordEncoder.encode("password2"))
                .authorities(UserRole.ADMINTRAINEE.getGrantedAuthorities())
                .build();

        UserDetails miluUser = User.builder()
                .username("milu")
                .password(passwordEncoder.encode("password3"))
//                .roles(UserRole.USER.name()) //é possível designar mais de um role para o usuário
                .authorities(UserRole.USER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(fernandaUser, venicioUser, miluUser);
    }
}
