package com.luxoft.producer.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Entity
@Table(name = "configuration")
public class Configuration {

    @Id
    private Integer id;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "property_value")
    private String propertyValue;

    private String description;

    private String environment;

    @Column(name = "date_added")
    private Date dateAdded;

    public Configuration() {}

    public Configuration(ResultSet rs) throws SQLException {
        setId(rs.getInt("id"));
        setPropertyName(rs.getString("property_name"));
        setPropertyValue(rs.getString("property_value"));
        setDescription(rs.getString("description"));
        setEnvironment(rs.getString("environment"));
        setDateAdded(rs.getDate("date_added"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
