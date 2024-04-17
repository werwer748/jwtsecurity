package study.jwtsecurity.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자용 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
}
