package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.CustomizationAllowedDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.CustomizationAllowedMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.repository.CustomizationAllowedRepository;
import com.LicuadoraProyectoEcommerce.service.CustomizationAllowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public class CustomizationAllowedServiceImpl implements CustomizationAllowedService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private CustomizationAllowedRepository customizationAllowedRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private CustomizationAllowedMapper mapper;

    private CustomizationAllowed findEntityById(Long id){
        return customizationAllowedRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
    @Override
    public CustomizationAllowedDto createEntity(CustomizationAllowedDto customizationAllowedDto) {
        CustomizationAllowed entity = customizationAllowedRepository.save(mapper.createEntityFromDto(customizationAllowedDto));
        return mapper.getDtoFromEntity(entity);
    }

    @Override
    public CustomizationAllowedDto findDtoById(Long id) {
        return mapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Map<String, String> deleteEntityById(Long id) {
        customizationAllowedRepository.delete(findEntityById(id));
        return Map.of("message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public List<CustomizationAllowedDto> findDtoListPagination(Integer page) {
        List<CustomizationAllowed> entities = customizationAllowedRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return mapper.getListDtoFromListEntity(entities);
    }

    @Override
    public CustomizationAllowedDto updateEntity(Long id, CustomizationAllowedDto customizationAllowedDto) {
        CustomizationAllowed entity = mapper.updateEntityFromDto(findEntityById(id), customizationAllowedDto);
        return mapper.getDtoFromEntity(entity);
    }
}
