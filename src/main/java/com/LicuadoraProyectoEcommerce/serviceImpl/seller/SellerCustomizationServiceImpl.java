package com.LicuadoraProyectoEcommerce.serviceImpl.seller;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.mapper.seller.SellerCustomizationMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerCustomizationRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class SellerCustomizationServiceImpl implements SellerCustomizationService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private SellerCustomizationRepository sellerCustomizationRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private SellerCustomizationMapper sellerCustomizationMapper;

    @Autowired
    private UserAuthService userAuthService;
    @Override
    public SellerCustomizationCompleteDto findById(Long id) {
        return sellerCustomizationMapper.getCompleteDtoFromEntity(findEntityById(id));
    }

    @Override
    public SellerCustomization findEntityById(Long id) {
        return sellerCustomizationRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public List<SellerCustomizationCompleteDto> geDtoListPagination(Integer page) {
        List<SellerCustomization> list = sellerCustomizationRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return sellerCustomizationMapper.getCompleteDtoListFromEntityList(list);
    }

    @Override
    public SellerCustomizationCompleteDto updateEntity(Long idCustomization, SellerCustomizationDto sellerCustomizationForm, HttpServletRequest request) {
        SellerCustomization sellerCustomizationFound = findEntityById(idCustomization);
        userAuthService.isSellerProductSellerCreator(request, sellerCustomizationFound.getAreas().get(0).getSellerProducts().get(0)); //TODO SACAR PARA PRUEBAS - verifica usuario
        SellerCustomization sellerCustomization = sellerCustomizationMapper.updateEntityFromDto(sellerCustomizationFound, sellerCustomizationForm);

        SellerCustomization customizationSaved =  sellerCustomizationRepository.save(sellerCustomization);
        return sellerCustomizationMapper.getCompleteDtoFromEntity(customizationSaved);
    }

    @Override
    public Map<String, String> deleteEntityParams(Long idCustomization, HttpServletRequest request) {
        SellerCustomization customization=findEntityById(idCustomization);
        userAuthService.isSellerProductSellerCreator(request, customization.getAreas().get(0).getSellerProducts().get(0)); //TODO SACAR PARA PRUEBAS - verifica usuario
        customization.setCustomizationPrice(0d);
        customization.setName(null);
        sellerCustomizationRepository.save(customization);
        return Map.of("Message", "the name and price params were reestablished");
    }
    @Override
    public Map<String, String> deleteEntity(Long idCustomization, HttpServletRequest request) {
        SellerCustomization customization=findEntityById(idCustomization);
        userAuthService.isSellerProductSellerCreator(request, customization.getAreas().get(0).getSellerProducts().get(0)); //TODO SACAR PARA PRUEBAS - verifica usuario
        sellerCustomizationRepository.delete(customization);
        return Map.of("Message", "entity was deleted");
    }


}
