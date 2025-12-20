package org.example.zoopark.service;

import org.example.zoopark.dto.EnclosureDto;
import java.util.List;
import java.util.function.BooleanSupplier;

public interface EnclosureService {
    EnclosureDto create(EnclosureDto enclosureDto);
    List<EnclosureDto> getAll();
    EnclosureDto getById(Long id);
    EnclosureDto update(Long id, EnclosureDto enclosureDto);
    BooleanSupplier delete(Long id);
}