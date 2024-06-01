package tech.buildrun.VOIDpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.VOIDpay.entity.WalletType;

public interface WalletRepository extends JpaRepository<WalletType,Long> {
}
