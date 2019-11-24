package edu.karolinawidz.imageorganizer;

import edu.karolinawidz.imageorganizer.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	private AppUserRepo appUserRepo;
//	private TagRepo tagRepo;
	//private ImageRepo imageRepo;

	@Autowired
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,AppUserRepo appUserRepo/*, TagRepo tagRepo,ImageRepo imageRepo */){
		this.userDetailsService=userDetailsService;
		this.appUserRepo = appUserRepo;
		//this.tagRepo = tagRepo;
		//this.imageRepo = imageRepo;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests()
				.antMatchers("/test1").hasRole("USER")
				.antMatchers("/test2").hasRole("ADMIN")
				.antMatchers("/uploadImage").hasRole("ADMIN")
				.and()
				.formLogin().permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}
	@EventListener(ApplicationReadyEvent.class)
	public void get(){
		//User appUserUser = new User("Jan",passwordEncoder().encode("Jan123"),"ROLE_USER");
		//User appUserAdmin = new User("Ala",passwordEncoder().encode("Jan123"),"ROLE_ADMIN");


	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


}
