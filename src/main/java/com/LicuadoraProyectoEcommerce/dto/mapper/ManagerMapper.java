package com.LicuadoraProyectoEcommerce.dto.mapper;

import com.LicuadoraProyectoEcommerce.dto.ManagerDto;
import com.LicuadoraProyectoEcommerce.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManagerMapper {
    @Autowired
    private BaseProductMapper productMapper;
    public ManagerDto getDtoFromEntity(Manager manager){
        return new ManagerDto(manager.getUser().getName(), manager.getUser().getEmail(), productMapper.getListDtoFromListEntity(manager.getBaseProductList()));
    }
    public List<ManagerDto> listDtoFromListEntities(List<Manager> managerList){
        return managerList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }

}
