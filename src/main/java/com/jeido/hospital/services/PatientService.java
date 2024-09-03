package com.jeido.hospital.services;

import com.jeido.hospital.entities.Patient;
import com.jeido.hospital.repositories.PatientRepository;
import com.jeido.hospital.utils.exception.PatientNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class PatientService {
    private final PatientRepository repo;

    public PatientService() {
        this.repo = new PatientRepository();
    }

    public Patient create(String name, String phone, LocalDate birthDate) {
        Patient p = Patient.builder()
                .name(name).phone(phone).birthDate(birthDate)
                .build();

        return repo.createOrUpdate(p);
    }

    public Patient create() {
        Patient p = Patient.builder()
                .name("")
                .phone("")
                .birthDate(LocalDate.now())
                .build();
        return repo.createOrUpdate(p);
    }

    public Patient update(int id, String name, String phone, LocalDate birthDate){

        Patient p = repo.findById(id);

        if (p == null) throw new PatientNotFoundException(id);

        p.setName(name);
        p.setPhone(phone);
        p.setBirthDate(birthDate);
        return repo.createOrUpdate(p);

    }

    public boolean delete (int id) {
        Patient p = repo.findById(id);
        if (p == null) throw new PatientNotFoundException(id);
        return repo.delete(p);

    }

    public Patient findById (int id) {
        Patient p = repo.findById(id);
        if(p == null) throw new PatientNotFoundException(id);
        return p;
    }

    public List<Patient> findByName (String name) {
        return repo.findByName(name);//Don't send Exception
    }

    public List<Patient> findAll () {
        return repo.findAll();
    }
}
