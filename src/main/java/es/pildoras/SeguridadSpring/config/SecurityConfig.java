package es.pildoras.SeguridadSpring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securitySource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*UserBuilder usuarios = User.withDefaultPasswordEncoder();		
		auth.inMemoryAuthentication()
		.withUser(usuarios.username("Andres").password("123").roles("administrador"))
		.withUser(usuarios.username("Natalia").password("456").roles("usuario"))
		.withUser(usuarios.username("Rafael").password("789").roles("ayudante"))
		.withUser(usuarios.username("Alejo").password("222").roles("usuario"));*/
		auth.jdbcAuthentication().dataSource(securitySource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/administradores/**").hasRole("ADMINISTRADOR").
		antMatchers("/usuarios/**").hasRole("USUARIO").
		anyRequest().authenticated().and().formLogin()
		.loginPage("/formularioLogin")
		.loginProcessingUrl("/autenticacionUsuario")
		.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/accesoDenegado");
	}
	
	

}
