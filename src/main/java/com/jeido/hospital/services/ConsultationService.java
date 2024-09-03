package com.jeido.hospital.services;

import com.jeido.hospital.entities.Consultation;
import com.jeido.hospital.entities.Patient;
import com.jeido.hospital.repositories.ConsultationRepository;
import com.jeido.hospital.utils.exception.ConsultationNotFoundException;
import com.jeido.hospital.utils.exception.PatientNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class ConsultationService {
    private final ConsultationRepository repo;

    public ConsultationService() {
        this.repo = new ConsultationRepository();
    }

    public Consultation create(LocalDate date, String prescription, String healthSheet) {
        Consultation c = Consultation.builder()
                .date(date)
                .prescription(prescription)
                .healthSheet(healthSheet)
                .build();

        return repo.createOrUpdate(c);
    }

    public Consultation create() {
        Consultation c = Consultation.builder()
                .healthSheet("")
                .prescription("")
                .date(LocalDate.now())
                .build();

        return repo.createOrUpdate(c);
    }

    public Consultation update(int id, LocalDate date, String prescription, String healthSheet) {

        Consultation c = repo.findById(id);

        if (c == null) throw new ConsultationNotFoundException(id);
        c.setDate(date);
        c.setPrescription(prescription);
        c.setHealthSheet(healthSheet);

        return repo.createOrUpdate(c);
    }

    public boolean delete (int id) {
        Consultation c = repo.findById(id);
        if (c == null) throw new ConsultationNotFoundException(id);
        return repo.delete(c);

    }

    public Consultation findById (int id) {
        Consultation c = repo.findById(id);
        if(c == null) throw new ConsultationNotFoundException(id);
        return c;
    }

    public List<Consultation> findByPatient(Patient patient) {
        PatientService ps = new PatientService();
        Patient p = ps.findById(patient.getId());
        if(p == null) throw new PatientNotFoundException(patient.getId());
        return repo.findByPatient(patient.getId());
    }

    public List<Consultation> findAll () {
        return repo.findAll();
    }
}
