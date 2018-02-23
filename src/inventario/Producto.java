package inventario;
import java.io.Serializable;

/**
 * @author Eduar
 */

public class Producto implements Serializable{
    private int id;
    private String nombre;
    private String descripcion;
    private String unidad;
    private int cantidad;
    private float precioc;
    private float preciov;

    public Producto (){}
    
    public Producto(int id, String nombre, String descripcion, String unidad,int cantidad, float precioc, float preciov ){
     this.id=id;
     this.nombre=nombre;
     this.descripcion = descripcion;
     this.unidad=unidad;
     this.cantidad=cantidad;
     this.precioc=precioc;
     this.preciov=preciov;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioc() {
        return precioc;
    }

    public void setPrecioc(float precioc) {
        this.precioc = precioc;
    }

    public float getPreciov() {
        return preciov;
    }

    public void setPreciov(float preciov) {
        this.preciov = preciov;
    }

    public String toString(){
        return ("\nID: "+id+"\nNombre: "+nombre+"\nDescripcion: "+descripcion+"\nUnidad: "+unidad+"\nCantidad: "+cantidad+"\nPrecio de Compra: "+precioc+"\nPrecio de Venta: "+preciov);
    }
}
