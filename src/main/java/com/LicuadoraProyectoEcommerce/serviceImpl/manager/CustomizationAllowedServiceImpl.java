package com.LicuadoraProyectoEcommerce.serviceImpl.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.mapper.manager.CustomizationAllowedMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.repository.manager.CustomizationAllowedRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.EnableAreaRepository;
import com.LicuadoraProyectoEcommerce.service.managerService.CustomizationAllowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomizationAllowedServiceImpl implements CustomizationAllowedService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private CustomizationAllowedRepository customizationAllowedRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private CustomizationAllowedMapper mapper;
    @Autowired
    private EnableAreaRepository enableAreaRepository;

    @Override
    public CustomizationAllowed findEntityById(Long id){
        return customizationAllowedRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
    @Override
    public CustomizationAllowedDto createEntity(CustomizationAllowedDto customizationAllowedDto) {
        if(customizationAllowedRepository.existsByType(customizationAllowedDto.getType())) throw new BadRequestException("the " + customizationAllowedDto.getType() + " type already exists in the database!!!");
        CustomizationAllowed entity = customizationAllowedRepository.save(mapper.createEntityFromDto(customizationAllowedDto));
        return mapper.getDtoFromEntity(entity);
    }

    @Override
    public CustomizationAllowedDto findDtoById(Long id) {
        return mapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Map<String, String> deleteEntityById(Long id) { //TODO falta borrar peronalizacion sin errores
        CustomizationAllowed entity = findEntityById(id);
        List<EnabledArea> enabledAreaList = new ArrayList<>(entity.getEnabledAreas());
        if(!enabledAreaList.isEmpty()) {
        if(enabledAreaList.stream().anyMatch(e-> !e.getSellerProducts().isEmpty()))
            throw new BadRequestException(messageHandler.message("cant.delete", null));
        int size = enabledAreaList.size();
            for (int i = 0; i < size; i++);{
                enabledAreaList.get(0).removeCustomizationAllowedToEnabledArea(entity);
                enableAreaRepository.save(enabledAreaList.get(0));
            }
        }
        customizationAllowedRepository.delete(entity);
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public List<CustomizationAllowedDto> findDtoListPagination(Integer page) {
        List<CustomizationAllowed> entities = customizationAllowedRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return mapper.getListDtoFromListEntity(entities);
    }

    @Override
    public CustomizationAllowedDto updateEntity(Long id, CustomizationAllowedDto customizationAllowedDto) {
        CustomizationAllowed entity = mapper.updateEntityFromDto(findEntityById(id), customizationAllowedDto);
        if(customizationAllowedRepository.existsByType(customizationAllowedDto.getType()))
            throw new BadRequestException("the " + customizationAllowedDto.getType() + " type already exists in the database!!!");
        return mapper.getDtoFromEntity(customizationAllowedRepository.save(entity));
    }

    @Override
    public CustomizationAllowed findByType(String type) {
        return customizationAllowedRepository.findByType(type)
                .orElseThrow(()-> new NotFoundException(messageHandler.message("not.found.name", type)));
    }
}
