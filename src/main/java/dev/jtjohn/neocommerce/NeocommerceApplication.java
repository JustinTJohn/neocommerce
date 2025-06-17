package dev.jtjohn.neocommerce;

import dev.jtjohn.neocommerce.test.TestLombok;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeocommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeocommerceApplication.class, args);
//		TestLombok t = new TestLombok();
//		t.setName("Hello");
//		System.out.println(t.getName());
	}
}
