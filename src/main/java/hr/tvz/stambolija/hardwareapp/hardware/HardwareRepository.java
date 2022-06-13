package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HardwareRepository {

    Set<Hardware> finaAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);

    Optional<Hardware> update(String code, Hardware hardware);

    Set<Hardware> findByString(String text);

    void deleteByCode(String code);


}
