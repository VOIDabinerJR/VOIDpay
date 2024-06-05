package tech.buildrun.VOIDpay.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.VOIDpay.controller.dto.DepositDto;

import tech.buildrun.VOIDpay.entity.Deposit;

import tech.buildrun.VOIDpay.service.DepositService;

@RestController
public class DepositController {
    private DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/deposit")
    public ResponseEntity<Deposit> deposit(@RequestBody @Valid DepositDto dto){
        var resp = depositService.deposit(dto);
        return ResponseEntity.ok(resp);
    }

}
