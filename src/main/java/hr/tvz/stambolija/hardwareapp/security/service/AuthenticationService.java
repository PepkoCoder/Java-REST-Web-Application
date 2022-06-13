package hr.tvz.stambolija.hardwareapp.security.service;

import hr.tvz.stambolija.hardwareapp.security.command.LoginCommand;
import hr.tvz.stambolija.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
