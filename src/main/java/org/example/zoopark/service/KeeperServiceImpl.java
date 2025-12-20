package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.KeeperDto;
import org.example.zoopark.entity.Keeper;
import org.example.zoopark.mapper.KeeperMapper;
import org.example.zoopark.repository.KeeperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BooleanSupplier;

@Service
@RequiredArgsConstructor
public class KeeperServiceImpl implements KeeperService {

    private final KeeperRepository keeperRepository;
    private final KeeperMapper keeperMapper;

    @Override
    public KeeperDto create(KeeperDto keeperDto) {
        Keeper keeper = keeperMapper.toEntity(keeperDto);
        Keeper saved = keeperRepository.save(keeper);
        return keeperMapper.toDto(saved);
    }

    @Override
    public List<KeeperDto> getAll() {
        List<Keeper> keepers = keeperRepository.findAll();
        return keeperMapper.toDtoList(keepers);
    }

    @Override
    public KeeperDto getById(Long id) {
        Keeper keeper = keeperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Keeper not found with id " + id));
        return keeperMapper.toDto(keeper);
    }

    @Override
    public KeeperDto update(Long id, KeeperDto keeperDto) {
        Keeper existing = keeperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Keeper not found with id " + id));

        existing.setName(keeperDto.getName());
        existing.setExperience(keeperDto.getExperience());

        Keeper updated = keeperRepository.save(existing);
        return keeperMapper.toDto(updated);
    }

    @Override
    public BooleanSupplier delete(Long id) {
        Keeper existing = keeperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Keeper not found with id " + id));
        keeperRepository.delete(existing);
        return () -> true;
    }
}
