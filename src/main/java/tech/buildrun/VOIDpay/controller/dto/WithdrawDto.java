package tech.buildrun.VOIDpay.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WithdrawDto(@DecimalMin("0.01") @NotNull BigDecimal value, @NotNull Long withdrawal, @NotNull Long destinationBankAccount){


}
