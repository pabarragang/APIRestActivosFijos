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
 * Clase Estado. Representa los estados de un activo fijo.
 *
 * @author andrea
 */
@Entity
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")
    , @NamedQuery(name = "Estado.findByIdestado", query = "SELECT e FROM Estado e WHERE e.idestado = :idestado")
    , @NamedQuery(name = "Estado.findByNombre", query = "SELECT e FROM Estado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estado.findByActivo", query = "SELECT e FROM Estado e WHERE e.activo = :activo")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado")
    private Integer idestado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdestado")
    private Collection<ActivoFijo> activoFijoCollection;

    /**
     * Constructor de la clase.
     */
    public Estado() {
    }

    /**
     * Constructor de la clase.
     *
     * @param idestado Entero.
     */
    public Estado(Integer idestado) {
        this.idestado = idestado;
    }

    /**
     * Constructor de la clase.
     *
     * @param idestado Entero.
     * @param nombre Cadena de Texto.
     * @param activo Boolean.
     */
    public Estado(Integer idestado, String nombre, short activo) {
        this.idestado = idestado;
        this.nombre = nombre;
        this.activo = activo;
    }

    // Getters, Setters y demas metodos utiles de la clase.

    /**
     *
     * @return
     */
    public Integer getIdestado() {
        return idestado;
    }

    /**
     *
     * @param idestado
     */
    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
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
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        return !((this.idestado == null && other.idestado != null) 
                || (this.idestado != null 
                && !this.idestado.equals(other.idestado)));
    }

    @Override
    public String toString() {
        return "entities.Estado[ idestado=" + idestado + " ]";
    }

}
