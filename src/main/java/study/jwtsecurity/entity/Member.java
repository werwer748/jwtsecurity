package study.jwtsecurity.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import study.jwtsecurity.common.MemberType;
import study.jwtsecurity.dto.member.request.MemberUpdateRequest;
import study.jwtsecurity.dto.signup.SignUpRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, scale = 20, unique = true)
    private String account;

    @Column(nullable = false)
    private String password;

    private String name;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Member from(SignUpRequest request) {
        return Member.builder()
                .account(request.account())
                .password(request.password())
                .name(request.name())
                .age(request.age())
                .type(MemberType.USER)
                .build();
    }

    @Builder
    private Member(String account, String password, String name, Integer age, MemberType type) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public void update(MemberUpdateRequest newMember) {
        this.password = newMember.newPassword() == null || newMember.newPassword().isBlank() ? this.password : newMember.newPassword();
        this.name = newMember.name();
        this.age = newMember.age();
    }
}
