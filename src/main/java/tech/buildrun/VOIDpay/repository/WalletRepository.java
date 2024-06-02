package tech.buildrun.VOIDpay.repository;

import feign.Param;
import org.springframework.data.jpa.repository.Query;
import tech.buildrun.VOIDpay.entity.WalletType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.VOIDpay.entity.Wallet;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("SELECT w FROM Wallet w WHERE w.biNuit = :biNuit AND w.email = :email")
    Optional<Wallet> findByBiNuitorEmail(@Param("biNuit") String biNuit, @Param("email") String email);
   // Optional<Wallet> findByBiNuitorEmail(String biNuit, String email);
}
