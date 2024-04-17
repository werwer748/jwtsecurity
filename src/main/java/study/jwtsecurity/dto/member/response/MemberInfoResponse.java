package study.jwtsecurity.dto.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import study.jwtsecurity.common.MemberType;
import study.jwtsecurity.entity.Member;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberInfoResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b0a-6b1c032f0e4a")
        UUID id,
        @Schema(description = "회원 아이디", example = "hugo444")
        String account,
        @Schema(description = "회원 이름", example = "휴고강")
        String name,
        @Schema(description = "회원 나이", example = "30")
        Integer age,
        @Schema(description = "회원 타입", example = "USER")
        MemberType type,
        @Schema(description = "회원 생성일", example = "2024-04-17T15:00:00")
        LocalDateTime createdAt
) {
    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getAccount(),
                member.getName(),
                member.getAge(),
                member.getType(),
                member.getCreatedAt()
        );
    }
}
