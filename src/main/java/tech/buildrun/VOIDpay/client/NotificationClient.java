package tech.buildrun.VOIDpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.buildrun.VOIDpay.entity.Deposit;
import tech.buildrun.VOIDpay.entity.Transfer;
import tech.buildrun.VOIDpay.entity.Withdraw;

@FeignClient(
        name = "NotificationClient",
        url = "${client.notification-service.url}"
)
public interface NotificationClient {
    @PostMapping
    ResponseEntity<Void> sendTransferNotification(@RequestBody Transfer transfer);
    @PostMapping
    ResponseEntity<Void> sendDepositNotification(@RequestBody Deposit deposit);
    @PostMapping
    ResponseEntity<Void> sendWithdrawNotification(@RequestBody Withdraw withdraw);


}
