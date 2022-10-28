package app;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import app.driver.TestCases;
import app.model.Driver;
import app.service.DriverService;
import app.service.IDriverService;
import app.service.IUserService;
import app.service.RideService;
import app.service.UserService;

@SpringBootApplication
@ComponentScan("app")
public class CabBookingAppApplication implements CommandLineRunner{
	@Autowired
	public TestCases testcases;
	
	public static void main(String[] args)  {
		SpringApplication.run(CabBookingAppApplication.class, args);
		
	}
	
	@Override
	public void run(String ...args) throws Exception{
		testcases.run();
		
		
	}
	

}
