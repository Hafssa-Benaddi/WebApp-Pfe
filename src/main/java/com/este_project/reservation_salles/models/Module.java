package com.este_project.reservation_salles.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Modules")
public class Module {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "module_id")
  private Integer id;

  @Column(name = "module_code", nullable = false, unique = true)
  private String moduleCode;

  @Column(name = "module_name", nullable = false)
  private String moduleName;

  @Column(name = "hours_per_week", nullable = false, precision = 4, scale = 2)
  private BigDecimal hoursPerWeek;

  @Column(name = "semester", nullable = false)
  private String semester;

  @ManyToOne
  @JoinColumn(name = "filiere_id", nullable = false)
  private Filiere filiere;

  @ManyToOne
  @JoinColumn(name = "professor_id")
  private User professor;

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getModuleCode() {
    return moduleCode;
  }

  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }

  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public BigDecimal getHoursPerWeek() {
    return hoursPerWeek;
  }

  public void setHoursPerWeek(BigDecimal hoursPerWeek) {
    this.hoursPerWeek = hoursPerWeek;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public Filiere getFiliere() {
    return filiere;
  }

  public void setFiliere(Filiere filiere) {
    this.filiere = filiere;
  }

  public User getProfessor() {
    return professor;
  }

  public void setProfessor(User professor) {
    this.professor = professor;
  }

  public String getProfessorName() {
    if (professor != null) {
      return professor.getFirstName() + " " + professor.getLastName();
    }
    return "Non attribué";
  }
}