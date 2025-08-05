package org.example.environement.controller;

import org.example.environement.dto.specie.SpecieDtoReceive;
import org.example.environement.dto.specie.SpecieDtoResponse;
import org.example.environement.service.SpecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/specie")
public class SpecieController {

    private final SpecieService specieService;

    public SpecieController(SpecieService specieService) {
        this.specieService = specieService;
    }

    @GetMapping
    public ResponseEntity<List<SpecieDtoResponse>> getSpecies (@RequestParam int pageSize, @RequestParam int pageNumber){
        return ResponseEntity.ok(specieService.get(pageSize,pageNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecieDtoResponse> getSpecieById (@PathVariable long id){
        return ResponseEntity.ok(specieService.get(id));
    }

    @PostMapping
    public ResponseEntity<SpecieDtoResponse> createSpecie (@RequestBody SpecieDtoReceive specieDtoReceive){
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.create(specieDtoReceive));
    }


}
