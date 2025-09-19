package com.hackathon.mipildora.services;

import com.hackathon.mipildora.dtos.MedicationRequest;
import com.hackathon.mipildora.dtos.MedicationResponse;
import com.hackathon.mipildora.dtos.MedicationTakenRequest;

import java.util.List;

public interface MedicationService {
    List<MedicationResponse> getAllMedication();
    MedicationResponse createMedication(MedicationRequest medicationRequest);
    MedicationResponse updateTakenMedication(Long id, MedicationTakenRequest medicationRequest);
    MedicationResponse updateMedication(Long id, MedicationRequest medicationRequest);
    void deleteMedicationById(Long id);
}