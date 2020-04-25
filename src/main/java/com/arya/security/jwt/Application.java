package com.arya.security.jwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arya.security.jwt.model.User;
import com.arya.security.jwt.repository.UserDao;

@SpringBootApplication
@EnableJpaRepositories
public class Application implements CommandLineRunner {
	

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    
    
    @Autowired
    private UserDao userDao;

    
    
	@Override
	public void run(String... args) throws Exception {
		
		List<User> userList = userDao.findAll();
		
		if(null == userList || userList.size() < 2) {
			
			User user = new User();
			user.setFirstName("user");
			user.setLastName("user");
			user.setUsername("user");
			user.setEmail("user@email.com");
			user.setPassword(new BCryptPasswordEncoder().encode("password"));
			
			User admin = new User();
			admin.setFirstName("admin");
			admin.setLastName("admin");
			admin.setUsername("admin");
			admin.setEmail("admin@email.com");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
			
			userDao.save(user);
			userDao.save(admin);
			
		} else {
			System.out.println("User is present in DB...!");
		}
	}

}
