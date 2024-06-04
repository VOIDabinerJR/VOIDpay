package tech.buildrun.VOIDpay.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.buildrun.VOIDpay.controller.dto.TransferDto;
import tech.buildrun.VOIDpay.entity.Transfer;
import tech.buildrun.VOIDpay.entity.Wallet;
import tech.buildrun.VOIDpay.exception.TransferNotAllowedWalletTypeException;
import tech.buildrun.VOIDpay.exception.WalletNotFoundException;
import tech.buildrun.VOIDpay.exception.insufficienteBalanceException;
import tech.buildrun.VOIDpay.exception.transferNotAllowedException;
import tech.buildrun.VOIDpay.repository.TransferRepository;
import tech.buildrun.VOIDpay.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;
@Service
public class TransferService {

    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final TransferRepository transferRepository;

    private final WalletRepository walletRepository;

    public TransferService(NotificationService notificationService,
                           AuthorizationService authorizationService,
                           TransferRepository transferRepository,
                           WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }
    @Transactional
    public  Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(()-> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(()-> new WalletNotFoundException(transferDto.payee()));


        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender,receiver,transferDto.value());


        walletRepository.save(sender);
        walletRepository.save(receiver);
       var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedWalletType()){
            throw new TransferNotAllowedWalletTypeException();
        }

        if (!sender.isBalanceBiggerThan(transferDto.value())){
            throw new insufficienteBalanceException();

        }
        if (!authorizationService.isAuthorized(transferDto)){
            throw new transferNotAllowedException();
        }
    }


}
