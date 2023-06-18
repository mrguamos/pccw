package com.pccw.backend.modules.mail;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Mail {

    private Long id;
    @NonNull
    private String recipient;
    @NonNull
    private String subject;
    @NonNull
    private String content;
    private LocalDateTime sentOn;

}
