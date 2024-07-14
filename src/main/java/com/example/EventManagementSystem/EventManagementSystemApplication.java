package com.example.EventManagementSystem;

import com.example.EventManagementSystem.domain.Admin;
import com.example.EventManagementSystem.domain.SecuredUser;
import com.example.EventManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EventManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private AdminService adminService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EventManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Admin admin = Admin.builder()
					.name("admin1")
					.email("admin1@gmail.com")
					.securedUser(SecuredUser.builder()
							.username("admin1")
							.password(passwordEncoder.encode("admin1@123"))
							.build())
					.build();
			adminService.createAdmin(admin);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}
}
