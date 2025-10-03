package com.thymeleaf.crud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "employee",
        indexes = @Index(name = "datebirth_indx", columnList = "datebirth"),
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id", "datebirth"})
        })
public class Employee

{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname",length = 50)
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "First name must contain only letters and spaces")
    private String firstName;

    @Column(name = "lastname", length = 50)
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Last name must contain only letters and spaces")
    private String lastName;

    @Column(name = "age")
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", length = 1)
    private Sex sex = Sex.N;

    @Column(name = "datebirth")
    @NotNull(message = "Date of birth is required")
    private LocalDate dateBirth;

    @Column(name = "jobstatus")
    private Boolean jobStatus = false;

    @Column(name = "levelofeducation", length = 50)
    @Size(max = 50)
    private String levelOfEducation = "N/A";

    @Column(name = "salary")
    @Min(value = 0, message = "salary must be at least 0 or 1000.0 and greater")
    private Double salary = 0.0;

    @Transient
    private Double netSalary;

    public enum Sex
    {
        M, F, N
    }

    @PrePersist
    @PreUpdate
    private void validateAndFormat()
    {

        if (firstName.matches("^[A-Za-z ]+$")) {
            firstName = capitalize(firstName);
        }
        if (lastName.matches("^[A-Za-z ]+$")) {
            lastName = capitalize(lastName);
        }

        if (dateBirth != null && age != null)
        {
            int calculatedAge = Period.between(dateBirth, LocalDate.now()).getYears();

            if (!age.equals(calculatedAge))
            {
                dateBirth = LocalDate.now().minusYears(age);
            }
        }

        switch (levelOfEducation)
        {
            case "4" -> levelOfEducation = "Lyceum";
            case "5" -> levelOfEducation = "Institute of vocational training";
            case "6" -> levelOfEducation = "Bachelor's degree";
            case "7" -> levelOfEducation = "Master's degree";
            case "8" -> levelOfEducation = "PhD";
            case "N/A" -> levelOfEducation = "No education";
        }

        if (salary >= 1000.0)
        {
            jobStatus = true;
        }
        else if (salary == 0.0)
        {
            jobStatus = false;
        }
        else if (salary > 0.0 && salary < 1000.0)
        {
            salary = 1000.0;
            jobStatus = true;
        }

    }

    private String capitalize(String input)
    {
        input = input.trim();

        if (input.length() < 1)
        {
            return input;
        }
        else
        {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Boolean getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Boolean jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getNetSalary() {
        return netSalary = salary - (salary * 0.257);
    }

    @Override
    public String toString() {
        return "Information{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", dateBirth=" + dateBirth +
                ", jobStatus=" + jobStatus +
                ", levelOfEducation='" + levelOfEducation + '\'' +
                ", netsSalary=" + getNetSalary() +
                '}';
    }
}


