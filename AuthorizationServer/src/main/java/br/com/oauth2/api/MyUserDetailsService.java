package br.com.oauth2.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import retrofit2.Call;

@Service
public class MyUserDetailsService implements UserDetailsService {

	UserService userService;

	@Autowired
	public MyUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Call<User> call = userService.login(login);
		User user;
		
		try {
			user = call.execute().body();
			if (user == null) {
				throw new UsernameNotFoundException("User " + login + " not found in database.");
			}

			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), 
					true, true, true, true, getAuthorities(user.getRoles()));

		} catch (IOException e) {
			throw new UsernameNotFoundException("User " + login + " not found in database.");
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(roles);
	}

	private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Role privilege : roles) {
			authorities.add(new SimpleGrantedAuthority(privilege.getAuthority()));
		}

		return authorities;
	}
}