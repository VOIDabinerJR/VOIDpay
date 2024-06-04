package tech.buildrun.VOIDpay.service;

import org.springframework.stereotype.Service;
import tech.buildrun.VOIDpay.client.AuthorizationClient;
import tech.buildrun.VOIDpay.controller.dto.TransferDto;
import tech.buildrun.VOIDpay.entity.Transfer;
import tech.buildrun.VOIDpay.exception.VOIDpayException;

@Service
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;
    public AuthorizationService(AuthorizationClient authorizationClient){
        this.authorizationClient = authorizationClient;

    }
    public boolean isAuthorized(TransferDto transfer){
        var resp = authorizationClient.isAuthorized();
        if (resp.getStatusCode().isError()){
            throw  new VOIDpayException();
        }
        return resp.getBody().authorized();
    }
}
