package com.LicuadoraProyectoEcommerce.serviceImpl.sellerServiceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.SellerCustomizationMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerCustomizationRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public SellerCustomizationCompleteDto updateEntity(Long idCustomization, SellerCustomizationDto sellerCustomizationForm) {
        SellerCustomization sellerCustomization = sellerCustomizationMapper.updateEntityFromDto(findEntityById(idCustomization), sellerCustomizationForm);
        SellerCustomization customizationSaved =  sellerCustomizationRepository.save(sellerCustomization);
        return sellerCustomizationMapper.getCompleteDtoFromEntity(customizationSaved);
    }

    @Override
    public Map<String, String> deleteEntityParams(Long idCustomization) {
        SellerCustomization customization=findEntityById(idCustomization);
        customization.setCustomizationPrice(0d);
        customization.setName(null);
        sellerCustomizationRepository.save(customization);
        return Map.of("Message", "the name and price params were reestablished");
    }
    @Override
    public Map<String, String> deleteEntity(Long idCustomization) {
        sellerCustomizationRepository.delete(findEntityById(idCustomization));
        return Map.of("Message", "entity was deleted");
    }


}
