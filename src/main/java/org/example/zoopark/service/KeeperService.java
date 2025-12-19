package org.example.zoopark.service;

import org.example.zoopark.dto.KeeperDto;

import java.util.List;
import java.util.function.BooleanSupplier;

public interface KeeperService {
    KeeperDto create(KeeperDto keeperDto);
    List<KeeperDto> getAll();
    KeeperDto getById(Long id);
    KeeperDto update(Long id, KeeperDto keeperDto);
    BooleanSupplier delete(Long id);
}