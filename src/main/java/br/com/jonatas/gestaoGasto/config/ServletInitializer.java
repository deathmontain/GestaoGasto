package br.com.jonatas.gestaoGasto.config;

import br.com.jonatas.gestaoGasto.GestãoGastoApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GestãoGastoApplication.class);
	}

}
