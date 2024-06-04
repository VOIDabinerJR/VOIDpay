package tech.buildrun.VOIDpay.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.VOIDpay.entity.Transfer;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
