package com.LicuadoraProyectoEcommerce.serviceImpl.managerServiceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.dto.mapper.BaseProductMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.repository.manager.BaseProductRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.ManagerRepository;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BaseProductServiceImpl implements BaseProductService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private BaseProductRepository baseProductRepository;
    @Autowired
    private BaseProductMapper baseProductMapper;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public BaseProductDtoComplete createBaseProduct(BaseProductDto baseProductDto) {
        BaseProduct baseProduct = baseProductMapper.getEntityCreateFromDto(baseProductDto);
        baseProduct.setManager(managerRepository.findById(1L).get()); //TODO
        return baseProductMapper.getDtoFromEntity(baseProductRepository.save(baseProduct));
    }

    @Override
    public BaseProductDtoComplete getBaseProductById(Long id) {
        return baseProductMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public List<BaseProductDtoComplete> getBaseProductListPage(Integer page) {
        return baseProductMapper.getListDtoCompleteFromListEntity(baseProductRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent());
    }

    @Override
    public Map<String, String> deleteBaseProductById(Long id) {
        baseProductRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public BaseProductDtoComplete updateBaseProduct(Long id, BaseProductDto baseProductDto) {
        BaseProduct baseProduct= baseProductRepository.save(baseProductMapper.getEntityUpdateFromDto(findEntityById(id), baseProductDto));
        return baseProductMapper.getDtoFromEntity(baseProduct);
    }
    @Override
    public BaseProduct findEntityById(Long id){
        return baseProductRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf((id)))));
    }
    @Override
    public BaseProductDtoComplete addEnabledAreaToEntity(Long id, EnabledArea enabledArea) {
        BaseProduct baseProduct = findEntityById(id);
        if(baseProduct.getEnabledAreas().contains(enabledArea)) throw new BadRequestException(messageHandler.message("entity.exists", enabledArea.getName()));
        baseProduct.removeAreasToBaseProduct(baseProduct.getEnabledAreas().stream().filter(a->
                a.getName().equals(enabledArea.getName())).collect(Collectors.toList()));
        baseProduct.addAreaToBaseProduct(enabledArea);
        baseProductRepository.save(baseProduct);
        return baseProductMapper.getDtoFromEntity(baseProduct);
    }

    @Override
    public BaseProductDtoComplete removeEnabledAreaToEntity(Long id, EnabledArea enabledArea) {
        BaseProduct baseProduct = findEntityById(id);
        if(!baseProduct.getEnabledAreas().contains(enabledArea)) throw new BadRequestException(messageHandler.message("not.entity.exists", enabledArea.getName()));
        baseProduct.removeAreaToBaseProduct(enabledArea);
        baseProductRepository.save(baseProduct);
        return baseProductMapper.getDtoFromEntity(baseProduct);
    }
}
