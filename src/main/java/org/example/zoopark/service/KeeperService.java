package org.example.zoopark.service;

import org.example.zoopark.dto.KeeperDto;
import org.example.zoopark.entity.Keeper;

import java.util.List;

public interface KeeperService {
    KeeperDto create(KeeperDto keeperDto);
    List<KeeperDto> getAll();
    KeeperDto getById(Long id);
    KeeperDto update(Long id, KeeperDto keeperDto);
    void delete(Long id);
}