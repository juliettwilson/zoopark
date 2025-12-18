package org.example.zoopark.controller;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.KeeperDto;
import org.example.zoopark.service.KeeperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keepers")
@RequiredArgsConstructor
public class KeeperController {

    private final KeeperService keeperService;

    @GetMapping
    public ResponseEntity<List<KeeperDto>> getAll() {
        return new ResponseEntity<>(keeperService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeeperDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(keeperService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KeeperDto> create(@RequestBody KeeperDto keeperDto) {
        return new ResponseEntity<>(keeperService.create(keeperDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeeperDto> update(@PathVariable Long id, @RequestBody KeeperDto keeperDto) {
        return new ResponseEntity<>(keeperService.update(id, keeperDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        keeperService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
