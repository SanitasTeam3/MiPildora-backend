package com.hackathon.mipildora.services;

import com.hackathon.mipildora.dtos.MedicationMapper;
import com.hackathon.mipildora.dtos.MedicationRequest;
import com.hackathon.mipildora.dtos.MedicationResponse;
import com.hackathon.mipildora.models.Medication;
import com.hackathon.mipildora.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImp implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    @Override
    public List<MedicationResponse> getAllMedication() {
        return medicationRepository.findAll()
                .stream()
                .map(MedicationMapper::entityToDto)
                .toList();
    }

    @Override
    public MedicationResponse createMedication(MedicationRequest medicationRequest) {
        Medication medication = MedicationMapper.dtoToEntity(medicationRequest);
        Medication saved = medicationRepository.save(medication);
        return MedicationMapper.entityToDto(saved);
    }

    @Override
    public MedicationResponse updateMedication(Long id, MedicationRequest medicationRequest){
        Medication isExisting = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No" + id));
        isExisting.setTaken(medicationRequest.taken());
        Medication savedMedication = medicationRepository.save(isExisting);
        return MedicationMapper.entityToDto(savedMedication);
    }
}

