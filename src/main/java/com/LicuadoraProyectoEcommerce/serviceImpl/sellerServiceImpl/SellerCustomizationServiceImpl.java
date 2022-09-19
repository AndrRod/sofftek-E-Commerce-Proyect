package com.LicuadoraProyectoEcommerce.serviceImpl.sellerServiceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.SellerCustomizationCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.SellerCustomizationMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.SellerCustomizationNameFrom;
import com.LicuadoraProyectoEcommerce.model.seller.SellerCustomization;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerCustomizationRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public SellerCustomization createAndUpdateEntity(SellerCustomization sellerCustomization, SellerCustomizationNameFrom NameForm) {
        return null;
    }

    @Override
    public SellerCustomizationCompleteDto findById(Long id) {
        return sellerCustomizationMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public SellerCustomization findEntityById(Long id) {
        return sellerCustomizationRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public List<SellerCustomizationCompleteDto> geDtoListPagination(Integer page) {
        List<SellerCustomization> list = sellerCustomizationRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return sellerCustomizationMapper.getDtoListFromEntityList(list);
    }

    @Override
    public void delete(SellerCustomization sellerCustomization) {
        sellerCustomizationRepository.delete(sellerCustomization);
    }
}
