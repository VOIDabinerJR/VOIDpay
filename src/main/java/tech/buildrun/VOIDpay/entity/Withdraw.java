package tech.buildrun.VOIDpay.entity;



import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "tb_withdraw")
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
            (name = "destination_bank_account")
    private Long destinationBankAccount;
    @ManyToOne
    @JoinColumn(name = "wallet_withdrawal_id")
    private Wallet withdrawal;


    @Column(name = "value")
    private BigDecimal value;

    public Withdraw() {
    }

    public UUID getId() {
        return id;
    }

    public Withdraw(Long destinationBankAccount, Wallet withdrawal, BigDecimal value) {
        this.destinationBankAccount = destinationBankAccount;
        this.withdrawal = withdrawal;
        this.value = value;
    }

    public Withdraw(Wallet withdrawal, BigDecimal value) {
        this.withdrawal = withdrawal;
        this.value = value;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getDestinationBankAccount() {
        return destinationBankAccount;
    }

    public void setDestinationBankAccount(Long destinationBankAccount) {
        this.destinationBankAccount = destinationBankAccount;
    }

    public Wallet getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Wallet withdrawal) {
        this.withdrawal = withdrawal;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
