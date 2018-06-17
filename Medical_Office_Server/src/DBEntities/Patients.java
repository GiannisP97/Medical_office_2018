/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEntities;

import SoftwareEngineering.Patient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "Patients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patients.findAll", query = "SELECT p FROM Patients p")
    , @NamedQuery(name = "Patients.findByAmka", query = "SELECT p FROM Patients p WHERE p.amka = :amka")
    , @NamedQuery(name = "Patients.findByName", query = "SELECT p FROM Patients p WHERE p.name = :name")
    , @NamedQuery(name = "Patients.findByContactNumber", query = "SELECT p FROM Patients p WHERE p.contactNumber = :contactNumber")
    , @NamedQuery(name = "Patients.findByBirthDate", query = "SELECT p FROM Patients p WHERE p.birthDate = :birthDate")
    , @NamedQuery(name = "Patients.findBySex", query = "SELECT p FROM Patients p WHERE p.sex = :sex")})
public class Patients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AMKA")
    private Integer amka;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "sex")
    private String sex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientAMKA")
    private List<Appointments> appointmentsList;

    public Patients() {
    }

    public Patients(Integer amka) {
        this.amka = amka;
    }

    public Patients(Integer amka, String name, String sex) {
        this.amka = amka;
        this.name = name;
        this.sex = sex;
    }

    public Integer getAmka() {
        return amka;
    }

    public void setAmka(Integer amka) {
        this.amka = amka;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @XmlTransient
    public List<Appointments> getAppointmentsList() {
        return appointmentsList;
    }

    public void setAppointmentsList(List<Appointments> appointmentsList) {
        this.appointmentsList = appointmentsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amka != null ? amka.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patients)) {
            return false;
        }
        Patients other = (Patients) object;
        if ((this.amka == null && other.amka != null) || (this.amka != null && !this.amka.equals(other.amka))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBEntities.Patients[ amka=" + amka + " ]";
    }
    
    public Patient toPatient(){
        
        Patient pt = new Patient();
        pt.setAMKA(this.amka);
        pt.setBirth(convertToLocalDateViaInstant(this.birthDate));
        if(sex.equals("MALE")) pt.setGender(new Integer(1).shortValue());
        else pt.setGender(new Integer(0).shortValue());
//        pt.setGender(this.sex);
        pt.setName(this.name);
        pt.setPhoneNumber(this.contactNumber);
        pt.setSurname(this.name);
        return pt;
    }
    
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
}
