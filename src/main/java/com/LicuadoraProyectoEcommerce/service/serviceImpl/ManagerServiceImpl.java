package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.ManagerDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.ManagerMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.model.User;
import com.LicuadoraProyectoEcommerce.repository.ManagerRepository;
import com.LicuadoraProyectoEcommerce.service.ManagerService;
import com.LicuadoraProyectoEcommerce.service.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public ManagerDto createEntity(String email) {
        User user = userAuthService.findUserByEmail(email);
        return managerMapper.getDtoFromEntity(managerRepository.save(new Manager(null, user, null)));
    }

    @Override
    public ManagerDto getById(Long id) {
        return managerMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.delete(findEntityById(id));
    }
    public Manager findEntityById(Long id){
        return managerRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

}
