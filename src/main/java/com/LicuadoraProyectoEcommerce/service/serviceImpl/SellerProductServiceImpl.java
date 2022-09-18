package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.SellerProductMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerProductRepository;
import com.LicuadoraProyectoEcommerce.service.SellerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SellerProductServiceImpl implements SellerProductService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private SellerProductRepository sellerProductRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private SellerProductMapper sellerProductMapper;
    @Override
    public SellerProduct findEntityById(Long id) {
        return sellerProductRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public SellerProductCompleteDto findById(Long id) {
        return sellerProductMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public List<SellerProductCompleteDto> listDtoPagination(Integer page) {
        List<SellerProduct> products = sellerProductRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return sellerProductMapper.getListDtoFromEntityList(products);
    }



    @Override
    public Map<String, String> deleteById(Long id) {
        sellerProductRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }
    @Override
    public SellerProductCompleteDto createEntity(BaseProduct baseProduct) { //TODO
//        SellerProduct sellerProduct = sellerProductMapper.createEntityFromDto(baseProduct, sellerProductDto);
//        sellerProduct.setAreas(baseProduct.getEnabledAreas());
//        new SellerProduct(baseProduct.getId(), null, )
        return null;
    }
    @Override
    public SellerProductCompleteDto updateEntity(Long id, SellerProductCompleteDto sellerProductDto) {
        return null;
    }
}
