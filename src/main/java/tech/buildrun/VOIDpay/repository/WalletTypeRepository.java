package tech.buildrun.VOIDpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.VOIDpay.entity.WalletType;
@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType,Long> {

}
