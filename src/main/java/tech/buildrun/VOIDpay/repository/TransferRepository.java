package tech.buildrun.VOIDpay.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.VOIDpay.entity.Transfer;

import java.util.UUID;
@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
