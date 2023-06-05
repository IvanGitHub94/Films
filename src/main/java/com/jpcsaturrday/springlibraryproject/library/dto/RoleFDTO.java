package com.jpcsaturrday.springlibraryproject.library.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleFDTO {
    private Long id;
    private String title;
    private String description;
}
