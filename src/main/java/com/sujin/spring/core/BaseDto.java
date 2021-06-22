package com.sujin.spring.core;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public abstract class BaseDto<PK> {

    private PK id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a")
    private LocalDateTime lastModifiedAt;

    private Long createdBy;

    private Long modifiedBy;

    private int version;
}
