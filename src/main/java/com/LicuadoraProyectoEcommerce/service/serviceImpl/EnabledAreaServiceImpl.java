package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.EnableAreaMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.repository.manager.EnableAreaRepository;
import com.LicuadoraProyectoEcommerce.service.EnabledAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EnabledAreaServiceImpl implements EnabledAreaService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private EnableAreaRepository enableAreaRepository;
    @Autowired
    private EnableAreaMapper enableAreaMapper;
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public EnabledArea findEntityById(Long id){
        return enableAreaRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
    @Override
    public EnabledAreaDto createEntity(EnabledAreaDto enabledAreaDto) {
        EnabledArea area = enableAreaMapper.createEntityFromDto(enabledAreaDto);
        return enableAreaMapper.getDtoFromEntity(enableAreaRepository.save(area));
    }

    @Override
    public EnabledAreaDto findById(Long id) {
        return enableAreaMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Map<String, String> deleteEntityById(Long id) {
        enableAreaRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public List<EnabledAreaCompleteDto> findDtoListPagination(Integer page) {
        List<EnabledArea> enabledAreas = enableAreaRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return enableAreaMapper.getListCompleteDtoFromListEntity(enabledAreas);
    }

    @Override
    public EnabledAreaDto updateEntity(Long id, EnabledAreaDto enableAreaDto) {
        EnabledArea enabledArea = enableAreaMapper.updateEntityFrom(findEntityById(id), enableAreaDto);
        return enableAreaMapper.getDtoFromEntity(enableAreaRepository.save(enabledArea));
    }

    @Override
    public EnabledArea findByName(String name) {
        return enableAreaRepository.findByName(name).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found.name", name)));
    }

    @Override
    public EnabledAreaCompleteDto addCustomizationAllowedToEntity(Long id, CustomizationAllowed customizationAllowed) {
        EnabledArea enabledArea = findEntityById(id);
        if(enabledArea.getCustomizationsAllowed().contains(customizationAllowed)) throw new BadRequestException(messageHandler.message("entity.exists", customizationAllowed.getType()));
        enabledArea.addCustomizationAllowedToEnabledArea(customizationAllowed);
        enableAreaRepository.save(enabledArea);
        return enableAreaMapper.getCompleteDtoFromEntity(enabledArea);
    }

    @Override
    public EnabledAreaCompleteDto removeCustomizationAllowedToEntity(Long id, CustomizationAllowed customizationAllowed) {
        EnabledArea enabledArea = findEntityById(id);
        if(!enabledArea.getCustomizationsAllowed().contains(customizationAllowed)) throw new BadRequestException(messageHandler.message("not.entity.exists", customizationAllowed.getType()));
        enabledArea.removeCustomizationAllowedToEnabledArea(customizationAllowed);
        enableAreaRepository.save(enabledArea);
        return enableAreaMapper.getCompleteDtoFromEntity(enabledArea);
    }
}
