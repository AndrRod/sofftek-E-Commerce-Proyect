package com.LicuadoraProyectoEcommerce.serviceImpl.manager;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDto;
import com.LicuadoraProyectoEcommerce.dto.manager.BaseProductDtoComplete;
import com.LicuadoraProyectoEcommerce.mapper.manager.BaseProductMapper;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.manager.EnabledArea;
import com.LicuadoraProyectoEcommerce.repository.manager.BaseProductRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.ManagerRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerCustomizationRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.managerService.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private SellerCustomizationRepository sellerCustomizationRepository;
    @Autowired
        private UserAuthService userAuthService;

    @Override
    public BaseProductDtoComplete createBaseProduct(BaseProductDto baseProductDto, HttpServletRequest request) {
        BaseProduct baseProduct = baseProductMapper.getEntityCreateFromDto(baseProductDto);
        baseProduct.setManagerCreator(userAuthService.findManagerLogged(request)); //TODO USAR HARCODEADO PARA PRUEBAS
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
    public Map<String, String> deleteBaseProductById(Long id) { //TODO falta borrado total en cascada hacia producto vendedor
        BaseProduct baseProduct = findEntityById(id);
//        baseProduct.getSellerProducts().forEach(sellerProduct -> {
//            if(sellerProduct.getBaseProduct().getId().equals(baseProduct.getId()))
//                sellerProduct.getAreas().stream().forEach(area->{
//                    sellerCustomizationRepository.deleteAll(area.getCustomizations());
//                });
//                sellerProductService.deleteByEntity(sellerProduct);
//        });
        baseProductRepository.delete(baseProduct);
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public BaseProductDtoComplete updateBaseProduct(Long id, BaseProductDto baseProductDto, HttpServletRequest request) {
        BaseProduct baseProduct = baseProductMapper.getEntityUpdateFromDto(findEntityById(id), baseProductDto);
        baseProduct.setManagerLastUpdate(userAuthService.findManagerLogged(request).getUser().getEmail()); //TODO USAR HARCODEADO PARA PRUEBAS
        BaseProduct baseProductSaved = baseProductRepository.save(baseProduct);
        return baseProductMapper.getDtoFromEntity(baseProductSaved);
    }
    @Override
    public BaseProduct findEntityById(Long id){
        return baseProductRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf((id)))));
    }
    @Override
    public BaseProductDtoComplete addEnabledAreaToEntity(Long id, EnabledArea enabledArea, HttpServletRequest request) {
        BaseProduct baseProduct = findEntityById(id);
        if(baseProduct.getEnabledAreas().contains(enabledArea)) throw new BadRequestException(messageHandler.message("entity.exists", enabledArea.getName()));
        baseProduct.setManagerLastUpdate(userAuthService.findManagerLogged(request).getUser().getEmail()); //TODO USAR HARCODEADO PARA PRUEBAS
        baseProduct.setUpdateDate(LocalDateTime.now());
        baseProduct.removeAreasToBaseProduct(baseProduct.getEnabledAreas().stream().filter(a->
                a.getName().equals(enabledArea.getName())).collect(Collectors.toList()));
        baseProduct.addAreaToBaseProduct(enabledArea);
        baseProductRepository.save(baseProduct);
        return baseProductMapper.getDtoFromEntity(baseProduct);
    }

    @Override
    public BaseProductDtoComplete removeEnabledAreaToEntity(Long id, EnabledArea enabledArea, HttpServletRequest request) {
        BaseProduct baseProduct = findEntityById(id);
        if(!baseProduct.getEnabledAreas().contains(enabledArea)) throw new BadRequestException(messageHandler.message("not.entity.exists", enabledArea.getName()));
        baseProduct.setManagerLastUpdate(userAuthService.findManagerLogged(request).getUser().getEmail()); //TODO USAR HARCODEADO PARA PRUEBAS
        baseProduct.setUpdateDate(LocalDateTime.now());
        baseProduct.removeAreaToBaseProduct(enabledArea);
        baseProductRepository.save(baseProduct);
        return baseProductMapper.getDtoFromEntity(baseProduct);
    }
}
