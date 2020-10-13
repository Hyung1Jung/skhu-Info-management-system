package com.skhu.hyungil.project.mycontact.domain;

import com.skhu.hyungil.project.mycontact.controller.dto.PersonDto;
import com.skhu.hyungil.project.mycontact.domain.dto.Birthday;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @NotEmpty // String 값이기 때문에
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private int age;

    private String hobby;

    @NotEmpty
    @NonNull
    @Column(nullable = false)
    private String bloodType;

    private String address;

    @Valid
    @Embedded //Birthday Entity 안의 Column을 하나의 갹체로 사용하고 싶을떄 @Embeded or @Embeddable
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Block block;

    public void set(PersonDto personDto) {
        if (personDto.getAge() != 0) {
            this.setAge(personDto.getAge());
        }

        if(!StringUtils.isEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
        }

        if(!StringUtils.isEmpty(personDto.getBloodType())) {
            this.setHobby(personDto.getBloodType());
        }

        if(!StringUtils.isEmpty(personDto.getAddress())) {
            this.setHobby(personDto.getAddress());
        }

        if(!StringUtils.isEmpty(personDto.getJob())) {
            this.setHobby(personDto.getJob());
        }

        if(!StringUtils.isEmpty(personDto.getPhoneNumber())) {
            this.setHobby(personDto.getPhoneNumber());
        }
    }
}

