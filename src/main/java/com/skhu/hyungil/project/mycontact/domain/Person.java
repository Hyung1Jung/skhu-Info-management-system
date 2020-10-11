package com.skhu.hyungil.project.mycontact.domain;

import com.skhu.hyungil.project.mycontact.domain.dto.Birthday;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    @Embedded //Birthday Entity 안의 Column을 하나의 갹체로 사용하고 싶을떄 @Embeded or @Embeddable
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne
    private Block block;

}

