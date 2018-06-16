/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "Appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointments.findAll", query = "SELECT a FROM Appointments a")
    , @NamedQuery(name = "Appointments.findByAppointmentId", query = "SELECT a FROM Appointments a WHERE a.appointmentId = :appointmentId")
    , @NamedQuery(name = "Appointments.findByPatientAMKA", query = "SELECT a FROM Appointments a WHERE a.patientAMKA = :patientAMKA")
    , @NamedQuery(name = "Appointments.findByMediclauserId", query = "SELECT a FROM Appointments a WHERE a.mediclauserId = :mediclauserId")
    , @NamedQuery(name = "Appointments.findByAppointmentDay", query = "SELECT a FROM Appointments a WHERE a.appointmentDay = :appointmentDay")
    , @NamedQuery(name = "Appointments.findByDoctorPrescription", query = "SELECT a FROM Appointments a WHERE a.doctorPrescription = :doctorPrescription")})
public class Appointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "appointment_id")
    private Integer appointmentId;
    @Basic(optional = false)
    @Column(name = "Patient_AMKA")
    private int patientAMKA;
    @Basic(optional = false)
    @Column(name = "mediclauser_id")
    private int mediclauserId;
    @Column(name = "appointment_day")
    @Temporal(TemporalType.DATE)
    private Date appointmentDay;
    @Column(name = "doctor_prescription")
    private String doctorPrescription;

    public Appointments() {
        
        appointmentId = 0;
        patientAMKA = 0;
        mediclauserId = 0 ;
        appointmentDay = new Date(0);
        doctorPrescription = "";
    }

    public Appointments(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Appointments(Integer appointmentId, int patientAMKA, int mediclauserId) {
        this.appointmentId = appointmentId;
        this.patientAMKA = patientAMKA;
        this.mediclauserId = mediclauserId;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientAMKA() {
        return patientAMKA;
    }

    public void setPatientAMKA(int patientAMKA) {
        this.patientAMKA = patientAMKA;
    }

    public int getMediclauserId() {
        return mediclauserId;
    }

    public void setMediclauserId(int mediclauserId) {
        this.mediclauserId = mediclauserId;
    }

    public Date getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(Date appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getDoctorPrescription() {
        return doctorPrescription;
    }

    public void setDoctorPrescription(String doctorPrescription) {
        this.doctorPrescription = doctorPrescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentId != null ? appointmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointments)) {
            return false;
        }
        Appointments other = (Appointments) object;
        if ((this.appointmentId == null && other.appointmentId != null) || (this.appointmentId != null && !this.appointmentId.equals(other.appointmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SoftwareEngineering.Appointments[ appointmentId=" + appointmentId + " ]";
    }
    
}
