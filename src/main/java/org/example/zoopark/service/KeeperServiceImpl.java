package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.entity.Keeper;
import org.example.zoopark.repository.KeeperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeeperServiceImpl implements KeeperService {

    private final KeeperRepository keeperRepository;

    @Override
    public Keeper save(Keeper keeper) {
        return keeperRepository.save(keeper);
    }

    @Override
    public List<Keeper> findAll() {
        return keeperRepository.findAll();
    }

    @Override
    public Keeper findById(Long id) {
        return keeperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Keeper not found with id " + id));
    }

    @Override
    public void deleteById(Long id) {
        keeperRepository.deleteById(id);
    }
}
