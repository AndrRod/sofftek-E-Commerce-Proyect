package com.LicuadoraProyectoEcommerce.serviceImpl.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.manager.EnabledAreaDto;
import com.LicuadoraProyectoEcommerce.mapper.manager.EnableAreaMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.CustomizationAllowed;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.repository.manager.BaseProductRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.CustomizationAllowedRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.EnableAreaRepository;
import com.LicuadoraProyectoEcommerce.service.managerService.EnabledAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private CustomizationAllowedRepository customizationAllowedRepository;
    @Autowired
    private BaseProductRepository baseProductRepository;
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
    public EnabledAreaCompleteDto findById(Long id) {
        return enableAreaMapper.getCompleteDtoFromEntity(findEntityById(id));
    }

    @Override
    public Map<String, String> deleteEntityById(Long id) { //TODO puede borrar solo si nadie usa
        EnabledArea enabledArea =findEntityById(id);
        if(!enabledArea.getSellerProducts().isEmpty()) throw new BadRequestException(messageHandler.message("cant.delete", null));
        // para eludir ConcurrentModificationException (stream.foreach error cuando se intenta remover un indice durante iteracion)
        List<BaseProduct> baseProducts = new ArrayList<>(enabledArea.getBaseProducts());
        int sizeProducts = baseProducts.size();
        for(int i = 0; i< sizeProducts; i++){
            BaseProduct baseProduct = baseProducts.get(0);
            enabledArea.removeProduct(baseProduct);
        }
        List<CustomizationAllowed> customizationsAllowed = new ArrayList<>(enabledArea.getCustomizationsAllowed());
        int sizeCustomizations = customizationsAllowed.size();
        for (int i = 0; i < sizeCustomizations; i++){
            CustomizationAllowed customizationAllowed = customizationsAllowed.get(0);
            enabledArea.removeCustomizationAllowedToEnabledArea(customizationAllowed);
        }
        enableAreaRepository.delete(enabledArea);
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
