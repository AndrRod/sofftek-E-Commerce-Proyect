package com.LicuadoraProyectoEcommerce.mapper.manager;

import com.LicuadoraProyectoEcommerce.dto.manager.ManagerDto;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManagerMapper {
    @Autowired
    private BaseProductMapper productMapper;
    public ManagerDto getDtoFromEntity(Manager manager){
        return new ManagerDto(manager.getId(), manager.getUser().getName(), manager.getUser().getEmail(), productMapper.getListDtoCompleteFromListEntity(manager.getBaseProducts()));
    }
    public List<ManagerDto> listDtoFromListEntities(List<Manager> managerList){
        return managerList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }

}
