package com.este_project.reservation_salles.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salle_id")
    private Integer id;

    @Column(name = "numero_salle", nullable = false, unique = true)
    private String numeroSalle;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "type", nullable = false)
    private String type;

    // Constructeurs
    public Salle() {
    }

    public Salle(String numeroSalle, int capacity, String type) {
        this.numeroSalle = numeroSalle;
        this.capacity = capacity;
        this.type = type;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(String numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
