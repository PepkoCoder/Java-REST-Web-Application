package hr.tvz.stambolija.hardwareapp.security.service;

import hr.tvz.stambolija.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
