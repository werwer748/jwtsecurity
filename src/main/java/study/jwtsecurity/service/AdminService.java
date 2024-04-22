package study.jwtsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jwtsecurity.common.MemberType;
import study.jwtsecurity.dto.member.response.MemberInfoResponse;
import study.jwtsecurity.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final MemberRepository memberRepository;

    public List<MemberInfoResponse> getMembers() {
        return memberRepository.findAllByType(MemberType.USER).stream()
//                .map(member -> MemberInfoResponse.from(member)) // from에 new 가 있음!
                .map(MemberInfoResponse::from)
                .toList();
    }

    public List<MemberInfoResponse> getAdmins() {
        return memberRepository.findAllByType(MemberType.ADMIN).stream()
                .map(MemberInfoResponse::from)
                .toList();
    }
}
