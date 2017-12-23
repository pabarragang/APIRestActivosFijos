/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import entities.ActivoFijo;
import entities.Tipo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sun.rmi.runtime.Log;

/**
 *
 * @author andrea
 */
@Stateless
@Path("entities.activofijo")
public class ActivoFijoFacadeREST extends AbstractFacade<ActivoFijo> {

    @PersistenceContext(unitName = "APIRestActivosFijosPU")
    private EntityManager em;

    public ActivoFijoFacadeREST() {
        super(ActivoFijo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ActivoFijo entity) {
       
        if (entity.getFechaBaja().after(entity.getFechaCompra())) {
            Log.getLog("Datos válidos", "La fecha de baja debe ser mayor a la de compra", 0);
            super.create(entity);
        }else{
            Log.getLog("Datos inválidos", "La fecha de baja debe ser mayor a la de compra", 0);
        }
        
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ActivoFijo entity) {
        if (entity.getFechaBaja().before(entity.getFechaCompra())) {
            Log.getLog("Datos válidos", "La fecha de baja debe ser mayor a la de compra", 0);
            super.edit(entity);
        }else{
            Log.getLog("Datos inválidos", "La fecha de baja debe ser mayor a la de compra", 0);
        }
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ActivoFijo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ActivoFijo> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("{field}/{value}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ActivoFijo> fndByField(@PathParam("field") String field, @PathParam("value") String value) {
        javax.persistence.Query query = null;
        if (field.equals("tipo")) {
            query = getEntityManager().createNamedQuery("ActivoFijo.findByTipo");
            query.setParameter(field, new Tipo(Integer.parseInt(value)));
        }else{
            if (field.equals("fechaCompra")) {
                DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateformat.parse(value);
                } catch (ParseException ex) {
                    Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
                }
                query = getEntityManager().createNamedQuery("ActivoFijo.findByFechaCompra");
                query.setParameter(field, date);
            }else{
                if (field.equals("serial")) {
                    query = getEntityManager().createNamedQuery("ActivoFijo.findBySerial");
                    query.setParameter(field, value);
                }
            }
            
        }        
        return query.getResultList();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
