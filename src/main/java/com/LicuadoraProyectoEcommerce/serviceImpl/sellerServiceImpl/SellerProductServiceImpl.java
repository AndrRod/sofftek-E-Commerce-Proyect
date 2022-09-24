package com.LicuadoraProyectoEcommerce.serviceImpl.sellerServiceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerProductDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.SellerProductMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.SellerProductPriceForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.repository.manager.EnableAreaRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerAreaRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerCustomizationRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerProductRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SellerProductServiceImpl implements SellerProductService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private SellerProductRepository sellerProductRepository;
    @Autowired
    private EnableAreaRepository enableAreaRepository;
    @Autowired
    private SellerCustomizationRepository customizationRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private SellerProductMapper sellerProductMapper;
    @Autowired
    private SellerCustomizationRepository sellerCustomizationRepository;
    @Autowired
    private SellerAreaRepository sellerAreaRepository;

    @Override
    public SellerProduct findEntityById(Long id) {
        return sellerProductRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public SellerProductCompleteDto findById(Long id) {
        return sellerProductMapper.getCompleteDtoFromEntity(findEntityById(id));
    }

    @Override
    public List<SellerProductCompleteDto> listDtoPagination(Integer page) {
        List<SellerProduct> products = sellerProductRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return sellerProductMapper.getListCompleteDtoFromEntityList(products);
    }
    @Override
    public Map<String, String> deleteById(Long id) {
        deleteByEntity(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }
    @Override
    public SellerProductDto createAndUpdateEntity(BaseProduct baseProduct, SellerProductPriceForm sellerProductPriceForm) {
        SellerProduct sellerProduct = sellerProductMapper.createEntityFromDto(baseProduct, sellerProductPriceForm);
        baseProduct.getEnabledAreas().stream().forEach(enabledArea -> {
            List<SellerCustomization> listSellerCustomization = new ArrayList<>();
            SellerArea sellerArea = new SellerArea();
            sellerArea.setEnabledArea(enabledArea);
            enabledArea.getCustomizationsAllowed().stream().forEach(customizationAllowed -> {
                SellerCustomization sellerCustomization = new SellerCustomization();
                sellerCustomization.setCustomizationAllowed(customizationAllowed);
                SellerCustomization customizationSaved = customizationRepository.save(sellerCustomization);
                listSellerCustomization.add(customizationSaved);
            });
            sellerArea.getCustomizations().addAll(listSellerCustomization);
            sellerProduct.addAreaToSellerProduct(sellerArea);
        });
        return sellerProductMapper.getDtoFromEntity(sellerProductRepository.save(sellerProduct));
    }

    @Override
    public void deleteByEntity(SellerProduct sellerProduct) {
        List<SellerArea> areas = new ArrayList<>(sellerProduct.getAreas());
        for(SellerArea area : areas){
            int size = area.getCustomizations().size();
            for(int i= 0; i< size; i+=1){
                SellerCustomization customization = area.getCustomizations().get(0);
                customization.removeArea(area);
                customizationRepository.delete(customization);
            }
            area.removeProduct(sellerProduct);
            sellerAreaRepository.delete(area);
        }
        sellerProductRepository.delete(sellerProduct);
        }
}