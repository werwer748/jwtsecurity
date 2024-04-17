package study.jwtsecurity.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerApi() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new Info()
                                .title("스프링시큐리티 + JWT 예제")
                                .description("스프링시큐리티와 JWT를 이용한 사용자 인증 예제!")
                                .version("1.0.0"));
    }

}
