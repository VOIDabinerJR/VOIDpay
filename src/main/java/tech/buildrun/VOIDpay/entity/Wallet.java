package tech.buildrun.VOIDpay.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_wallet")

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "bi_nuit", unique = true)
    private String biNuit;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

//    @Column(name = "endereco")
//    private String endereco;
//
    @JoinColumn(name = "wallet_type_id")
    @ManyToOne
    private WalletType walletType;

    public Wallet() {
    }

    public Wallet(String fullName, String biNuit, String email, String password, WalletType walletType) {
        this.fullName = fullName;
        this.biNuit = biNuit;
        this.email = email;
        this.password = password;
        this.walletType = walletType;
    }

    public boolean isTransferAllowedWalletType() {

        return this.walletType.equals(WalletType.Enum.USER.get());
    }
    public boolean isDepositAllowedWalletType() {

        return this.walletType.equals(WalletType.Enum.USER.get());
    }

    public boolean isBalanceBiggerThan(BigDecimal value) {
        return this.balance.doubleValue() > value.doubleValue();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBiNuit() {
        return biNuit;
    }

    public void setBiNuit(String biNuit) {
        this.biNuit = biNuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }


    public void debit(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance =  this.balance.add(value);
    }
}