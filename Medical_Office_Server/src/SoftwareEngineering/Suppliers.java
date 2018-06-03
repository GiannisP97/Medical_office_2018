/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "Suppliers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suppliers.findAll", query = "SELECT s FROM Suppliers s")
    , @NamedQuery(name = "Suppliers.findBySupplierId", query = "SELECT s FROM Suppliers s WHERE s.supplierId = :supplierId")
    , @NamedQuery(name = "Suppliers.findBySupplierName", query = "SELECT s FROM Suppliers s WHERE s.supplierName = :supplierName")
    , @NamedQuery(name = "Suppliers.findByContactNumber", query = "SELECT s FROM Suppliers s WHERE s.contactNumber = :contactNumber")
    , @NamedQuery(name = "Suppliers.findByContactMail", query = "SELECT s FROM Suppliers s WHERE s.contactMail = :contactMail")})
public class Suppliers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "supplier_id")
    private Integer supplierId;
    @Basic(optional = false)
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "contact_mail")
    private String contactMail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierId")
    private Collection<Restocks> restocksCollection;

    public Suppliers() {
    }

    public Suppliers(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Suppliers(Integer supplierId, String supplierName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    @XmlTransient
    public Collection<Restocks> getRestocksCollection() {
        return restocksCollection;
    }

    public void setRestocksCollection(Collection<Restocks> restocksCollection) {
        this.restocksCollection = restocksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suppliers)) {
            return false;
        }
        Suppliers other = (Suppliers) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SoftwareEngineering.Suppliers[ supplierId=" + supplierId + " ]";
    }
    
}
