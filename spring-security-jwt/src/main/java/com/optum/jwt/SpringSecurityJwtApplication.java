package com.optum.jwt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.optum.jwt.entity.UserInfo;
import com.optum.jwt.repository.UserInfoRepository;
import com.optum.jwt.security.MD5Encryptor;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class SpringSecurityJwtApplication {
	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private MD5Encryptor md5Encryptor;

	@PostConstruct
	public void initUsers() {
		List<UserInfo> userInfos = Stream.of(new UserInfo(101, "optum", md5Encryptor.getMd5("pwd")),
				new UserInfo(102, "optum1", md5Encryptor.getMd5("pwd1")),
				new UserInfo(103, "optum2", md5Encryptor.getMd5("pwd2")),
				new UserInfo(104, "optum3", md5Encryptor.getMd5("pwd3"))).collect(Collectors.toList());
		repository.saveAll(userInfos);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:4200");
			}
		};
	}

}
