package com.este_project.reservation_salles.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Filieres")
public class Filiere {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int filiere_id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false, unique = true)
  private String code_abrev;

  private LocalDateTime created_at;

  @Column(nullable = false)
  private String niveau;

  @ManyToOne
  @JoinColumn(name = "responsable_prof_id", referencedColumnName = "id", nullable = true)
  private User responsable;

  // Association bidirectionnelle avec les modules
  @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Module> modules;

  public Filiere() {
    this.created_at = LocalDateTime.now();
    this.niveau = "DUT";
  }

  // Getters et Setters
  public int getFiliere_id() {
    return filiere_id;
  }

  public void setFiliere_id(int filiere_id) {
    this.filiere_id = filiere_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode_abrev() {
    return code_abrev;
  }

  public void setCode_abrev(String code_abrev) {
    this.code_abrev = code_abrev;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public String getNiveau() {
    return niveau;
  }

  public void setNiveau(String niveau) {
    this.niveau = niveau;
  }

  public User getResponsable() {
    return responsable;
  }

  public void setResponsable(User responsable) {
    this.responsable = responsable;
  }

  public String getResponsableName() {
    if (responsable != null) {
      return "Pr. " + responsable.getFirstName() + " " + responsable.getLastName();
    }
    return "Aucun";
  }

  public List<Module> getModules() {
    return modules;
  }

  public void setModules(List<Module> modules) {
    this.modules = modules;
  }

  @Override
  public String toString() {
    return "Filiere{" +
        "name='" + name + '\'' +
        ", code_abrev='" + code_abrev + '\'' +
        ", created_at=" + created_at +
        ", niveau='" + niveau + '\'' +
        ", responsable=" + getResponsableName() +
        '}';
  }
}
