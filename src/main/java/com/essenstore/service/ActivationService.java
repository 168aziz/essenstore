package com.essenstore.service;

import com.essenstore.entity.ActivationCode;
import com.essenstore.entity.Verified;
import com.essenstore.exception.ActivationCodeExpiredException;
import com.essenstore.exception.NotFoundException;
import com.essenstore.exception.UserAlreadyVerifiedException;
import com.essenstore.repository.ActivationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

import static com.essenstore.utils.Utils.timeDifferenceFromNow;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivationService {

    private final ActivationCodeRepository repository;

    private final UserService userService;

    @Transactional
    public void save(ActivationCode code) {
        repository.findByUser(code.getUser())
                .ifPresentOrElse(c -> {
                    repository.delete(c);
                    repository.save(code);
                }, () -> repository.save(code));
    }

    public boolean exists(String code) {
        return repository.findByCode(code).isPresent();
    }

    @Transactional
    public void remove(ActivationCode code) {
        repository.delete(code);
    }

    @Transactional
    public void remove(String code) {
        repository.findByCode(code).ifPresent(repository::delete);
    }

    @Transactional
    public void activate(String code) {
        repository.findByCode(code)
                .map(exist -> {
                    if (exist.getUser().getVerified().isVerified())
                        throw new UserAlreadyVerifiedException();

                    if (Duration.ofMinutes(2)
                            .compareTo(timeDifferenceFromNow(exist.getCreatedAt())) > 0) {
                        exist.getUser().setVerified(Verified.ENABLED);
                        repository.delete(exist);
                        userService.save(exist.getUser());
                        return true;
                    } else
                        throw new ActivationCodeExpiredException();
                })
                .orElseThrow(() -> {
                    throw new NotFoundException();
                });
    }
}
