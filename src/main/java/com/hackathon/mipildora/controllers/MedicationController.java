package com.hackathon.mipildora.controllers;

import com.hackathon.mipildora.dtos.MedicationRequest;
import com.hackathon.mipildora.dtos.MedicationResponse;
import com.hackathon.mipildora.services.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping
    public ResponseEntity<MedicationResponse> createMedication(@RequestBody MedicationRequest medicationRequest) {
        MedicationResponse response = medicationService.createMedication(medicationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MedicationResponse>> getAllMedications() {
        List<MedicationResponse> medications = medicationService.getAllMedication();
        return ResponseEntity.ok(medications);
    }

    @PutMapping("/{id}/taken")
    public ResponseEntity<MedicationResponse> updateMedication(@PathVariable Long id, @Valid @RequestBody MedicationRequest medicationRequest) {
        MedicationResponse updatedMedication = medicationService.updateMedication(id, medicationRequest);
        return new ResponseEntity<>(updatedMedication, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicationById(@PathVariable Long id) {
        medicationService.deleteMedicationById(id);
        return new ResponseEntity<>("Medication with id: " + id + " has been deleted", HttpStatus.OK);
    }
}
