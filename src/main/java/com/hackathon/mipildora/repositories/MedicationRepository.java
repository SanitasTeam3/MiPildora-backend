package com.hackathon.mipildora.repositories;
import com.hackathon.mipildora.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long>{
}
