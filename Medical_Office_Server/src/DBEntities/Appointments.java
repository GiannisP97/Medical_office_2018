/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEntities;

import SoftwareEngineering.Appointment;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    , @NamedQuery(name = "Appointments.findByAppointmentDay", query = "SELECT a FROM Appointments a WHERE a.appointmentDay = :appointmentDay")
    , @NamedQuery(name = "Appointments.findByDoctorPrescription", query = "SELECT a FROM Appointments a WHERE a.doctorPrescription = :doctorPrescription")})
public class Appointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "appointment_id")
    private Integer appointmentId;
    @Column(name = "appointment_day")
    @Temporal(TemporalType.DATE)
    private Date appointmentDay;
    @Column(name = "doctor_prescription")
    private String doctorPrescription;
    @JoinColumn(name = "Patient_AMKA", referencedColumnName = "AMKA")
    @ManyToOne(optional = false)
    private Patients patientAMKA;
    @JoinColumn(name = "mediclauser_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private MediclaUsers mediclauserId;

    public Appointments() {
    }

    public Appointments(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
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

    public Patients getPatientAMKA() {
        return patientAMKA;
    }

    public void setPatientAMKA(Patients patientAMKA) {
        this.patientAMKA = patientAMKA;
    }

    public MediclaUsers getMediclauserId() {
        return mediclauserId;
    }

    public void setMediclauserId(MediclaUsers mediclauserId) {
        this.mediclauserId = mediclauserId;
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
        return "DBEntities.Appointments[ appointmentId=" + appointmentId + " ]";
    }
    
    public Appointment toAppointment(){
        
        Appointment ap = new Appointment();
        ap.setID(this.appointmentId);
        ap.setPatient(this.patientAMKA.toPatient());
        ap.setPrescription(this.doctorPrescription);
        ap.setDoctorID(this.mediclauserId.getUserId());
        ap.setDate(this.convertToLocalDateTimeViaInstant(appointmentDay));
        
        return ap;
    }
    
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
