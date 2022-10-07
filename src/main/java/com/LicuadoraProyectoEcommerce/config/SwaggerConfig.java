package com.LicuadoraProyectoEcommerce.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String TITLE = "SOFFTEK: Proyecto Licuadora. E-COMMERCE";
    private static final String DESCRIPTION = "Nos han solicitado diseñar y desarrollar una plataforma de E-Commerce para la\n" +
            "venta de productos de indumentaria semi-personalizados.";

    @Configuration
    public class SwaggerConfiguration  {
        @Bean
        public OpenAPI customOpenAPI() {
            OpenAPI openApi = new OpenAPI();
            openApi.info(
                    new Info()
                            .title(TITLE)
                            .description(DESCRIPTION)
                            .contact(new Contact().name("Andres Rodriguez").
                                    url("https://www.linkedin.com/in/andres-rodriguez-60a166208/").email("rodrigueza.federacion@gmail.com"))
            );
            Server localServer = new Server();
            localServer.setDescription("local");
            localServer.setUrl("http://localhost:8080");
            openApi.setServers(Arrays.asList(localServer));
            openApi.components(
                    new Components().addSecuritySchemes("Insertar token",
                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                                    .in(SecurityScheme.In.HEADER).name("Authorization"))
            );
            openApi.addSecurityItem(
                    new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"))
            );
            return openApi;
        }
    }
}