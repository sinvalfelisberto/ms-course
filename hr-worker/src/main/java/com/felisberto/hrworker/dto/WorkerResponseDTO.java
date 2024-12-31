package com.felisberto.hrworker.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponseDTO {
    private String name;
    private BigDecimal dailyIncome;
}
