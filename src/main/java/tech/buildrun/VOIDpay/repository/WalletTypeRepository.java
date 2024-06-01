package tech.buildrun.VOIDpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.VOIDpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType,Long> {

}
