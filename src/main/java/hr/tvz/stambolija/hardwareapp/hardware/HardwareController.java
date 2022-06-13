package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {
    private final HardwareService hardwareService;

    public HardwareController (HardwareService hardwareService){
        this.hardwareService = hardwareService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final String code){
        HardwareDTO hardwareDTO = hardwareService.findByCode(code);

        if(hardwareDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(hardwareDTO);
        }
    }

    @GetMapping("/search/{text}")
    public List<HardwareDTO> getHardwareByString(@PathVariable final String text){
        return hardwareService.findByString(text);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command){
        HardwareDTO hardwareDTO = hardwareService.save(command);

        if(hardwareDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody
    final HardwareCommand updateHardwareCommand){
        return hardwareService.update(code, updateHardwareCommand)
                .map(hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO))
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }
}
