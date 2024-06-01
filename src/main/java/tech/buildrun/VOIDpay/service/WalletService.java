package tech.buildrun.VOIDpay.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.buildrun.VOIDpay.controller.dto.CreateWalletDto;
import tech.buildrun.VOIDpay.entity.Wallet;
import tech.buildrun.VOIDpay.entity.WalletType;
import tech.buildrun.VOIDpay.repository.WalletRepository;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }



    public Wallet createWallet(CreateWalletDto dto) {
        var walletDb = walletRepository.findByBiNuitorEmail(dto.biNuit(), dto.email());
        if (walletDb.isPresent()){

        }


        return walletRepository.save(dto.toWallet());
    }
}
