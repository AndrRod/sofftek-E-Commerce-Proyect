package com.LicuadoraProyectoEcommerce.serviceImpl.seller;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.seller.PublicationDto;
import com.LicuadoraProyectoEcommerce.exception.BadRequestException;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.form.PublicationForm;
import com.LicuadoraProyectoEcommerce.mapper.seller.PublicationMapper;
import com.LicuadoraProyectoEcommerce.model.seller.Publication;
import com.LicuadoraProyectoEcommerce.model.seller.PublicationSate;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import com.LicuadoraProyectoEcommerce.model.seller.Store;
import com.LicuadoraProyectoEcommerce.repository.seller.PublicationRepository;
import com.LicuadoraProyectoEcommerce.service.UserAuth.UserAuthService;
import com.LicuadoraProyectoEcommerce.service.sellerService.PublicationService;
import com.LicuadoraProyectoEcommerce.service.sellerService.SellerProductService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
@Service
public class PublicationServiceImpl implements PublicationService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private SellerProductService sellerProductService;
    @Autowired
    private PublicationMapper publicationMapper;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private MessageHandler messageHandler;
    @Override
    public PublicationDto createEntity(Long idSellerProduct, PublicationForm publicationForm, HttpServletRequest request) {
        Store store = Optional.ofNullable(userAuthService.findSellerLogged(request).getStore()).orElseThrow(()-> new BadRequestException("To add a new publication first create a store"));
        SellerProduct sellerProduct = sellerProductService.findEntityById(idSellerProduct);
        if(publicationRepository.existsBySellerProduct(sellerProduct)) throw new BadRequestException("already exists a publication with the product id " + idSellerProduct);
        getPublicationStateFromStringOrThrowException(publicationForm.getPublicationSate());
        Publication publicationSave = publicationMapper.createEntityFromForm(sellerProduct, publicationForm);
        publicationSave.setStore(store);
        publicationRepository.save(publicationSave);
        return publicationMapper.getDtoFromEntity(publicationSave);
    }
    @Override
    public PublicationDto updateEntity(Long id, Long idSellerProduct, PublicationForm publicationForm, HttpServletRequest request) {
        getPublicationStateFromStringOrThrowException(publicationForm.getPublicationSate());
        Publication publicationSave = publicationMapper.updateEntityFromForm(findEntityById(id),publicationForm);
        if(idSellerProduct!=null){
            SellerProduct sellerProduct = sellerProductService.findEntityById(idSellerProduct);
            publicationSave.setSellerProduct(sellerProduct);
        }
        userAuthService.isSellerProductSellerCreator(request, publicationSave.getSellerProduct());
        return publicationMapper.getDtoFromEntity(publicationRepository.save(publicationSave));
    }

    @Override
    public Map<String, String> deleteById(Long id, HttpServletRequest request) {
        Publication publication = findEntityById(id);
        userAuthService.isSellerProductSellerCreator(request, publication.getSellerProduct());
        publicationRepository.delete(publication);
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public List<PublicationDto> getListEntityPage(Integer page, String state) {
        if(state!= null) {
            PublicationSate publicationSate = getPublicationStateFromStringOrThrowException(state);
            List<Publication> publications = publicationRepository.findByPublicationSate(publicationSate, PageRequest.of(page, SIZE_TEN));
            return publicationMapper.getListDtoFromListEntity(publications);
        }
        List<Publication> publications = publicationRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return publicationMapper.getListDtoFromListEntity(publications);
    }

    @Override
    public Publication findEntityById(Long id) {
        return publicationRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }

    @Override
    public PublicationDto findById(Long id) {
        return publicationMapper.getDtoFromEntity(findEntityById(id));
    }
    public PublicationSate getPublicationStateFromStringOrThrowException(String publication){
        return Try.of(()-> PublicationSate.valueOf(publication.toUpperCase(Locale.ROOT))).getOrElseThrow(() -> new NotFoundException("the " + publication + " state is not valid"));
    }
}
