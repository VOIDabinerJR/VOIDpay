package tech.buildrun.VOIDpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VoiDpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiDpayApplication.class, args);
	}

}
