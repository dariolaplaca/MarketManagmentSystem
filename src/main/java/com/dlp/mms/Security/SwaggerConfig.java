package com.dlp.mms.Security;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI().info(new Info()
                .title("Market Management System")
                .description("Web APP for managing market shop, workers, inventory and cashiers")
                .version("0.0.1")
                .contact(new Contact()
                        .name("Dario La Placa")
                        .url("https://www.linkedin.com/in/dario-la-placa-dev/"))
        ).externalDocs(new ExternalDocumentation()
                .description("MMS")
                .url("https://github.com/dariolaplaca/MarketManagmentSystem")
        );
    }
}