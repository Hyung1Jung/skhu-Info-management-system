package com.skhu.hyungil.project.mycontact.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RenameNotPermittedException extends RuntimeException{
    private static final String MESSAGE = "이름을 변경하지 않습니다.";

    public RenameNotPermittedException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
