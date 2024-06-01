package tech.buildrun.VOIDpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.VOIDpay.entity.Wallet;
import tech.buildrun.VOIDpay.entity.WalletType;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByBiNuitorEmail(String biNuit, String email);
}
