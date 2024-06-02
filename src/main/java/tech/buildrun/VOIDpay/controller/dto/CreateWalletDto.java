package tech.buildrun.VOIDpay.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.buildrun.VOIDpay.entity.Wallet;
import tech.buildrun.VOIDpay.entity.WalletType;

public record CreateWalletDto(@NotBlank String fullName,
                              @NotBlank String biNuit,
                              @NotBlank  String email,
                              @NotBlank String password,
                              @NotNull WalletType.Enum walletType) {
    public Wallet toWallet() {
        return new Wallet(
                fullName,
                biNuit,
                email,
                password,
                walletType.get()
        );
    }
}
