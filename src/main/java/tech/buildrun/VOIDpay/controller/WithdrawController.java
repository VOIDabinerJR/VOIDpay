package tech.buildrun.VOIDpay.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.buildrun.VOIDpay.controller.dto.WithdrawDto;

import tech.buildrun.VOIDpay.entity.Withdraw;
import tech.buildrun.VOIDpay.service.WithdrawService;

@RestController
public class WithdrawController {
    private final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/withdraw")
    public ResponseEntity<Withdraw> withdraw(@RequestBody @Valid WithdrawDto dto){
        var resp = withdrawService.withdraw(dto);
        return ResponseEntity.ok(resp);
    }
}
