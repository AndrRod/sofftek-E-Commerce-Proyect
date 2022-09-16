package com.LicuadoraProyectoEcommerce.service.serviceImpl;

import com.LicuadoraProyectoEcommerce.config.MessageHandler;
import com.LicuadoraProyectoEcommerce.dto.EnableAreaDto;
import com.LicuadoraProyectoEcommerce.dto.mapper.EnableAreaMapper;
import com.LicuadoraProyectoEcommerce.exception.NotFoundException;
import com.LicuadoraProyectoEcommerce.model.EnabledArea;
import com.LicuadoraProyectoEcommerce.repository.EnableAreaRepository;
import com.LicuadoraProyectoEcommerce.service.EnabledAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EnabledAreaServiceImpl implements EnabledAreaService {
    private static final int SIZE_TEN = 10;
    @Autowired
    private EnableAreaRepository enableAreaRepository;
    @Autowired
    private EnableAreaMapper enableAreaMapper;
    @Autowired
    private MessageHandler messageHandler;

    public EnabledArea findEntityById(Long id){
        return enableAreaRepository.findById(id).orElseThrow(()-> new NotFoundException(messageHandler.message("not.found", String.valueOf(id))));
    }
    @Override
    public EnableAreaDto createEntity(EnableAreaDto enableAreaDto) {
        EnabledArea enabledArea = enableAreaRepository.save(enableAreaMapper.createEntityFromDto(enableAreaDto));
        return enableAreaMapper.getDtoFromEntity(enabledArea);
    }

    @Override
    public EnableAreaDto findById(Long id) {
        return enableAreaMapper.getDtoFromEntity(findEntityById(id));
    }

    @Override
    public Map<String, String> deleteEntityById(Long id) {
        enableAreaRepository.delete(findEntityById(id));
        return Map.of("Message", messageHandler.message("delete.success", String.valueOf(id)));
    }

    @Override
    public List<EnableAreaDto> findDtoListPagination(Integer page) {
        List<EnabledArea> enabledAreas = enableAreaRepository.findAll(PageRequest.of(page, SIZE_TEN)).getContent();
        return enableAreaMapper.getListDtoFromListEntity(enabledAreas);
    }

    @Override
    public EnableAreaDto updateEntity(Long id, EnableAreaDto enableAreaDto) {
        EnabledArea enabledArea = enableAreaMapper.updateEntityFrom(findEntityById(id), enableAreaDto);
        return enableAreaMapper.getDtoFromEntity(enableAreaRepository.save(enabledArea));
    }
}
