package com.LicuadoraProyectoEcommerce.serviceImpl.seller;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.PublicationDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreCompleteDto;
import com.LicuadoraProyectoEcommerce.dto.seller.SellerStoreDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.mapper.seller.StoreMapper;
import com.LicuadoraProyectoEcommerce.model.seller.*;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.StoreRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.sellerService.PublicationService;
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
    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private PublicationService publicationService;
    @Override
    public SellerStoreCompleteDto createEntity(SellerStoreDto sellerStoreDto, HttpServletRequest request) {
        Store store= storeMapper.createEntityFromDto(sellerStoreDto);
        Seller seller = userAuthService.findSellerLogged(request);; //TODO VERIFICAR QUE ESTE TOMANDO USUARIO - HARCODEAR AQUI PARA PRUEBAS
        if(seller.getStore()!= null) throw new BadRequestException("this user already have a store called " + seller.getStore().getName());
        seller.setStore(store);
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store), null);
    }

    @Override
    public SellerStoreCompleteDto updateEntity(Long idStore, SellerStoreDto sellerStoreDto, HttpServletRequest request) {
        Seller sellerLogged = userAuthService.findSellerLogged(request);
        Store sellerStore = findEntityById(idStore);
        userAuthService.isTheSameUserLogged(sellerStore.getSeller().getUser(), request);
        Store storeUpdate  = storeMapper.updateEntityFromDto(sellerStore, sellerStoreDto);
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(storeUpdate), null);
    }

    @Override
    public SellerStoreCompleteDto findById(Long id, Integer page, String state) {
        List<PublicationDto> publicationDto = publicationService.getListEntityPage(page, state);
        return storeMapper.getCompleteDtoFromEntity(findEntityById(id), publicationDto);
    }

    @Override
    public Store findEntityById(Long id) {
        return storeRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
    @Override
    public Map<String, String> deleteById(Long id, HttpServletRequest request) {
        Store store = findEntityById(id);
        userAuthService.isTheSameUserLogged(store.getSeller().getUser(), request);
        storeRepository.delete(store);
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public SellerStoreCompleteDto addNewPaymentMethod(Long id, String newPayMethod, HttpServletRequest request) {
        Store store = findEntityById(id);
        userAuthService.isTheSameUserLogged(store.getSeller().getUser(), request); //TODO VER MEJORAR
        PaymentMethod paymentMethod = getPaymentMethodFromStringOrThrowException(newPayMethod);
        if(store.getPaymentMethods().contains(paymentMethod)) throw new BadRequestException(messageHandler.message("already.exists", newPayMethod.toUpperCase(Locale.ROOT)));
        store.getPaymentMethods().add(paymentMethod);
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store), null);
    }

    @Override
    public SellerStoreCompleteDto removePaymentMethod(Long id, String newPayMethod, HttpServletRequest request) {
        Store store = findEntityById(id);
        userAuthService.isTheSameUserLogged(store.getSeller().getUser(), request);
        PaymentMethod paymentMethod = getPaymentMethodFromStringOrThrowException(newPayMethod);
        if(!store.getPaymentMethods().contains(paymentMethod)) throw new BadRequestException(messageHandler.message("already.deleted", newPayMethod.toUpperCase(Locale.ROOT)));
        store.getPaymentMethods().remove(paymentMethod);
        return storeMapper.getCompleteDtoFromEntity(storeRepository.save(store), null);
    }
    public PaymentMethod getPaymentMethodFromStringOrThrowException(String paymentMethod){
        return Try.of(() ->PaymentMethod.valueOf(paymentMethod.toUpperCase(Locale.ROOT)))
                .getOrElseThrow(()-> new NotFoundException(messageHandler.message("method.not.found", paymentMethod.toUpperCase(Locale.ROOT))));
    }
    @Override
    public List<SellerStoreDto> listDtoPagination(Integer page) {
        List<Store> storeList = storeRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return storeMapper.getListDtoFromEntityList(storeList);
    }
}
