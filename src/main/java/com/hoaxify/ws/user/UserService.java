package com.hoaxify.ws.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Bu create ettiğmiiz UserService katmanının Service ile ilgili bir class olduğunu göstermek için @Service diye bir annotation oluşturuyoruz.

@Service
public class UserService {

	// Önceki durumda UserRepository UserController içinde tanımlamıştık ancak bu layered architecture uygun olmadığı için UserRepository getirip
	// burada tanımlıyoruz. UserRepository burada tanımladığımız için UserServcice gidip UserController içinde tanımlıyoruz.
	
	// @Autowired annotationı yazarak direkt olarak dependencyleri inject edebiliriz. Ancak başka yollar da vardır. Örneğin aşağıda görülen constructor
	// (public UserService(UserRepository userRepository) ile başlayan kısım) sağ click source, generate constructor using field ile de eklenebilir.
	// Bu constructor injectiondır.
	UserRepository userRepository;

	// create ettiğimiz userların passwordlari şimdiye kadar DB açık halde görünyordu ancak bu güvenli bir yol değidir,
	// bunu önlemek için proje başlangıcında eklediğim security depedency kullanırız.
	
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository  = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public void save(User user) {
		String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
	
			userRepository.save(user);
	}
}
