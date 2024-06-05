package tech.buildrun.VOIDpay.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "tb_deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    (name = "origin_bank_account")
    private Long originBankAccount;
    @ManyToOne
    @JoinColumn(name = "wallet_depositor_id")
    private Wallet depositor;


    @Column(name = "value")
    private BigDecimal value;

    public Deposit() {
    }

    public UUID getId() {
        return id;
    }

    public Deposit(Long originBankAccount, Wallet depositor, BigDecimal value) {
        this.originBankAccount = originBankAccount;
        this.depositor = depositor;
        this.value = value;
    }



    public void setId(UUID id) {
        this.id = id;
    }

    public Long getOriginBankAccount() {
        return originBankAccount;
    }

    public void setOriginBankAccount(Long originBankAccount) {
        this.originBankAccount = originBankAccount;
    }

    public Wallet getDepositor() {
        return depositor;
    }

    public void setDepositor(Wallet depositor) {
        this.depositor = depositor;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
