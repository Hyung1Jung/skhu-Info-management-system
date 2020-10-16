package com.skhu.hyungil.project.mycontact.domain;

import com.skhu.hyungil.project.mycontact.controller.dto.PersonDto;
import com.skhu.hyungil.project.mycontact.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @NotEmpty // String 값이기 때문에
    @Column(nullable = false)
    private String name;

    private String hobby;

    private String address;

    @Valid
    @Embedded //Birthday Entity 안의 Column을 하나의 갹체로 사용하고 싶을떄 @Embeded or @Embeddable
    private Birthday birthday;

    private String job;

    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;


    public void set(PersonDto personDto) {
        if(!StringUtils.isEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
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

        if (personDto.getBirthday() != null) {
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }
    }

    public Integer getAge() {
        if(this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        }else {
            return null;
        }
    }

    public boolean isBirthdayToday() {
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }
}

