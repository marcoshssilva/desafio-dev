package br.com.marcoshssilva.desafiodev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@SpringBootApplication
@EnableSwagger2
public class DesafiodevApplication implements CommandLineRunner{

	Logger LOG = LoggerFactory.getLogger(DesafiodevApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DesafiodevApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.setupTimezone();
	}

	private void setupTimezone(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		LOG.info("Timezone changed to America/Sao_Paulo");
	}

}
