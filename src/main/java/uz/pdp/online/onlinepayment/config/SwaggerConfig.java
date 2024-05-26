package uz.pdp.online.onlinepayment.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Online Payment System",
                description = "This application is for Online payments where you can pay any services",
                contact = @Contact(
                        name = "Jayxunbey",
                        url = "https://www.github.com/Jayxunbey",
                        email = "muxammedovjayxun@gmail.com"
                ),
                license = @License(
                        name = "Apache 3.0",
                        url = "https://starter.spring.io"
                ),
                termsOfService = "https://wwww.wiki.com"
        ),
        servers = {
                @Server(url = "http://localhost:8081/swagger",
                        description = "Local Server"),
                @Server(url = "http://localhost:8082/swagger",
                        description = "Production Server"),
                @Server(url = "http://localhost:8080/swagger",
                        description = "Development")
        }
)


public class SwaggerConfig {
//
////    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI().addSecurityItem(new SecurityRequirement().
//                        addList("Bearer Authentication"))
//                .components(new Components().addSecuritySchemes
//                        ("Bearer Authentication", createAPIKeyScheme()))
//                .info( new io.swagger.v3.oas.models.info.Info().title("My REST API")
//                        .description("Some custom description of API.")
//                        .version("1.0").contact(new io.swagger.v3.oas.models.info.Contact().name("Sallo Szrajbman")
//                                .email( "www.baeldung.com").url("salloszraj@gmail.com"))
//                        .license(new io.swagger.v3.oas.models.info.License().name("License of API")
//                                .url("API license URL")));
//    }
//
//    private SecurityScheme createAPIKeyScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }
}
