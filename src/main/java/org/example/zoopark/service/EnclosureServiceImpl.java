package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.EnclosureDto;
import org.example.zoopark.entity.Enclosure;
import org.example.zoopark.mapper.EnclosureMapper;
import org.example.zoopark.repository.EnclosureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BooleanSupplier;

@Service
@RequiredArgsConstructor
public class EnclosureServiceImpl implements EnclosureService {

    private final EnclosureRepository enclosureRepository;
    private final EnclosureMapper enclosureMapper;

    @Override
    public EnclosureDto create(EnclosureDto enclosureDto) {
        Enclosure enclosure = enclosureMapper.toEntity(enclosureDto);
        Enclosure saved = enclosureRepository.save(enclosure);
        return enclosureMapper.toDto(saved);
    }

    @Override
    public List<EnclosureDto> getAll() {
        List<Enclosure> enclosures = enclosureRepository.findAll();
        return enclosureMapper.toDtoList(enclosures);
    }

    @Override
    public EnclosureDto getById(Long id) {
        Enclosure enclosure = enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found with id " + id));
        return enclosureMapper.toDto(enclosure);
    }

    @Override
    public EnclosureDto update(Long id, EnclosureDto enclosureDto) {
        Enclosure existing = enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found with id " + id));

        existing.setName(enclosureDto.getName());
        existing.setType(enclosureDto.getType());

        Enclosure updated = enclosureRepository.save(existing);
        return enclosureMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        Enclosure existing = enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found with id " + id));
        enclosureRepository.delete(existing);
    }
}
