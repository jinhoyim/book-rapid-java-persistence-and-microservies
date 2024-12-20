package book.apress.rapidjavapersistencemicroservice.eshopservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Eshop API")
                        .description("Spring Shop Sample Application")
                        .version("v0.0.1")
                        .termsOfService("describe the terms of service")
                        .contact(new Contact()
                                .name("John Doe")
                                .email("john.doe@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Cloud Eshop API")
                        .url("https://example.com")
                );
    }
}
