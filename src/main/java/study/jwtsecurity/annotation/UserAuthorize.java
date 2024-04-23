package study.jwtsecurity.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('USER','ADMIN')") // hasAnyAuthority: 두 권한중 하나라도 가지고 있다면 사용 가능
public @interface UserAuthorize {
}
