package com.LicuadoraProyectoEcommerce.serviceImpl.seller;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.mapper.seller.SellerProductMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.SellerProductForm;
import com.LicuadoraProyectoEcommerce.model.manager.BaseProduct;
import com.LicuadoraProyectoEcommerce.model.seller.*;
import com.LicuadoraProyectoEcommerce.repository.manager.EnableAreaRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.*;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public SellerProduct findEntityById(Long id) {
        return sellerProductRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id) + " (seller product)")));
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
    public List<SellerProductDto> listPartDtoPagination(Integer page) {
        List<SellerProduct> products = sellerProductRepository.findProductsByPublication(PublicationSate.valueOf("CANCELLED"), PageRequest.of(page, SIZE_TEN));
        return sellerProductMapper.getListDtoFromEntityList(products);
    }

    @Override
    public Map<String, String> deleteById(Long id, HttpServletRequest request) {
        SellerProduct sellerProduct = findEntityById(id);
        userAuthService.isSellerProductSellerCreator(request, sellerProduct); //TODO SACAR PARA PRUEBAS - verifica usuario
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
        if(sellerProduct.getPublication()!= null)publicationRepository.delete(sellerProduct.getPublication());
        sellerProductRepository.delete(sellerProduct);
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }
    @Override
    public SellerProductDto createEntity(BaseProduct baseProduct, SellerProductForm sellerProductForm, HttpServletRequest request) {
        SellerProduct sellerProduct = sellerProductMapper.createEntityFromDto(baseProduct, sellerProductForm);
        sellerProduct.setSeller(userAuthService.findSellerLogged(request)); //TODO VERIFICAR QUE ESTE TOMANDO USUARIO - HARCODEAR AQUI PARA PRUEBAS
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
    public SellerProductDto updateEntity(Long id, SellerProductForm sellerProductForm, HttpServletRequest request) {
        SellerProduct sellerProduct = findEntityById(id);
        userAuthService.isSellerProductSellerCreator(request, sellerProduct); //TODO SACAR PARA PRUEBAS - verifica usuario
        SellerProduct sellerProductSaved = sellerProductMapper.updateEntityFromDto(sellerProduct, sellerProductForm);
        sellerProductRepository.save(sellerProductSaved);
        return sellerProductMapper.getDtoFromEntity(sellerProductSaved);
    }

}