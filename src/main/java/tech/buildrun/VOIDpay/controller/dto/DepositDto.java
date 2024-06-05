package tech.buildrun.VOIDpay.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositDto(@DecimalMin("0.01") @NotNull BigDecimal value, @NotNull Long originBankAccount, @NotNull Long depositor){


}
