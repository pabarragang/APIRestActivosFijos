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
 * Clase Tipo. Representa un tipo de Activo Fijo.
 *
 * @author andrea
 */
@Entity
@Table(name = "tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t")
    , @NamedQuery(name = "Tipo.findByIdtipo", query = "SELECT t FROM Tipo t WHERE t.idtipo = :idtipo")
    , @NamedQuery(name = "Tipo.findByNombre", query = "SELECT t FROM Tipo t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tipo.findByActivo", query = "SELECT t FROM Tipo t WHERE t.activo = :activo")})
public class Tipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo")
    private Integer idtipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIdtipo")
    private Collection<ActivoFijo> activoFijoCollection;

    /**
     * Constructor de la clase vacio.
     */
    public Tipo() {
    }

    /**
     * Constructor de la clase. Recibe un identificador del tipo.
     *
     * @param idtipo Entero.
     */
    public Tipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    /**
     * Constructor de la clase. Recibe todos los atributos de la clase.
     *
     * @param idtipo Entero.
     * @param nombre Cadena de texto.
     * @param activo Boolean.
     */
    public Tipo(Integer idtipo, String nombre, short activo) {
        this.idtipo = idtipo;
        this.nombre = nombre;
        this.activo = activo;
    }

    // Getters, Setters y demas metodos utiles de la clase.
    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<ActivoFijo> getActivoFijoCollection() {
        return activoFijoCollection;
    }

    public void setActivoFijoCollection(Collection<ActivoFijo> activoFijoCollection) {
        this.activoFijoCollection = activoFijoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipo != null ? idtipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipo)) {
            return false;
        }
        Tipo other = (Tipo) object;
        return !((this.idtipo == null && other.idtipo != null) || (this.idtipo != null && !this.idtipo.equals(other.idtipo)));
    }

    @Override
    public String toString() {
        return "entities.Tipo[ idtipo=" + idtipo + " ]";
    }

}
