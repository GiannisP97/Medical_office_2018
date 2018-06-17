/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEntities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "Restocks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restocks.findAll", query = "SELECT r FROM Restocks r")
    , @NamedQuery(name = "Restocks.findByRestockId", query = "SELECT r FROM Restocks r WHERE r.restockId = :restockId")
    , @NamedQuery(name = "Restocks.findByFileName", query = "SELECT r FROM Restocks r WHERE r.fileName = :fileName")})
public class Restocks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restock_id")
    private Integer restockId;
    @Column(name = "file_name")
    private String fileName;
    @JoinColumn(name = "medicaluser_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private MediclaUsers medicaluserId;

    public Restocks() {
    }

    public Restocks(Integer restockId) {
        this.restockId = restockId;
    }

    public Integer getRestockId() {
        return restockId;
    }

    public void setRestockId(Integer restockId) {
        this.restockId = restockId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MediclaUsers getMedicaluserId() {
        return medicaluserId;
    }

    public void setMedicaluserId(MediclaUsers medicaluserId) {
        this.medicaluserId = medicaluserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restockId != null ? restockId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restocks)) {
            return false;
        }
        Restocks other = (Restocks) object;
        if ((this.restockId == null && other.restockId != null) || (this.restockId != null && !this.restockId.equals(other.restockId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBEntities.Restocks[ restockId=" + restockId + " ]";
    }
    
//    public Restock toPatient(){
//        
//        Restock rs = new Restock();
//        
//        
//        return rs;
//    }
    
}
