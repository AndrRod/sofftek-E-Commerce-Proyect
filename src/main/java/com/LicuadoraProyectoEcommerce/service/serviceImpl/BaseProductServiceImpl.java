package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.dto.mapper.BaseProductMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.BaseProduct;
import com.LicuadoraProyectoEcommerce.repository.BaseProductRepository;
import com.LicuadoraProyectoEcommerce.service.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseProductServiceImpl implements BaseProductService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private BaseProductRepository baseProductRepository;
    @Autowired
    private BaseProductMapper baseProductMapper;
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public BaseProductDtoComplete createBaseProduct(BaseProductDto baseProductDto) {
        BaseProduct baseProduct = baseProductRepository.save(baseProductMapper.getEntityCreateFromDto(baseProductDto));
        return baseProductMapper.getDtoFromEntity(baseProduct);
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
    public BaseProduct findEntityById(Long id){
        return baseProductRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf((id)))));
    }
}
