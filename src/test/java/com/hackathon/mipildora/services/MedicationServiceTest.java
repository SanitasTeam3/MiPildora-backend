package com.hackathon.mipildora.services;

import com.hackathon.mipildora.dtos.MedicationMapper;
import com.hackathon.mipildora.dtos.MedicationRequest;
import com.hackathon.mipildora.dtos.MedicationResponse;
import com.hackathon.mipildora.models.Medication;
import com.hackathon.mipildora.repositories.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MedicationServiceImp Tests")
class MedicationServiceImpTest {

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationMapper medicationMapper;

    @InjectMocks
    private MedicationServiceImp medicationService;

    private Medication medication1;
    private Medication medication2;
    private MedicationRequest medicationRequest;
    private MedicationResponse medicationResponse1;
    private MedicationResponse medicationResponse2;

    @BeforeEach
    void setUp() {

        medication1 = Medication.builder()
                .id(1L)
                .name("Paracetamol")
                .dosage("500mg")
                .frequency(2)
                .time(LocalTime.of(8, 0))
                .initDate(LocalDate.now())
                .taken(false)
                .alert(true)
                .createDate(LocalDateTime.now())
                .description("Para el dolor de cabeza")
                .build();

        medication2 = Medication.builder()
                .id(2L)
                .name("Ibuprofeno")
                .dosage("400mg")
                .frequency(3)
                .time(LocalTime.of(12, 0))
                .initDate(LocalDate.now())
                .taken(false)
                .alert(true)
                .createDate(LocalDateTime.now())
                .description("Antiinflamatorio")
                .build();

        medicationRequest = new MedicationRequest(
                "Aspirina",
                "100mg",
                1,
                LocalTime.of(20, 0),
                LocalDate.now(),
                LocalDateTime.of(LocalDate.of(2025, 10, 1), LocalTime.of(20,0)),
                true,
                false,
                "Para prevenir infartos"
        );

        medicationResponse1 = new MedicationResponse(
                1L,
                "Paracetamol",
                "500mg",
                2,
                LocalTime.of(8, 0),
                LocalDate.now(),
                LocalDateTime.of(LocalDate.of(2025, 10, 1), LocalTime.of(20,0)),
                true,
                false,
                LocalDateTime.now(),
                "Para el dolor de cabeza"
        );

        medicationResponse2 = new MedicationResponse(
                2L,
                "Ibuprofeno",
                "400mg",
                3,
                LocalTime.of(12, 0),
                LocalDate.now(),
                LocalDateTime.of(LocalDate.of(2025, 10, 1), LocalTime.of(20,0)),
                false,
                true,
                LocalDateTime.now(),
                "Antiinflamatorio"
        );
    }

    @Test
    @DisplayName("Should return all medications successfully")
    void getAllMedication_ShouldReturnAllMedications_WhenMedicationsExist() {

        List<Medication> medications = Arrays.asList(medication1, medication2);
        List<MedicationResponse> expectedResponses = Arrays.asList(medicationResponse1, medicationResponse2);

        when(medicationRepository.findAll()).thenReturn(medications);


        try (MockedStatic<MedicationMapper> mockedMapper = mockStatic(MedicationMapper.class)) {
            mockedMapper.when(() -> MedicationMapper.entityToDto(medication1)).thenReturn(medicationResponse1);
            mockedMapper.when(() -> MedicationMapper.entityToDto(medication2)).thenReturn(medicationResponse2);

            List<MedicationResponse> result = medicationService.getAllMedication();

            assertThat(result).isNotNull();
            assertThat(result).hasSize(2);
            assertThat(result).containsExactlyInAnyOrder(medicationResponse1, medicationResponse2);

            verify(medicationRepository).findAll();
            mockedMapper.verify(() -> MedicationMapper.entityToDto(medication1));
            mockedMapper.verify(() -> MedicationMapper.entityToDto(medication2));
        }
    }

    @Test
    @DisplayName("Should return empty list when no medications exist")
    void getAllMedication_ShouldReturnEmptyList_WhenNoMedicationsExist() {

        when(medicationRepository.findAll()).thenReturn(List.of());

        List<MedicationResponse> result = medicationService.getAllMedication();

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();

        verify(medicationRepository).findAll();
    }

    @Test
    @DisplayName("Should create medication successfully")
    void createMedication_ShouldCreateMedication_WhenValidRequestProvided() {

        Medication medicationToSave = Medication.builder()
                .name("Aspirina")
                .dosage("100mg")
                .frequency(1)
                .time(LocalTime.of(20, 0))
                .initDate(LocalDate.now())
                .taken(false)
                .alert(true)
                .description("Para prevenir infartos")
                .build();

        Medication savedMedication = Medication.builder()
                .id(3L)
                .name("Aspirina")
                .dosage("100mg")
                .frequency(1)
                .time(LocalTime.of(20, 0))
                .initDate(LocalDate.now())
                .taken(false)
                .alert(true)
                .createDate(LocalDateTime.now())
                .description("Para prevenir infartos")
                .build();

        MedicationResponse expectedResponse = new MedicationResponse(
                3L,
                "Aspirina",
                "100mg",
                1,
                LocalTime.of(20, 0),
                LocalDate.now(),
                LocalDateTime.of(LocalDate.of(2025, 10, 1), LocalTime.of(20,0)),
                false,
                true,
                LocalDateTime.now(),
                "Para prevenir infartos"
        );

        when(medicationRepository.save(any(Medication.class))).thenReturn(savedMedication);


        try (MockedStatic<MedicationMapper> mockedMapper = mockStatic(MedicationMapper.class)) {
            mockedMapper.when(() -> MedicationMapper.dtoToEntity(medicationRequest)).thenReturn(medicationToSave);
            mockedMapper.when(() -> MedicationMapper.entityToDto(savedMedication)).thenReturn(expectedResponse);


            MedicationResponse result = medicationService.createMedication(medicationRequest);


            assertThat(result).isNotNull();
            assertThat(result.name()).isEqualTo("Aspirina");
            assertThat(result.dosage()).isEqualTo("100mg");
            assertThat(result.frequency()).isEqualTo(1);
            assertThat(result.taken()).isFalse();
            assertThat(result.alert()).isTrue();

            verify(medicationRepository).save(medicationToSave);
            mockedMapper.verify(() -> MedicationMapper.dtoToEntity(medicationRequest));
            mockedMapper.verify(() -> MedicationMapper.entityToDto(savedMedication));
        }
    }

    @Test
    @DisplayName("Should handle repository exception during creation")
    void createMedication_ShouldThrowException_WhenRepositoryFails() {

        Medication medicationToSave = Medication.builder()
                .name("Aspirina")
                .dosage("100mg")
                .build();

        when(medicationRepository.save(any(Medication.class))).thenThrow(new RuntimeException("Database error"));

        try (MockedStatic<MedicationMapper> mockedMapper = mockStatic(MedicationMapper.class)) {
            mockedMapper.when(() -> MedicationMapper.dtoToEntity(medicationRequest)).thenReturn(medicationToSave);

            org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
                medicationService.createMedication(medicationRequest);
            });

            verify(medicationRepository).save(medicationToSave);
            mockedMapper.verify(() -> MedicationMapper.dtoToEntity(medicationRequest));
        }
    }

    @Test
    @DisplayName("Should handle null request gracefully")
    void createMedication_ShouldHandleNullRequest_WhenNullProvided() {

        MedicationRequest nullRequest = null;

        try (MockedStatic<MedicationMapper> mockedMapper = mockStatic(MedicationMapper.class)) {
            mockedMapper.when(() -> MedicationMapper.dtoToEntity(nullRequest))
                    .thenThrow(new IllegalArgumentException("Request cannot be null"));

            org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
                medicationService.createMedication(nullRequest);
            });

            verify(medicationRepository, never()).save(any(Medication.class));
        }
    }

    @Test
    @DisplayName("Should verify correct method calls and interactions")
    void getAllMedication_ShouldVerifyCorrectMethodCalls() {

        List<Medication> medications = Arrays.asList(medication1);
        when(medicationRepository.findAll()).thenReturn(medications);

        try (MockedStatic<MedicationMapper> mockedMapper = mockStatic(MedicationMapper.class)) {
            mockedMapper.when(() -> MedicationMapper.entityToDto(any(Medication.class))).thenReturn(medicationResponse1);

            medicationService.getAllMedication();

            verify(medicationRepository, times(1)).findAll();
            mockedMapper.verify(() -> MedicationMapper.entityToDto(medication1), times(1));

            verifyNoMoreInteractions(medicationRepository);
        }
    }

    @Test
    @DisplayName("Should test medication mapping in isolation")
    void createMedication_ShouldTestMappingBehavior() {

        Medication medicationToSave = Medication.builder()
                .name("Test Medicine")
                .build();

        Medication savedMedication = Medication.builder()
                .id(999L)
                .name("Test Medicine")
                .build();

        MedicationResponse expectedResponse = new MedicationResponse(
                999L,
                "Test Medicine",
                null,
                null,
                null,
                null,
                null,
                true,
                null,
                null,
                null
        );

        when(medicationRepository.save(any(Medication.class))).thenReturn(savedMedication);

        try (MockedStatic<MedicationMapper> mockedMapper = mockStatic(MedicationMapper.class)) {
            mockedMapper.when(() -> MedicationMapper.dtoToEntity(medicationRequest)).thenReturn(medicationToSave);
            mockedMapper.when(() -> MedicationMapper.entityToDto(savedMedication)).thenReturn(expectedResponse);

            MedicationResponse result = medicationService.createMedication(medicationRequest);

            assertThat(result).isEqualTo(expectedResponse);

            mockedMapper.verify(() -> MedicationMapper.dtoToEntity(medicationRequest));
            mockedMapper.verify(() -> MedicationMapper.entityToDto(savedMedication));
        }
    }
}