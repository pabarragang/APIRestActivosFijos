package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase Activo Fijo. 
 * Representa un activo fijo.
 * @author andrea
 */
@Entity
@Table(name = "activo_fijo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoFijo.findAll", query = "SELECT a FROM ActivoFijo a")
    , @NamedQuery(name = "ActivoFijo.findByIdactivoFijo", query = "SELECT a FROM ActivoFijo a WHERE a.idactivoFijo = :idactivoFijo")
    , @NamedQuery(name = "ActivoFijo.findByNombre", query = "SELECT a FROM ActivoFijo a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "ActivoFijo.findByDescripcion", query = "SELECT a FROM ActivoFijo a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "ActivoFijo.findBySerial", query = "SELECT a FROM ActivoFijo a WHERE a.serial = :serial")
    , @NamedQuery(name = "ActivoFijo.findByNumInventario", query = "SELECT a FROM ActivoFijo a WHERE a.numInventario = :numInventario")
    , @NamedQuery(name = "ActivoFijo.findByPeso", query = "SELECT a FROM ActivoFijo a WHERE a.peso = :peso")
    , @NamedQuery(name = "ActivoFijo.findByAlto", query = "SELECT a FROM ActivoFijo a WHERE a.alto = :alto")
    , @NamedQuery(name = "ActivoFijo.findByTipo", query = "SELECT a FROM ActivoFijo a WHERE a.tipoIdtipo = :tipo")
    , @NamedQuery(name = "ActivoFijo.findByAncho", query = "SELECT a FROM ActivoFijo a WHERE a.ancho = :ancho")
    , @NamedQuery(name = "ActivoFijo.findByLargo", query = "SELECT a FROM ActivoFijo a WHERE a.largo = :largo")
    , @NamedQuery(name = "ActivoFijo.findByValorCompra", query = "SELECT a FROM ActivoFijo a WHERE a.valorCompra = :valorCompra")
    , @NamedQuery(name = "ActivoFijo.findByFechaCompra", query = "SELECT a FROM ActivoFijo a WHERE a.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "ActivoFijo.findByFechaBaja", query = "SELECT a FROM ActivoFijo a WHERE a.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "ActivoFijo.findByColor", query = "SELECT a FROM ActivoFijo a WHERE a.color = :color")})
public class ActivoFijo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactivo_fijo")
    private Integer idactivoFijo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "serial")
    private String serial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "num_inventario")
    private String numInventario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private float peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alto")
    private float alto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ancho")
    private float ancho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "largo")
    private float largo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_compra")
    private float valorCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "color")
    private String color;
    @JoinColumn(name = "area_idarea", referencedColumnName = "idarea")
    @ManyToOne
    private Area areaIdarea;
    @JoinColumn(name = "estado_idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado estadoIdestado;
    @JoinColumn(name = "persona_idpersona", referencedColumnName = "idpersona")
    @ManyToOne
    private Persona personaIdpersona;
    @JoinColumn(name = "tipo_idtipo", referencedColumnName = "idtipo")
    @ManyToOne(optional = false)
    private Tipo tipoIdtipo;

    /**
     * Constructor de la clase vacio.
     */
    public ActivoFijo() {
    }

    /**
     * Constructor de la clase. Recibe un identificador del activo fijo.
     *
     * @param idactivoFijo Entero.
     */
    public ActivoFijo(Integer idactivoFijo) {
        this.idactivoFijo = idactivoFijo;
    }

    /**
     * Constructor de la clase. Recibe todos los atributos del activo fijo.
     *
     * @param idactivoFijo Entero.
     * @param nombre Cadena de Texto.
     * @param descripcion Cadena de Texto.
     * @param serial Cadena de Texto.
     * @param numInventario Cadena de Texto.
     * @param peso Decimal.
     * @param alto Decimal.
     * @param ancho Decimal.
     * @param largo Decimal.
     * @param valorCompra Decimal.
     * @param fechaCompra Fecha.
     * @param fechaBaja Fecha.
     * @param color Cadena de Texto.
     */
    public ActivoFijo(Integer idactivoFijo, String nombre, String descripcion, 
            String serial, String numInventario, float peso, float alto, 
            float ancho, float largo, float valorCompra, Date fechaCompra, 
            Date fechaBaja, String color) {
        this.idactivoFijo = idactivoFijo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.serial = serial;
        this.numInventario = numInventario;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
        this.fechaBaja = fechaBaja;
        this.color = color;
    }

    // Getters, Setters y demas metodos utiles de la clase.
    public Integer getIdactivoFijo() {
        return idactivoFijo;
    }

    public void setIdactivoFijo(Integer idactivoFijo) {
        this.idactivoFijo = idactivoFijo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumInventario() {
        return numInventario;
    }

    public void setNumInventario(String numInventario) {
        this.numInventario = numInventario;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Area getAreaIdarea() {
        return areaIdarea;
    }

    public void setAreaIdarea(Area areaIdarea) {
        this.areaIdarea = areaIdarea;
    }

    public Estado getEstadoIdestado() {
        return estadoIdestado;
    }

    public void setEstadoIdestado(Estado estadoIdestado) {
        this.estadoIdestado = estadoIdestado;
    }

    public Persona getPersonaIdpersona() {
        return personaIdpersona;
    }

    public void setPersonaIdpersona(Persona personaIdpersona) {
        this.personaIdpersona = personaIdpersona;
    }

    public Tipo getTipoIdtipo() {
        return tipoIdtipo;
    }

    public void setTipoIdtipo(Tipo tipoIdtipo) {
        this.tipoIdtipo = tipoIdtipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactivoFijo != null ? idactivoFijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoFijo)) {
            return false;
        }
        ActivoFijo other = (ActivoFijo) object;
        return !((this.idactivoFijo == null && other.idactivoFijo != null) 
                || (this.idactivoFijo != null 
                && !this.idactivoFijo.equals(other.idactivoFijo)));
    }

    @Override
    public String toString() {
        return java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("entities/ActivoFijo").getString("ENTITIES.ACTIVOFIJO[ IDACTIVOFIJO={0} ]"), new Object[] {idactivoFijo});
    }

}
