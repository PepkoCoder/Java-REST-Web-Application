package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    List<Hardware> findAllInStock();

    HardwareDTO findByCode(String code);

    List<HardwareDTO> findByString(String text);

    HardwareDTO save(HardwareCommand command);

    void deleteByCode(String code);

    Optional<HardwareDTO> update (String code, HardwareCommand updateCommand);

}
