package study.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import study.jwtsecurity.common.MemberType;
import study.jwtsecurity.entity.Member;
import study.jwtsecurity.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberRepository.save(Member.builder()
                .account("admin")
                .password("admin")
                .name("관리자")
                .type(MemberType.ADMIN)
                .build());
    }
}
