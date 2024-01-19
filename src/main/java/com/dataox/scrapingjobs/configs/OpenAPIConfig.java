package com.dataox.scrapingjobs.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenAPIConfig {

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public OpenAPI openAPI() {
        Server localhost = new Server()
                .url("http://localhost:" + serverPort)
                .description("Local development server");

        Contact contact = new Contact()
                .name("DataOx technical task by Oleh Denkovych")
                .url("https://www.linkedin.com/in/olehdenkovych/");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Scraping Jobs API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage job vacancies.")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(Collections.singletonList(localhost));
    }
}
