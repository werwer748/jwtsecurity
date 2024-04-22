package study.jwtsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jwtsecurity.dto.signup.request.SignUpRequest;
import study.jwtsecurity.dto.signup.response.SignUpResponse;
import study.jwtsecurity.dto.singin.request.SignInRequest;
import study.jwtsecurity.dto.singin.response.SignInResponse;
import study.jwtsecurity.entity.Member;
import study.jwtsecurity.repository.MemberRepository;
import study.jwtsecurity.security.TokenProvider;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public SignUpResponse registMember(SignUpRequest request) {
        Member member = memberRepository.save(Member.from(request, encoder));

        try {
            memberRepository.flush();
        } catch (DataIntegrityViolationException e) { // account가 unique니까...
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        return SignUpResponse.from(member);
    }

    public SignInResponse signIn(SignInRequest request) {
        Member member = memberRepository.findByAccount(request.account())
                .filter(it -> encoder.matches(request.password(), it.getPassword())) // PasswordEncoder로 비밀번호 비교
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        String token = tokenProvider.createToken(String.format("%s:%s", member.getId(), member.getType())); // pk:type

        return new SignInResponse(member.getName(), member.getType(), token);
    }
}
