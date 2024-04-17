package study.jwtsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//? 테이블 이름 생성 확인용 - 예제와 관련 X
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamList {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
}
