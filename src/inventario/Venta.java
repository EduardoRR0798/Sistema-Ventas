package inventario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Venta implements Serializable{
    private int id;
    private Date fechav;
    private float total;
    private float tiva;
    private ArrayList<Producto> productos;
            
    public Venta(){}
    
    public Venta(Date fechav){
        this.fechav=fechav;
        productos=new ArrayList<>();
    }

    public Date getFechav() {
        return fechav;
    }

    public void setFechav(Date fechav) {
        this.fechav = fechav;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public Producto getProducto(int indice){
        return productos.get(indice);
    }
    
    public void setProducto(Producto p,int cant){
        p=new Producto(p.getId(),p.getNombre(),null,null,cant,0,p.getPreciov());
        productos.add(p);
    }
    
    public int getTamanio(){
        return productos.size();
    }
    public String toString(){
        return ("\tFecha: "+fechav+"\nTotal: "+total);
    }

    public float getTiva() {
        return tiva;
    }

    public void setTiva(float tiva) {
        this.tiva = tiva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
