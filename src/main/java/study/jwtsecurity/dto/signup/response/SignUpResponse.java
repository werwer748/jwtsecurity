package study.jwtsecurity.dto.signup.response;

import io.swagger.v3.oas.annotations.media.Schema;
import study.jwtsecurity.entity.Member;

import java.util.UUID;

public record SignUpResponse(
        @Schema(description = "회원 pk", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID id,
        @Schema(description = "회원 아이디", example = "hugo444")
        String account,
        @Schema(description = "회원 이름", example = "휴고강")
        String name,
        @Schema(description = "회원 비밀번호", example = "30")
        Integer age
) {
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getAccount(),
                member.getName(),
                member.getAge()
        );
    }
}
