package com.inforcol.cotizacion.config.ImpuestoCotizacion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cotizacionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cotizaciones")
                        .description(
                                "API para administrar cotizaciones, impuestos y deducibles.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Inforcol")))
                .addServersItem(new Server()
                        .url("http://localhost:9090")
                        .description("Servidor local"));
    }
}
