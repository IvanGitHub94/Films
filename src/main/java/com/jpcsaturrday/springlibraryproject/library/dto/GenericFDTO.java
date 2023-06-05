package com.jpcsaturrday.springlibraryproject.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class GenericFDTO {
    private Long id;
    private LocalDateTime createdWhen;
    private String createdBy;
    private LocalDateTime deletedWhen;
    private String deletedBy;
    private boolean isDeleted;
}
