package br.com.marcoshssilva.desafiodev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiodevApplication implements CommandLineRunner{

	Logger LOG = LoggerFactory.getLogger(DesafiodevApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DesafiodevApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("OK!");
	}

}
