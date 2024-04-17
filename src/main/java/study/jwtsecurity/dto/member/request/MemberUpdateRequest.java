package study.jwtsecurity.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberUpdateRequest(
        @Schema(description = "회원 기존 비밀번호", example = "1234")
        String password,
        @Schema(description = "회원 새 비밀번호", example = "1234")
        String newPassword,
        @Schema(description = "회원 이름", example = "휴고강")
        String name,
        @Schema(description = "회원 나이", example = "31")
        Integer age
) {
}
