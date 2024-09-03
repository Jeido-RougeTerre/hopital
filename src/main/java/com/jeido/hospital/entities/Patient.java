package com.jeido.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;

    private String name;
    private String phone;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;


    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
    }

}
