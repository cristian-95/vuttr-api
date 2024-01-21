package api.vuttr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI configure() {
        return new OpenAPI()
                .info(new Info()
                        .title("VUTTR API")
                        .description("VUTTR (Very Useful Tools to Remember) é um simples repositório para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags.")
                        .version("1.0.0")
                        .license(new License().name("GPLv3").url("https://www.gnu.org/licenses/gpl-3.0.html#license-text")
                        )
                );
    }

}
