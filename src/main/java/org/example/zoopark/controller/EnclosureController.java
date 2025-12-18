package org.example.zoopark.controller;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.EnclosureDto;
import org.example.zoopark.service.EnclosureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enclosures")
@RequiredArgsConstructor
public class EnclosureController {

    private final EnclosureService enclosureService;

    @GetMapping
    public ResponseEntity<List<EnclosureDto>> getAll() {
        return new ResponseEntity<>(enclosureService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnclosureDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(enclosureService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnclosureDto> create(@RequestBody EnclosureDto enclosureDto) {
        return new ResponseEntity<>(enclosureService.create(enclosureDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnclosureDto> update(@PathVariable Long id, @RequestBody EnclosureDto enclosureDto) {
        return new ResponseEntity<>(enclosureService.update(id, enclosureDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enclosureService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
