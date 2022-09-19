package com.LicuadoraProyectoEcommerce.serviceImpl.sellerServiceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.SellerAreaCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.SellerAreaDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.SellerAreaMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.seller.SellerArea;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerAreaRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerAreaServiceImpl implements SellerAreaService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private SellerAreaRepository sellerAreaRepository;
    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private SellerAreaMapper sellerAreaMapper;
    @Override
    public SellerArea createAndUpdateEntity(SellerArea sellerArea) {
        return sellerAreaRepository.save(sellerArea);
    }

    @Override
    public SellerAreaDto findById(Long id) {
        return sellerAreaMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public SellerArea findEntityById(Long id) {
        return sellerAreaRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public List<SellerAreaCompleteDto> geDtoListPagination(Integer page) {
        List<SellerArea> list = sellerAreaRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return sellerAreaMapper.getListCompleteDtoFromEntityList(list);
    }

    @Override
    public void delete(SellerArea area) {
        sellerAreaRepository.delete(area);
    }
}
