package study.jwtsecurity.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEMA_NAME = "authorization";

    @Bean
    public OpenAPI swaggerApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEMA_NAME, new SecurityScheme()  // 인증정보 버튼 생성
                                .name(SECURITY_SCHEMA_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                )
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEMA_NAME)) // 시큐리티 요구사항을 스웨거에 추가
                .info(
                        new Info()
                                .title("스프링시큐리티 + JWT 예제")
                                .description("스프링시큐리티와 JWT를 이용한 사용자 인증 예제!")
                                .version("1.0.0"));
    }

}
