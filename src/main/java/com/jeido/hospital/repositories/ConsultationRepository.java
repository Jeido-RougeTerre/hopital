package com.jeido.hospital.repositories;

import com.jeido.hospital.entities.Consultation;

import java.util.List;

public class ConsultationRepository extends BaseRepository<Consultation> {
    public ConsultationRepository() {
        super(Consultation.class);
    }

    public List<Consultation> findByPatient(int patientId) {
        session = sessionFactory.openSession();
        List<Consultation> consutations = session.createQuery("from Consultation where patient = :patient_id", Consultation.class).setParameter("patient_id", patientId).list();
        session.close();
        return consutations;
    }
}
