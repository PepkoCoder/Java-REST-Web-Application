package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository){
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.finaAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Hardware> findAllInStock() {
        return hardwareRepository.finaAll().stream()
                .filter(hardware1 -> hardware1.getAmountInStorage() >= 1)
                .collect(Collectors.toList());
    }

    @Override
    public HardwareDTO findByCode(String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO).orElse(null);
    }

    @Override
    public List<HardwareDTO> findByString(String text){
        return hardwareRepository.findByString(text).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public HardwareDTO save(HardwareCommand command){
        Optional<Hardware> hardware = hardwareRepository.save(new Hardware(
                command.getCode(),
                command.getName(),
                command.getPrice(),
                command.getType(),
                command.getStock()));

        if(hardware == null){
            return null;
        }
        else {
            return mapHardwareToDTO(hardware.get());
        }
    }

    @Override
    public void deleteByCode(String code){
        hardwareRepository.deleteByCode(code);
    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareCommand updateCommand){
        Optional<Hardware> hardware = hardwareRepository.update(code,
                new Hardware(updateCommand.getName(),
                            updateCommand.getCode(),
                            updateCommand.getPrice(),
                            updateCommand.getType(),
                            updateCommand.getStock()));

        return hardware == null ? Optional.empty() : Optional.of(mapHardwareToDTO(hardware.get()));
    }

    private HardwareDTO mapHardwareToDTO(Hardware hardware){
        if(hardware != null)
            return new HardwareDTO(hardware.getName(), hardware.getPrice(), hardware.getCode());

        return null;
    }

}
