package com.jpcsaturrday.springlibraryproject.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO extends GenericFDTO{
    private LocalDateTime rentDate;
    private Integer rentPeriod;
    private Boolean purchase;
    private Long filmId;
    private Long userFId;
}
