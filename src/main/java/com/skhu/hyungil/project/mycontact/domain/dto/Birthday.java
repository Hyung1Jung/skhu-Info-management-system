package com.skhu.hyungil.project.mycontact.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable // 엔티티에 속해있는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Data // 해쉬 값을 toString으로
public class Birthday {
    private int yearOfBirthday;
    private int monthOfBirthday;
    private int dayOfBirthday;
}
