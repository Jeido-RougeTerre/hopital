package com.jeido.hospital.repositories;

import com.jeido.hospital.entities.Patient;

import java.util.List;

public class PatientRepository extends BaseRepository<Patient> {
    public PatientRepository() {
        super(Patient.class);
    }

    public List<Patient> findByName(String name) {
        session = sessionFactory.openSession();
        List<Patient> patient = session.createQuery("from Patient where name like :name", Patient.class).setParameter("name", "%" + name + "%").list();
        session.close();
        return patient;
    }
}
