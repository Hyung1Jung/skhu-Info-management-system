package com.skhu.hyungil.project.mycontact.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.skhu.hyungil.project.mycontact.configuration.serializer.BirthdaySerializer;
import com.skhu.hyungil.project.mycontact.domain.dto.Birthday;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JsonConfig { // seriallzer mapping
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() { // seriallzer 등록
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new BirthdayModule());
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

    static class BirthdayModule extends SimpleModule {
        BirthdayModule() {
            super(); //simpleModule 메서드의 생성자 호출
            addSerializer(Birthday.class, new BirthdaySerializer());
        }
    }
}
/*
만든 seriallzer을 BirthdayModule에 주입해주고 BirthdayModule은
objectMapper()에 넣어주고, objectMapper()을
MappingJackson2HttpMessageConverter에 집어 넣어주는 형식 */