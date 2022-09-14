package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.ManagerDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.ManagerMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.Manager;
import com.LicuadoraProyectoEcommerce.repository.ManagerRepository;
import com.LicuadoraProyectoEcommerce.service.ManagerService;
import com.LicuadoraProyectoEcommerce.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ManagerServiceImpl implements ManagerService{
    private static final int SIZE_TEN = 10;
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
    public ManagerDto getById(Long id) {
        return managerMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        managerRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public List<ManagerDto> getListEntityPage(Integer page) {
        List<Manager> managerList = managerRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return managerMapper.listDtoFromListEntities(managerList);
    }
    public Manager findEntityById(Long id){
        return managerRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

}
