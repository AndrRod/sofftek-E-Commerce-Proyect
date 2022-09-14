package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.SellerDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.SellerMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.Seller;
import com.LicuadoraProyectoEcommerce.repository.SellerRepository;
import com.LicuadoraProyectoEcommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SellerServiceImpl implements SellerService{
    private static final int SIZE_TEN = 10;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private SellerMapper sellerMapper;
    @Override
    public SellerDto getById(Long id) {
        return sellerMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        sellerRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public List<SellerDto> getListEntityPage(Integer page) {
        List<Seller> sellers = sellerRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return sellerMapper.listDtoFromListEntities(sellers);

    }
    public Seller findEntityById(Long id){
        return sellerRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
}
