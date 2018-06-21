/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "MediclaUsers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MediclaUsers.findAll", query = "SELECT m FROM MediclaUsers m")
    , @NamedQuery(name = "MediclaUsers.findByUserId", query = "SELECT m FROM MediclaUsers m WHERE m.userId = :userId")
    , @NamedQuery(name = "MediclaUsers.findByName", query = "SELECT m FROM MediclaUsers m WHERE m.name = :name")
    , @NamedQuery(name = "MediclaUsers.findByPassword", query = "SELECT m FROM MediclaUsers m WHERE m.password = :password")
    , @NamedQuery(name = "MediclaUsers.findByUserType", query = "SELECT m FROM MediclaUsers m WHERE m.userType = :userType")
    , @NamedQuery(name = "MediclaUsers.findByAfm", query = "SELECT m FROM MediclaUsers m WHERE m.afm = :afm")})
public class MediclaUsers implements Serializable {

    @Column(name = "username")
    private String username;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "user_type")
    private short userType;
    @Basic(optional = false)
    @Column(name = "AFM")
    private int afm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mediclauserId")
    private List<Appointments> appointmentsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicaluserId")
    private List<Restocks> restocksList;

    public MediclaUsers() {
    }

    public MediclaUsers(Integer userId) {
        this.userId = userId;
    }

    public MediclaUsers(Integer userId, String name, String password, short userType, int afm) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.afm = afm;
    }
    
    public MediclaUsers(String name, String password, short userType, int afm) {
        this.userId = null;
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.afm = afm;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getUserType() {
        return userType;
    }

    public void setUserType(short userType) {
        this.userType = userType;
    }

    public int getAfm() {
        return afm;
    }

    public void setAfm(int afm) {
        this.afm = afm;
    }

    @XmlTransient
    public List<Appointments> getAppointmentsList() {
        return appointmentsList;
    }

    public void setAppointmentsList(List<Appointments> appointmentsList) {
        this.appointmentsList = appointmentsList;
    }

    @XmlTransient
    public List<Restocks> getRestocksList() {
        return restocksList;
    }

    public void setRestocksList(List<Restocks> restocksList) {
        this.restocksList = restocksList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediclaUsers)) {
            return false;
        }
        MediclaUsers other = (MediclaUsers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBEntities.MediclaUsers[ userId=" + userId + " ]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
