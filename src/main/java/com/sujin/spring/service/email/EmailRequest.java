package com.sujin.spring.service.email;

import java.io.File;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @NotNull
    @javax.validation.constraints.Email
    private String from;

    @NotNull
    @javax.validation.constraints.Email
    private String to;

    private List<String> cc;

    @NotNull
    private String subject;

    @NotNull
    private String title;

    @NotNull
    private String message;

    private List<File> attachments;
}
