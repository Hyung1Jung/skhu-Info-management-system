package com.skhu.hyungil.project.mycontact.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable // 엔티티에 속해있는 Dto
@NoArgsConstructor
@Data // 해쉬 값을 toString으로
public class Birthday {
    private Integer yearOfBirthday;
    private Integer monthOfBirthday;
    private Integer dayOfBirthday;

    public Birthday(LocalDate birthday) {
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }


    // new birthday보다는 static 생성자를 쓰느게 추세세
    public static Birthday of(LocalDate birthday) {
        return new Birthday(birthday);
    }
}
