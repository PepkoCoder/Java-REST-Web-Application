package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MockHardwareRepository implements HardwareRepository {

    private final List<Hardware> MOCKED_HARDWARE = new ArrayList<>(Arrays.asList(
            new Hardware("Intel Core i3 10105", "19388899", 219f, HardwareType.CPU, 5),
            new Hardware("AMD Radeon RX 6800", "12944477", 1023f, HardwareType.GPU, 3)
    ));

    @Override
    public Set<Hardware> finaAll() {
        return Set.copyOf(MOCKED_HARDWARE);
    }

    @Override
    public Optional<Hardware> findByCode(final String code) {
        return MOCKED_HARDWARE.stream().filter(it -> Objects.equals(it.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> save (Hardware hardware){

        Hardware hw = new Hardware(hardware.getName(),
                hardware.getCode(),
                hardware.getPrice(),
                hardware.getType(),
                hardware.getAmountInStorage());

        if(!findByCode(hw.getCode()).isEmpty()){
            return null;
        }
        else {
            MOCKED_HARDWARE.add(hw);
            return Optional.of(hw);
        }
    }

    @Override
    public void deleteByCode(String code){
        Optional<Hardware> hardware = findByCode(code);

        if(!hardware.isEmpty()){
            MOCKED_HARDWARE.remove(hardware.get());
        }

    }

    @Override
    public Optional<Hardware> update(String code, Hardware update){
        Optional<Hardware> hardwareOpt = findByCode(code);

        if(hardwareOpt.isEmpty()) {
            return null;
        }
        else {
            Hardware hardware = hardwareOpt.get();
            hardware.setName(update.getName());
            hardware.setCode(update.getCode());
            hardware.setPrice(update.getPrice());
            hardware.setType(update.getType());
            hardware.setAmountInStorage(update.getAmountInStorage());
            return Optional.of(hardware);
        }
    }

    @Override
    public Set<Hardware> findByString(String text) {
        return null;
    }

}
