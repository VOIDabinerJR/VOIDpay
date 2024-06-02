package tech.buildrun.VOIDpay.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.buildrun.VOIDpay.entity.WalletType;
import tech.buildrun.VOIDpay.repository.WalletTypeRepository;

@Service
public class WalletTypeService {

    private final WalletTypeRepository walletTypeRepository;

    @Autowired
    public WalletTypeService(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    public WalletType saveWalletType(WalletType walletType) {
        return walletTypeRepository.save(walletType);
    }
}
