package tech.buildrun.VOIDpay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.buildrun.VOIDpay.client.NotificationClient;
import tech.buildrun.VOIDpay.entity.Deposit;
import tech.buildrun.VOIDpay.entity.Transfer;
import tech.buildrun.VOIDpay.entity.Withdraw;

@Service
public class NotificationService {
    private final NotificationClient notificationClient;
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationClient notificationClient){
        this.notificationClient = notificationClient;

    }

    public  void sendTransferNotification(Transfer transfer){
        try {
            logger.info("Sending notification...");
            var resp = notificationClient.sendTransferNotification(transfer);
            if (resp.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not ok");
            }
        } catch (Exception e){
            logger.error("Error while sending notification", e);

        }
    }
    public  void sendDepositNotification(Deposit deposit){
        try {
            logger.info("Sending notification...");
            var resp = notificationClient.sendDepositNotification(deposit);
            if (resp.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not ok");
            }
        } catch (Exception e){
            logger.error("Error while sending notification", e);

        }
    }
    public  void sendWithdrawNotification(Withdraw withdraw){
        try {
            logger.info("Sending notification...");
            var resp = notificationClient.sendWithdrawNotification(withdraw);
            if (resp.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not ok");
            }
        } catch (Exception e){
            logger.error("Error while sending notification", e);

        }
    }
}
