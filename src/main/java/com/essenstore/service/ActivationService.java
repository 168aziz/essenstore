package com.essenstore.service;

import com.essenstore.entity.ActivationCode;
import com.essenstore.entity.Verified;
import com.essenstore.exception.ActivationCodeExpiredException;
import com.essenstore.exception.NotFoundException;
import com.essenstore.repository.ActivationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.essenstore.utils.Utils.timeDifference;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivationService {

    private final ActivationCodeRepository repository;

    private final ActivationCode emptyActivationCode;

    private final UserService userService;

    public ActivationCode getBy(String code) {
        return repository.findByCode(code).orElse(emptyActivationCode);
    }

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

    public void activate(String code) {
        repository.findByCode(code)
                .map(exist -> {
                    if (timeDifference(exist.getCreatedAt())
                            .compareTo(Duration.of(1, ChronoUnit.MINUTES)) < 0) {
                        exist.getUser().setVerified(Verified.ENABLED);
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
