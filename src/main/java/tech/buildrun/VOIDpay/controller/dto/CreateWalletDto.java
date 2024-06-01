package tech.buildrun.VOIDpay.controller.dto;

import tech.buildrun.VOIDpay.entity.Wallet;
import tech.buildrun.VOIDpay.entity.WalletType;

public record CreateWalletDto(String fullName,
                              String biNuit,
                              String email,
                              String password,
                              WalletType.Enum walletType) {
    public Wallet toWallet(){
        return new Wallet(
                 fullName,
                biNuit,
                email,
                password,
                walletType.get()
        );
    }

}
