package org.example.zoopark.controller;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.AnimalDto;
import org.example.zoopark.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAll() {
        return new ResponseEntity<>(animalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(animalService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnimalDto> create(@RequestBody AnimalDto animalDto) {
        return new ResponseEntity<>(animalService.create(animalDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDto> update(@PathVariable Long id, @RequestBody AnimalDto animalDto) {
        return new ResponseEntity<>(animalService.update(id, animalDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
