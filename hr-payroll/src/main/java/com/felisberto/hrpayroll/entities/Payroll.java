package com.felisberto.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payroll {
    private String name;
    private BigDecimal dailyIncome;
    private Integer days;

    public BigDecimal getTotal() {
        BigDecimal valor = new BigDecimal(dailyIncome.toString());
        return valor.multiply(new BigDecimal(days));
    }
}
