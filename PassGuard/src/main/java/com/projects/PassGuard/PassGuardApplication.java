package com.projects.PassGuard;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "PassGuard API",
		version = "1.0.0",
		contact = @Contact(name = "Hossam Hamdy", email = "hhamdypro@gmail.com")))
public class PassGuardApplication {


	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(PassGuardApplication.class, args);
	}

}
