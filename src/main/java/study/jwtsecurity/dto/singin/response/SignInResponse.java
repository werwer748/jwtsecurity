package study.jwtsecurity.dto.singin.response;

import io.swagger.v3.oas.annotations.media.Schema;
import study.jwtsecurity.common.MemberType;

public record SignInResponse(
        @Schema(description = "회원 이름", example = "휴고강")
        String name,
        @Schema(description = "회원 유형", example = "USER")
        MemberType type,
        String token // JWT 토큰값
) {
}
