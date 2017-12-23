package entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase Area. Representa una area de un activo fijo.
 *
 * @author andrea
 */
@Entity
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
    , @NamedQuery(name = "Area.findByIdarea", query = "SELECT a FROM Area a WHERE a.idarea = :idarea")
    , @NamedQuery(name = "Area.findByNombre", query = "SELECT a FROM Area a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Area.findByActivo", query = "SELECT a FROM Area a WHERE a.activo = :activo")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarea")
    private Integer idarea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @JoinColumn(name = "ciudad_idciudad", referencedColumnName = "idciudad")
    @ManyToOne(optional = false)
    private Ciudad ciudadIdciudad;
    @OneToMany(mappedBy = "areaIdarea")
    private Collection<ActivoFijo> activoFijoCollection;

    /**
     * Constructor de la clase vacio.
     */
    public Area() {
    }

    /**
     * Constructor de la clase. Recibe un identificador del area.
     *
     * @param idarea Entero.
     */
    public Area(Integer idarea) {
        this.idarea = idarea;
    }

    /**
     * Constructor de la clase. Recibe todos los atributos de un Area.
     *
     * @param idarea Entero.
     * @param nombre Cadena de Texto.
     * @param activo Boolean.
     */
    public Area(Integer idarea, String nombre, short activo) {
        this.idarea = idarea;
        this.nombre = nombre;
        this.activo = activo;
    }

    // Getters, Setters y demas metodos utiles de la clase.
    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
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

    public Ciudad getCiudadIdciudad() {
        return ciudadIdciudad;
    }

    public void setCiudadIdciudad(Ciudad ciudadIdciudad) {
        this.ciudadIdciudad = ciudadIdciudad;
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
        hash += (idarea != null ? idarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        return !((this.idarea == null && other.idarea != null) 
                || (this.idarea != null && !this.idarea.equals(other.idarea)));
    }

    @Override
    public String toString() {
        return "entities.Area[ idarea=" + idarea + " ]";
    }

}
