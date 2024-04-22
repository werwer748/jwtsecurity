package study.jwtsecurity.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.jwtsecurity.common.MemberType;
import study.jwtsecurity.dto.member.response.MemberInfoResponse;
import study.jwtsecurity.entity.Member;
import study.jwtsecurity.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdminServiceTest {
    @Autowired
    AdminService adminService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    void 관리자는_모든_회원정보를_조회할_수_있다() {
        // given
        memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .name("콜라곰")
                .type(MemberType.USER)
                .build());
        memberRepository.save(Member.builder()
                .account("ciderbear754")
                .password("1234")
                .name("사이다곰")
                .type(MemberType.USER)
                .build());
        memberRepository.save(Member.builder()
                .account("fantabear754")
                .password("1234")
                .name("환타곰")
                .type(MemberType.USER)
                .build());

        // when
        List<MemberInfoResponse> members = adminService.getMembers();

        // then
        assertThat(members).hasSize(3);
        assertThat(members.get(0).account()).isEqualTo("colabear754");
        assertThat(members.get(0).name()).isEqualTo("콜라곰");
        assertThat(members.get(0).type()).isEqualTo(MemberType.USER);
        assertThat(members.get(1).account()).isEqualTo("ciderbear754");
        assertThat(members.get(1).name()).isEqualTo("사이다곰");
        assertThat(members.get(1).type()).isEqualTo(MemberType.USER);
        assertThat(members.get(2).account()).isEqualTo("fantabear754");
        assertThat(members.get(2).name()).isEqualTo("환타곰");
        assertThat(members.get(2).type()).isEqualTo(MemberType.USER);
    }

    @Test
    void 관리자는_모든_관리자정보를_조회할_수_있다() {
        // given
        memberRepository.save(Member.builder()
                .account("colabear754")
                .password("1234")
                .name("콜라곰")
                .type(MemberType.ADMIN)
                .build());
        memberRepository.save(Member.builder()
                .account("ciderbear754")
                .password("1234")
                .name("사이다곰")
                .type(MemberType.ADMIN)
                .build());
        memberRepository.save(Member.builder()
                .account("fantabear754")
                .password("1234")
                .name("환타곰")
                .type(MemberType.ADMIN)
                .build());

        // when
        List<MemberInfoResponse> admins = adminService.getAdmins();

        // then
        assertThat(admins).hasSize(3);
        assertThat(admins.get(0).account()).isEqualTo("colabear754");
        assertThat(admins.get(0).name()).isEqualTo("콜라곰");
        assertThat(admins.get(0).type()).isEqualTo(MemberType.ADMIN);
        assertThat(admins.get(1).account()).isEqualTo("ciderbear754");
        assertThat(admins.get(1).name()).isEqualTo("사이다곰");
        assertThat(admins.get(1).type()).isEqualTo(MemberType.ADMIN);
        assertThat(admins.get(2).account()).isEqualTo("fantabear754");
        assertThat(admins.get(2).name()).isEqualTo("환타곰");
        assertThat(admins.get(2).type()).isEqualTo(MemberType.ADMIN);
    }
}