package br.com.luizalabs.quaklog.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "br.com.luizalabs.quaklog.entrypoint";

    @Value("${application.version}")
    private String version;

    @Value("${application.name}")
    private String name;

    @Value("${application.description}")
    private String description;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(name, description, version,
                "Terms of Service",
                new Contact("Guilherme Biff Zarelli", "http://helpdev.com.br", "gbzarelli@helpdev.com.br"),
                "MIT",
                "https://opensource.org/licenses/MIT",
                Collections.emptyList());
    }
}