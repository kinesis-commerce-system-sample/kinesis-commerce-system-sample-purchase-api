package com.example.kinesiscommercesystemsample.purchase.api;

import com.example.kinesiscommercesystemsample.ComponentScanBasePackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanBasePackage.class})
public class PurchaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseApiApplication.class, args);
	}
}
