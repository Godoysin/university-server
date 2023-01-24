package com.luxoft.producer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "career")
public class Career {

    @Id
    private String careerName;
    private Date creationDate;

    private Career() {
    }

    public Career(String careerName) {
        setCareerName(careerName);
        setCreationDate(new Date());
    }

    public Career(String careerName, Date creationDate) {
        setCareerName(careerName);
        setCreationDate(creationDate);
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
