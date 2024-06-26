package study.jwtsecurity.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.jwtsecurity.common.MemberType;
import study.jwtsecurity.dto.member.request.MemberUpdateRequest;
import study.jwtsecurity.dto.member.response.MemberDeleteResponse;
import study.jwtsecurity.dto.member.response.MemberInfoResponse;
import study.jwtsecurity.dto.member.response.MemberUpdateResponse;
import study.jwtsecurity.entity.Member;
import study.jwtsecurity.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder encoder;

    @BeforeEach
    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    void 회원조회() {
        // given
        Member savedMember = memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .name("콜라곰")
                .type(MemberType.USER)
                .build());
        // when
        MemberInfoResponse response = memberService.getMemberInfo(savedMember.getId());
        // then
        assertThat(response.id()).isEqualTo(savedMember.getId());
        assertThat(response.account()).isEqualTo("colabear754");
        assertThat(response.name()).isEqualTo("콜라곰");
        assertThat(response.type()).isEqualTo(MemberType.USER);
    }

    @Test
    void 존재하지_않는_회원은_예외가_발생한다() {
        // given
        // when
        // then
        assertThatThrownBy(() -> memberService.getMemberInfo(UUID.randomUUID()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 회원이에용!");
    }

    @Test
    void 회원탈퇴() {
        // given
        Member savedMember = memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .build());
        // when
        MemberDeleteResponse result = memberService.deleteMember(savedMember.getId());
        // then
        List<Member> members = memberRepository.findAll();
        assertThat(members).isEmpty();
        assertThat(result.result()).isEqualTo(true);
    }

    @Test
    void 회원정보수정() {
        // given
        Member savedMember = memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .build());
        // when
        MemberUpdateRequest request = new MemberUpdateRequest("1234", "5678", "콜라곰", 27);
        MemberUpdateResponse result = memberService.updateMember(savedMember.getId(), request);
        // then
        assertThat(result.result()).isEqualTo(true);
        assertThat(result.name()).isEqualTo("콜라곰");
        assertThat(result.age()).isEqualTo(27);
        Member member = memberRepository.findAll().get(0);
//        assertThat(member.getPassword()).isEqualTo("5678");
    }
}