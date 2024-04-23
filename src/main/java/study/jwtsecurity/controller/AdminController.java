package study.jwtsecurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.jwtsecurity.annotation.AdminAuthorize;
import study.jwtsecurity.dto.ApiResponse;
import study.jwtsecurity.service.AdminService;

@Tag(name = "관리자용 API")
@AdminAuthorize // 커스텀어노테이션 토큰으로 권한 확인하기위해 추가
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "회원 목록 조회")
    @GetMapping("/members")
    public ApiResponse getAllMembers() {
        return ApiResponse.success(adminService.getMembers());
    }

    @Operation(summary = "관리자 목록 조회")
    @GetMapping("/admins")
    public ApiResponse getAllAdmins() {
        return ApiResponse.success(adminService.getAdmins());
    }
}
