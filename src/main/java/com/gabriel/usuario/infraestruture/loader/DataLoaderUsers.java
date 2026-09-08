package com.gabriel.usuario.infraestruture.loader;

import com.gabriel.usuario.infraestruture.entity.UserRole;
import com.gabriel.usuario.infraestruture.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderUsers implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoaderUsers.class);

    private final UserRoleRepository userRoleRepository;

    public DataLoaderUsers(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        if (userRoleRepository.count() == 0){
            userRoleRepository.save(new UserRole("ROLE_ADMIN"));
            userRoleRepository.save(new UserRole("ROLE_USER"));
            userRoleRepository.save(new UserRole("ROLE_DEFAULT"));
            logger.info("✅ Initial data loaded successfully!");
        }

    }

}
