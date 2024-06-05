package tech.buildrun.VOIDpay.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.buildrun.VOIDpay.controller.dto.DepositDto;
import tech.buildrun.VOIDpay.entity.Deposit;
import tech.buildrun.VOIDpay.entity.Wallet;
import tech.buildrun.VOIDpay.exception.BankAccountNotValidException;
import tech.buildrun.VOIDpay.exception.DepositNotAllowedException;
import tech.buildrun.VOIDpay.exception.DepositNotAllowedWalletType;
import tech.buildrun.VOIDpay.exception.WalletNotFoundException;
import tech.buildrun.VOIDpay.repository.DepositRepository;
import tech.buildrun.VOIDpay.repository.TransferRepository;
import tech.buildrun.VOIDpay.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class DepositService {
    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final DepositRepository depositRepository;
    private final AuthorizationPovisoryDepositMpesa authorizationPovisoryDepositMpesa;

    private final WalletRepository walletRepository;

    public DepositService(NotificationService notificationService,
                          AuthorizationService authorizationService,
                          DepositRepository depositRepository, AuthorizationPovisoryDepositMpesa authorizationPovisoryDepositMpesa,
                          WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.depositRepository = depositRepository;
        this.authorizationPovisoryDepositMpesa = authorizationPovisoryDepositMpesa;
        this.walletRepository = walletRepository;
    }
    @Transactional
    public Deposit deposit(DepositDto depositDto) {
        var depositor = walletRepository.findById(depositDto.depositor())
                .orElseThrow(()-> new WalletNotFoundException(depositDto.depositor()));

        //validation needed
        var originBankAccount = depositDto.originBankAccount();
        //

        validateDeposit(depositDto, depositor);
        depositor.credit(depositDto.value());

        var deposit = new Deposit(originBankAccount,depositor,depositDto.value());
        walletRepository.save(depositor);

        var depositResult = depositRepository.save(deposit);

        CompletableFuture.runAsync(()-> notificationService.sendDepositNotification(depositResult));

        return depositResult;
    }

    private void validateDeposit(DepositDto depositDto, Wallet depositor) {
        Long originBankAccount= depositDto.originBankAccount();

        if (!depositor.isDepositAllowedWalletType()){
        throw new DepositNotAllowedWalletType();
        }

//        if(!originBankAccount.isValidAccount()){
//            throw new BankAccountNotValidException();
//        }
        if (!authorizationService.isAuthorizedToDeposit(depositDto)){
            throw new DepositNotAllowedException();
        }
        if (!authorizationPovisoryDepositMpesa.tryAuthorizeMpesa("","","","","")){
            throw  new DepositNotAllowedException();
        }

    }


}
