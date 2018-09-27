package com.example.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by gorkem on 28.03.2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home", "/registration", "/console/**").permitAll()
//				.antMatchers("/kaydet", "/edit").hasAuthority("admin") // kaydet ve edit sayfasını sadece admin yetkisi
//																		// olan kişi görebilir
//				.antMatchers("/at").hasAuthority("list")
//				.antMatchers("/kaydet").hasAuthority("save")
				.anyRequest().authenticated().and().csrf().disable().formLogin().loginPage("/login").permitAll()
				.usernameParameter("username").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and().logout()
				.permitAll();
		http.headers().frameOptions().disable();
	}

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enabled from users where username=?")
				.authoritiesByUsernameQuery("select u.username, p.name from roles r, users u, permissions p, roles_perms rp where username=?"
						+ " and u.role_id=r.id and  rp.role_id=r.id and rp.perms_id=p.id");
//				.authoritiesByUsernameQuery("select u.username, r.name from roles r, users u where username=? and u.role_id=r.id");
		
				// .authoritiesByUsernameQuery("select username, role from roles where
		// username=?");
	}
}
