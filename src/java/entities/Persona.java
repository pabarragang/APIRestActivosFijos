package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase Persona. Representa una persona.
 *
 * @author andrea
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByIdpersona", query = "SELECT p FROM Persona p WHERE p.idpersona = :idpersona")
    , @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Persona.findByIdentificacion", query = "SELECT p FROM Persona p WHERE p.identificacion = :identificacion")
    , @NamedQuery(name = "Persona.findByActivo", query = "SELECT p FROM Persona p WHERE p.activo = :activo")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersona")
    private Integer idpersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "activo")
    private Short activo;
    @OneToMany(mappedBy = "personaIdpersona")
    private Collection<ActivoFijo> activoFijoCollection;

    /**
     * Constructor de la clase.
     */
    public Persona() {
    }

    /**
     * Constructor de la clase. Recibe un identificador de la persona.
     *
     * @param idpersona Entero.
     */
    public Persona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    /**
     * Constructor de la clase. Recibe todos los atributos de una persona.
     *
     * @param idpersona Entero.
     * @param nombre Cadena de Texto.
     * @param identificacion Cadena de Texto.
     */
    public Persona(Integer idpersona, String nombre, String identificacion) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    // Getters, Setters y demas metodos utiles de la clase.

    /**
     *
     * @return
     */
    public Integer getIdpersona() {
        return idpersona;
    }

    /**
     *
     * @param idpersona
     */
    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     *
     * @param identificacion
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     *
     * @return
     */
    public Short getActivo() {
        return activo;
    }

    /**
     *
     * @param activo
     */
    public void setActivo(Short activo) {
        this.activo = activo;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<ActivoFijo> getActivoFijoCollection() {
        return activoFijoCollection;
    }

    /**
     *
     * @param activoFijoCollection
     */
    public void setActivoFijoCollection(Collection<ActivoFijo> activoFijoCollection) {
        this.activoFijoCollection = activoFijoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        return !((this.idpersona == null && other.idpersona != null) 
                || (this.idpersona != null 
                && !this.idpersona.equals(other.idpersona)));
    }

    @Override
    public String toString() {
        return "entities.Persona[ idpersona=" + idpersona + " ]";
    }

}
