package tech.buildrun.VOIDpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.ArrayList;

public class TransferNotAllowedWalletTypeException  extends VOIDpayException{

    @Override
    public ProblemDetail toProblemDetail() {
       var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
       pb.setTitle("Wallet not Allowed to Transfer");

       return pb;
    }

}
