package br.com.helpdev.quaklog.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_SCAN_PACKAGE = "br.com.helpdev.quaklog.entrypoint";

    /**
     * Tags
     */
    public static final String TAG_GAME_ENTRY_POINT = "GAME";

    /**
     * Swagger Infos
     **/
    private static final String HELPDEV_SITE = "http://helpdev.com.br";
    private static final String CONTACT_NAME = "Guilherme Biff Zarelli";
    private static final String CONTACT_EMAIL = "gbzarelli@helpdev.com.br";
    private static final String TERMS_OF_SERVICE_URL = "Terms of Service Url";
    private static final String LICENSE = "MIT";
    private static final String LICENSE_URL = "https://opensource.org/licenses/MIT";

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
                .apis(RequestHandlerSelectors.basePackage(BASE_SCAN_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .tags(getGameEntryPointTAG());
    }

    private Tag getGameEntryPointTAG() {
        return new Tag(TAG_GAME_ENTRY_POINT, "Entrypoint para importação e consulta de jogos baseada em logs");
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(name, description, version,
                TERMS_OF_SERVICE_URL,
                new Contact(CONTACT_NAME, HELPDEV_SITE, CONTACT_EMAIL),
                LICENSE,
                LICENSE_URL,
                Collections.emptyList());
    }
}