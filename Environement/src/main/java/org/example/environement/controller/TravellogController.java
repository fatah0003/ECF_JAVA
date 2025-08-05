package org.example.environement.controller;

import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.service.TravellogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travellog")
public class TravellogController {

    private final TravellogService travellogService;

    public TravellogController(TravellogService travellogService) {
        this.travellogService = travellogService;
    }

    @GetMapping
    public ResponseEntity<List<TravellogDtoResponse>> getAllTravellogs() {
        return ResponseEntity.ok(travellogService.get(10));
    }

    @GetMapping("/stats/{id}")
    public ResponseEntity<TravellogDtoStat> getStatFromObservation(@PathVariable long id) {
        return ResponseEntity.ok(travellogService.getStat(id));
    }


}
