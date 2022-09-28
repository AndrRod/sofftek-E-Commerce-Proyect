package com.LicuadoraProyectoEcommerce.serviceImpl.seller;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerProductDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.dto.shoppingCart.ProductDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.seller.StoreMapper;
import com.LicuadoraProyectoEcommerce.model.seller.PaymentMethod;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.StoreRepository;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import com.LicuadoraProyectoEcommerce.service.sellerService.StoreService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;
@Service
public class StoreServiceImpl implements StoreService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired SellerProductService sellerProductService;
    @Override
    public SellerStoreCompleteDto createEntity(SellerStoreDto sellerStoreDto, HttpServletRequest request) {
        Store store= storeMapper.createEntityFromDto(sellerStoreDto);
        //        sellerProduct.setSeller(userAuthService.findSellerLogged(request)); //TODO VERIFICAR QUE ESTE TOMANDO USUARIO - HARCODEAR AQUI PARA PRUEBAS
// TODO AGREGAR METODO QUE VERIFIQUE QUE EL USARIO SOLO TIENE UNA TIENDA
        Seller seller = sellerRepository.findById(1L).get();
        seller.setStore(store);
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }

    @Override
    public SellerStoreCompleteDto updateEntity(Long id, SellerStoreDto sellerStoreDto) {
//        TODO VERIFICAR QUE SOLO EL DUEÑO DE LA TIENDA PUEDA MODIFICAR
        Store store  = storeMapper.updateEntityFromDto(findEntityById(id), sellerStoreDto);
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }

    @Override
    public SellerStoreCompleteDto findById(Long id, Integer page) {
        List<SellerProductDto> listDto = sellerProductService.listPartDtoPagination(page==null ? 0 : page);
        return storeMapper.getCompleteDtoAndProductPageFromEntity(findEntityById(id), listDto);
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
        if(store.getPaymentMethods().contains(PaymentMethod.valueOf(newPayMethod.toUpperCase(Locale.ROOT)))) throw new BadRequestException(messageHandler.message("already.exists", newPayMethod));
        Try.of(()-> store.getPaymentMethods().add(PaymentMethod.valueOf(newPayMethod.toUpperCase(Locale.ROOT)))).getOrElseThrow(()-> new NotFoundException(messageHandler.message("method.not.found", newPayMethod)));
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }

    @Override
    public SellerStoreCompleteDto removePaymentMethod(Long id, String newPayMethod) {
        Store store = findEntityById(id);
        if(!store.getPaymentMethods().contains(PaymentMethod.valueOf(newPayMethod.toUpperCase(Locale.ROOT)))) throw new BadRequestException(messageHandler.message("already.deleted", newPayMethod));
        Try.of(()-> store.getPaymentMethods().remove(PaymentMethod.valueOf(newPayMethod.toUpperCase(Locale.ROOT)))).getOrElseThrow(()-> new NotFoundException(messageHandler.message("method.not.found", newPayMethod)));
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store));
    }

    @Override
    public List<SellerStoreDto> listDtoPagination(Integer page) {
        List<Store> storeList = storeRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return storeMapper.getListDtoFromEntityList(storeList);
    }
}
