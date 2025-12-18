package org.example.zoopark.service;

import org.example.zoopark.dto.EnclosureDto;
import java.util.List;

public interface EnclosureService {
    EnclosureDto create(EnclosureDto enclosureDto);
    List<EnclosureDto> getAll();
    EnclosureDto getById(Long id);
    EnclosureDto update(Long id, EnclosureDto enclosureDto);
    void delete(Long id);
}