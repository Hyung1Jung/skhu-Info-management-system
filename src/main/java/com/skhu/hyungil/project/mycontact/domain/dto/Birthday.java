package com.skhu.hyungil.project.mycontact.domain.dto;

import javax.persistence.Embeddable;

@Embeddable // 엔티티에 속해있는 Dtd
public class Birthday {
    private int yearOfBirthday;
    private int monthOfBirthday;
    private int dayOfBirthday;
}
