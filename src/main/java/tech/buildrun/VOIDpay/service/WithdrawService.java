package tech.buildrun.VOIDpay.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.buildrun.VOIDpay.controller.dto.WithdrawDto;
import tech.buildrun.VOIDpay.entity.Deposit;
import tech.buildrun.VOIDpay.entity.Wallet;
import tech.buildrun.VOIDpay.entity.Withdraw;
import tech.buildrun.VOIDpay.exception.DepositNotAllowedException;
import tech.buildrun.VOIDpay.exception.DepositNotAllowedWalletType;
import tech.buildrun.VOIDpay.exception.WalletNotFoundException;
import tech.buildrun.VOIDpay.exception.insufficienteBalanceException;
import tech.buildrun.VOIDpay.repository.WalletRepository;
import tech.buildrun.VOIDpay.repository.WithdrawRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class WithdrawService {
    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final WithdrawRepository withdrawRepository;

    private final WalletRepository walletRepository;

    public WithdrawService(NotificationService notificationService,
                          AuthorizationService authorizationService,
                          WithdrawRepository withdrawRepository,
                          WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.withdrawRepository = withdrawRepository;
        this.walletRepository = walletRepository;
    }
@Transactional
    public Withdraw withdraw(WithdrawDto withdrawDto) {
    var withdrawal = walletRepository.findById(withdrawDto.withdrawal())
            .orElseThrow(()-> new WalletNotFoundException(withdrawDto.withdrawal()));

    //validation needed
    var destinationBankAccount = withdrawDto.destinationBankAccount();
    //

    validateWithdraw(withdrawDto, withdrawal);
    withdrawal.debit(withdrawDto.value());

    var withdraw = new Withdraw(destinationBankAccount,withdrawal,withdrawDto.value());
    walletRepository.save(withdrawal);

    var withDrawResult = withdrawRepository.save(withdraw);

    CompletableFuture.runAsync(()-> notificationService.sendWithdrawNotification(withDrawResult));

    return withDrawResult;
    }

    private void validateWithdraw(WithdrawDto withdrawDto, Wallet withdrawal) {
        Long originBankAccount= withdrawDto.destinationBankAccount();

/*
        if (!withdrawal.isWithdrawAllowedWalletType()){
            throw new WithdrawNotAllowedWalletType();
        }
        if (!withdrawal.isWithdrawAllowedSituation()){

        }
        if(!originBankAccount.isValidAccount()){
            throw new BankAccountNotValidException();
        }

*/
        if (!withdrawal.isBalanceBiggerThan(withdrawDto.value())){
            throw new insufficienteBalanceException();

        }

        if (!authorizationService.isAuthorizedToWithdraw(withdrawDto)){
            throw new DepositNotAllowedException();
        }
    }


}
