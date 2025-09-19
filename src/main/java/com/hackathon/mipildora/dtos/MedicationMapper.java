package com.hackathon.mipildora.dtos;

import com.hackathon.mipildora.models.Medication;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {
    public static Medication dtoToEntity(MedicationRequest request) {
        if (request == null) return null;

        return Medication.builder()
                .name(request.name())
                .dosage(request.dosage())
                .frequency(request.frequency())
                .time(request.time())
                .initDate(request.initDate())
                .finalDate(request.finalDate())
                .taken(request.taken() != null ? request.taken() : false)
                .alert(request.alert() != null ? request.alert() : true)
                .description(request.description())
                .build();
    }

    public static MedicationResponse entityToDto(Medication medication) {
        if (medication == null) return null;

        return new MedicationResponse(
                medication.getId(),
                medication.getName(),
                medication.getDosage(),
                medication.getFrequency(),
                medication.getTime(),
                medication.getInitDate(),
                medication.getFinalDate(),
                medication.getTaken(),
                medication.getAlert(),
                medication.getCreateDate(),
                medication.getDescription()
        );
    }
}
