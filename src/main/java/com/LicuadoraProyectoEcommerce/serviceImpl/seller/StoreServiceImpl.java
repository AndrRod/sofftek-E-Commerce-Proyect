package com.LicuadoraProyectoEcommerce.serviceImpl.seller;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.seller.StoreMapper;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.StoreRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.StoreService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private SellerRepository sellerRepository;
    @Override
    public SellerStoreCompleteDto createEntity(SellerStoreDto sellerStoreDto, HttpServletRequest request) {
        Store store= storeMapper.createEntityFromDto(sellerStoreDto);
        //        sellerProduct.setSeller(userAuthService.findSellerLogged(request)); //TODO VERIFICAR QUE ESTE TOMANDO USUARIO - HARCODEAR AQUI PARA PRUEBAS
// TODO AGREGAR METODO QUE VERIFIQUE QUE EL USARIO SOLO TIENE UNA TIENDA
        store.setSeller(sellerRepository.findById(1L).get());
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }

    @Override
    public SellerStoreCompleteDto updateEntity(Long id, SellerStoreDto sellerStoreDto) {
//        TODO VERIFICAR QUE SOLO EL DUEÑO DE LA TIENDA PUEDA MODIFICAR
        Store store  = storeMapper.updateEntityFromDto(findEntityById(id), sellerStoreDto);
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }

    @Override
    public SellerStoreCompleteDto findById(Long id) {
        return storeMapper.getCompleteDtoFromEntity(findEntityById(id));
    }

    @Override
    public Store findEntityById(Long id) {
        return storeRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public Map<String, String> deleteById(Long id) {
        storeRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public SellerStoreCompleteDto addNewPaymentMethod(Long id, String newPayMethod) {
        Store store = findEntityById(id);
        Try.of(()-> store.getPaymentMethods().add(PaymentMethod.valueOf(newPayMethod))).getOrElseThrow(()-> new NotFoundException(messageHandler.message("method.not.found", newPayMethod)));
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }

    @Override
    public SellerStoreCompleteDto removePaymentMethod(Long id, String newPayMethod) {
        Store store = findEntityById(id);
        Try.of(()-> store.getPaymentMethods().remove(PaymentMethod.valueOf(newPayMethod))).getOrElseThrow(()-> new NotFoundException(messageHandler.message("method.not.found", newPayMethod)));
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }
}
