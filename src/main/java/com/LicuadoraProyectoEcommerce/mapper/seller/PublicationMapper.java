package com.LicuadoraProyectoEcommerce.mapper.seller;

import com.LicuadoraProyectoEcommerce.dto.seller.PublicationDto;
import com.LicuadoraProyectoEcommerce.form.PublicationForm;
import com.LicuadoraProyectoEcommerce.model.seller.Publication;
import com.LicuadoraProyectoEcommerce.model.seller.PublicationSate;
import com.LicuadoraProyectoEcommerce.model.seller.SellerProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PublicationMapper {
    public Publication createEntityFromForm(SellerProduct sellerProduct, PublicationForm publicationForm) {
        return new Publication(sellerProduct, publicationForm.getDescription(), PublicationSate.valueOf(publicationForm.getPublicationSate().toUpperCase(Locale.ROOT)));
    }
    public Publication updateEntityFromForm(Publication publication, PublicationForm publicationForm){
        Stream.of(publicationForm).forEach(dto->{
            if(dto.getPublicationSate()!=null) publication.setPublicationSate(PublicationSate.valueOf(dto.getPublicationSate().toUpperCase(Locale.ROOT)));
            if(dto.getDescription() != null) publication.setDescription(dto.getDescription());
        });
        return publication;
    }

    public PublicationDto getDtoFromEntity(Publication publication) {
        return new PublicationDto(publication.getId(), publication.getStore().getName(),publication.getSellerProduct().getBaseProduct().getName(), publication.getSellerProduct().getDescription(), publication.getSellerProduct().getFinalPrice(), publication.getDescription(), publication.getPublicationSate(), publication.getUpdateDate());
    }

    public List<PublicationDto> getListDtoFromListEntity(List<Publication> publications) {
        return publications.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
}
