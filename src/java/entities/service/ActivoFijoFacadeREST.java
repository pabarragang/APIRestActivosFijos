/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response create(ActivoFijo entity) {
        if (entity.getFechaBaja().equals("")&&entity.getFechaCompra().equals("")
                &&entity.getColor().equals("")&&entity.getDescripcion().equals("")
                &&entity.getNombre().equals("")&&entity.getNumInventario().equals("")
                &&entity.getSerial().equals("")) {
            Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.WARNING, null, "Algunos datos no fueron ingresados");
            return Response.status(Response.Status.PARTIAL_CONTENT).type(MediaType.TEXT_PLAIN).entity("Algunos datos no fueron ingresados").build();  
        }else{
            if (entity.getFechaBaja().after(entity.getFechaCompra())) {
                Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, "Activo actualizado");
                return super.create(entity);
            }else{
                Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, "La fecha de baja debe ser mayor a la de compra");
                return Response.status(Response.Status.BAD_REQUEST).header("message", "La fecha de baja debe ser mayor que la fecha de compra").build();  
            }
        }       
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, ActivoFijo entity) {
        if (entity!=null) {
            try{
                if (entity.getFechaBaja().equals("")&&entity.getFechaCompra().equals("")
                &&entity.getColor().equals("")&&entity.getDescripcion().equals("")
                &&entity.getNombre().equals("")&&entity.getNumInventario().equals("")
                &&entity.getSerial().equals("")) {
                    Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.WARNING, null, "Algunos datos no fueron ingresados");
                    return Response.status(Response.Status.PARTIAL_CONTENT).type(MediaType.TEXT_PLAIN).entity("Algunos datos no fueron ingresados").build();  
                }else{
                    if (entity.getFechaBaja().after(entity.getFechaCompra())) {
                        Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, "Activo actualizado");
                        return super.edit(entity);
                    }else{
                        Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, "La fecha de baja debe ser mayor a la de compra");
                        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("La fecha de baja debe ser mayor que la fecha de compra").build();  
                    }
                }
            }catch(NullPointerException e){
                Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.WARNING, null, "Algunos datos no fueron ingresados");
                return Response.status(Response.Status.PARTIAL_CONTENT).type(MediaType.TEXT_PLAIN).entity("Algunos datos no fueron ingresados").build();  
            }
            
        }else{
            Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, "Este activo no existe");
                    return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("Este activo no existe").build();  
        }
        
        
        
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        ActivoFijo activo = super.find(id);
        if(activo!=null){
            Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.INFO, "Se encontraron activos", "Success");
            return Response.ok("Se ha encontrado el activo").entity(activo).build();  
        }else{
            Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, "No se encontraron activos","Error");
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("No se ha encontrado el activo").build();
        }
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
            try{
                query = getEntityManager().createNamedQuery("ActivoFijo.findByTipo");
                query.setParameter(field, new Tipo(Integer.parseInt(value)));
                
            }catch(NumberFormatException e){
                Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
            
        }else{
            if (field.equals("fechaCompra")) {                
                try {
                    DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    date = dateformat.parse(value);
                    query = getEntityManager().createNamedQuery("ActivoFijo.findByFechaCompra");
                    query.setParameter(field, date);
                } catch (ParseException ex) {
                    Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
                
            }else{
                if (field.equals("serial")) {
                    try{
                        query = getEntityManager().createNamedQuery("ActivoFijo.findBySerial");
                        query.setParameter(field, value);
                    }catch(Exception e){
                        Logger.getLogger(ActivoFijoFacadeREST.class.getName()).log(Level.SEVERE, null, e);
                        return null;
                    }
                    
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
