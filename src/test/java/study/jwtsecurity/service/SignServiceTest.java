package study.jwtsecurity.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.jwtsecurity.common.MemberType;
import study.jwtsecurity.dto.signup.request.SignUpRequest;
import study.jwtsecurity.dto.signup.response.SignUpResponse;
import study.jwtsecurity.dto.singin.request.SignInRequest;
import study.jwtsecurity.dto.singin.response.SignInResponse;
import study.jwtsecurity.entity.Member;
import study.jwtsecurity.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SignServiceTest {
    @Autowired
    SignService signService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    void 회원가입() {
        // given
        SignUpRequest request = new SignUpRequest("colabear754", "1234", "콜라곰", 30);
        // when
        SignUpResponse response = signService.registMember(request);
        // then
        assertThat(response.account()).isEqualTo("colabear754");
        assertThat(response.name()).isEqualTo("콜라곰");
        assertThat(response.age()).isEqualTo(30);
    }

    @Test
    void 로그인아이디는_중복될_수_없다() {
        // given
        memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .build());
        SignUpRequest request = new SignUpRequest("colabear754", "1234", null, null);
        // when
        // then
        assertThatThrownBy(() -> signService.registMember(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 사용중인 아이디입니다.");
    }

    @Test
    void 로그인() {
        // given
        memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .name("콜라곰")
                .type(MemberType.USER)
                .build());
        // when
        SignInResponse response = signService.signIn(new SignInRequest("colabear754", "1234"));
        // then
        assertThat(response.name()).isEqualTo("콜라곰");
        assertThat(response.type()).isEqualTo(MemberType.USER);
    }

    @Test
    void 로그인실패() {
        // given
        memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .build());
        // when
        // then
        Assertions.assertThatThrownBy(() -> signService.signIn(new SignInRequest("colabear754", "12345")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다.");
    }
}