package study.jwtsecurity.dto.signup;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpRequest(
        @Schema(description = "회원 아이디", example = "hugo444")
        String account,
        @Schema(description = "회원 비밀번호", example = "1234")
        String password,
        @Schema(description = "회원 이름", example = "휴고강")
        String name,
        @Schema(description = "회원 비밀번호", example = "30")
        Integer age
) {
}
