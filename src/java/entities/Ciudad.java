package entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase Ciudad. Representa una ciudad.
 *
 * @author andrea
 */
@Entity
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c")
    , @NamedQuery(name = "Ciudad.findByIdciudad", query = "SELECT c FROM Ciudad c WHERE c.idciudad = :idciudad")
    , @NamedQuery(name = "Ciudad.findByNombre", query = "SELECT c FROM Ciudad c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Ciudad.findByActivo", query = "SELECT c FROM Ciudad c WHERE c.activo = :activo")})
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idciudad")
    private Integer idciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadIdciudad")
    private Collection<Area> areaCollection;

    /**
     * Constructor de la clase.
     */
    public Ciudad() {
    }

    /**
     * Constructor de la clase. Recibe un identificador de la ciudad.
     *
     * @param idciudad Entero
     */
    public Ciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    /**
     * Constructor de la clase. Recibe todos los atributos de una ciudad.
     *
     * @param idciudad Entero.
     * @param nombre Cadena de Texto.
     * @param activo Boolean.
     */
    public Ciudad(Integer idciudad, String nombre, short activo) {
        this.idciudad = idciudad;
        this.nombre = nombre;
        this.activo = activo;
    }

    // Getters, Setters y demas metodos utiles de la clase.

    /**
     *
     * @return
     */
    public Integer getIdciudad() {
        return idciudad;
    }

    /**
     *
     * @param idciudad
     */
    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
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
    public short getActivo() {
        return activo;
    }

    /**
     *
     * @param activo
     */
    public void setActivo(short activo) {
        this.activo = activo;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    /**
     *
     * @param areaCollection
     */
    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idciudad != null ? idciudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        return !((this.idciudad == null && other.idciudad != null) 
                || (this.idciudad != null 
                && !this.idciudad.equals(other.idciudad)));
    }

    @Override
    public String toString() {
        return "entities.Ciudad[ idciudad=" + idciudad + " ]";
    }

}
