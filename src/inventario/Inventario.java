package inventario;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eduar
 */

public class Inventario {
    public static Scanner sc=new Scanner(System.in);
    private static ArrayList<Producto> productos=new ArrayList<>();
    private static ArrayList<Venta> ventas=new ArrayList<>();
    public static void main(String[] args) throws ClassNotFoundException {

        Producto p=new Producto();
        int opcion;
        do{
            System.out.println("Seleccione una opcion:");
            System.out.println("\t1. Alta");
            System.out.println("\t2. Baja");
            System.out.println("\t3. Cambio");
            System.out.println("\t4. Mostrar");
            System.out.println("\t5. Vender");
            System.out.println("\t6. Mostrar ventas");
            System.out.println("\t7. Reporte de ventas");
            System.out.println("\t8. Generar ganancia total (coming soon)");
            System.out.println("\t9. Salir");
            opcion=sc.nextInt();
          
            switch(opcion){

                case 1://alta
                    pedirDatos();
                    break;
                case 2://baja
                    Producto pbaja;
                    int idbaja;
                    System.out.println("Ingrese el ID del producto:");
                    idbaja=sc.nextInt();
                        try{
                            FileInputStream fis=new FileInputStream(".\\Inventario.bin");
                            ObjectInputStream ois=new ObjectInputStream(fis);
                            while(fis.available()>0){
                                pbaja=((Producto)ois.readObject());
                                productos.add(pbaja);
                            }
                            fis.close();
                            ois.close(); 
                            }catch(EOFException e){
                                System.out.println("Error: "+e.getMessage());
                            }catch(IOException e1){
                                System.out.println("Error: "+e1.getMessage());
                            }catch(ClassNotFoundException e2){
                                System.out.println("Error: "+e2.getMessage());
                            }
                        for(int f=0;f<productos.size();f++){
                            System.out.println(f);
                            if(productos.get(f).getId()==idbaja){
                                productos.remove(f);
                            }
                        }
                        try{
                            FileOutputStream fos=new FileOutputStream(".\\Inventario.bin");
                            ObjectOutputStream oos= new ObjectOutputStream(fos);
                            for(int j=0; j<productos.size();j++){
                                oos.writeObject(productos.get(j));
                            }
                            fos.close();
                            oos.close();
                            }catch(IOException e1){
                                System.err.println("Error: " +e1);
                                }
                            while(productos.size()!=0){
                                productos.remove(productos.size()-1);
                            }
                    break;
                case 3: //cambio
                    cambio();
                    break;
                case 4: //mostrar
                    leerProductos();
                    for(int q=0;q<productos.size();q++){
                        System.out.println(productos.get(q).toString());
                        System.out.println("-------------------------");
                    }
                    escribirProductos();
                    break;
                case 5://vender
                    Vender();
                    break;
                case 6://mostrar ventas
                    MostrarVentas();
                    break;
                case 7:
                    Date inicio, fin;
                    String first, end;
                    System.out.println("Ingrese la fecha de inicio dd/MM/yyyy: ");
                    sc.nextLine();
                    first=sc.nextLine();
                    System.out.println("Ingrese la fecha de fin dd/MM/yyyy: ");
                    end=sc.nextLine();
                    try{
                        
                        inicio=new SimpleDateFormat("dd/MM/yyyy").parse(first);
                        fin= new SimpleDateFormat("dd/MM/yyyy").parse(end);
                        mostrardesdefecha(inicio, fin);
                    }catch(ParseException ex){
                        
                    }
                    
                    break;
                case 8:
                    CalcularGanancia();
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while(opcion!=9);
        
    }
    public static void pedirDatos(){
        Producto pro;
                int i,cant;
                String nom, desc, uni;
                float prec, precv;
                System.out.println("\tAlta");
                System.out.println("Ingrese el id");
                i=sc.nextInt();
                System.out.println("Ingrese el nombre");
                sc.nextLine();//comer salto de linea generado con el int
                nom=sc.nextLine();
                System.out.println("Ingrese la descripcion");
                desc=sc.nextLine();
                System.out.println("Ingrese la unidad");
                uni=sc.nextLine();
                System.out.println("Ingrese la cantidad");
                cant=sc.nextInt();
                System.out.println("Ingrese el precio de compra");
                prec=sc.nextFloat();
                precv=prec*2;

                pro = new Producto(i, nom, desc, uni, cant, prec, precv);    
                    try{
                        FileInputStream fis=new FileInputStream(".\\Inventario.bin");
                        ObjectInputStream ois=new ObjectInputStream(fis);
                        while(fis.available()>0){
                            productos.add((Producto) ois.readObject());
                        }
                            ois.close();
                            fis.close();
                        }catch(IOException err1){
                            System.out.print("Error: "+err1);
                        }catch(ClassNotFoundException err2){
                            System.out.print("Error: "+err2);
                        }
                        productos.add(pro);
                        try{
                            FileOutputStream fos=new FileOutputStream(".\\Inventario.bin");
                            ObjectOutputStream oos= new ObjectOutputStream(fos);
                                for(int j=0; j<productos.size();j++){
                                     oos.writeObject(productos.get(j));
                               }
                            fos.close();
                            oos.close();
                        }catch(IOException e1){
                            System.err.println("Error: " +e1);
                            }
                        while(productos.size()!=0){
                            productos.remove(productos.size()-1);
                        }
                        System.out.println(productos.size());
    }
    public static void cambio(){
        Producto aux=new Producto();
        int opcion=0;
        int idbusq;
        try{
            FileInputStream fis=new FileInputStream(".\\Inventario.bin");
            ObjectInputStream ois=new ObjectInputStream(fis);
            while(fis.available()>0){
                aux=((Producto)ois.readObject());
                    productos.add(aux);
            }
                fis.close();
                ois.close(); 
        }catch(EOFException e){
            System.out.println("Error: "+e.getMessage());
        }catch(IOException e1){
            System.out.println("Error: "+e1.getMessage());
        }catch(ClassNotFoundException e2){
            System.out.println("Error: "+e2.getMessage());
        }
        System.out.println("\tIngrese la opcion: ");
        System.out.println("\t1. Agregar a un producto");
        System.out.println("\t2. Modificar");
        opcion=sc.nextInt();
        switch(opcion){
            case 1:
                int idmod=0, cantmod=0;
                float nprecio=0, tot=0, totaux=0;
                System.out.println("Ingrese el ID del producto:");
                idmod=sc.nextInt();
                for(int g=0;g<productos.size();g++){
                    if(productos.get(g).getId()==idmod){
                       System.out.println("Ingrese la cantidad que desea agregar: ");
                       cantmod=sc.nextInt();
                       System.out.println("ingrese el precio de compra");
                       nprecio=sc.nextFloat();
                        if(nprecio!=productos.get(g).getPrecioc()){
                            for(int ind=0;ind<productos.get(g).getCantidad();ind++){
                               tot+=productos.get(g).getPrecioc();
                            }
                            totaux=nprecio*cantmod;
                            tot+=totaux;
                            int cantaux;
                            cantaux=productos.get(g).getCantidad()+cantmod;
                            tot=tot/cantaux;
                            productos.get(g).setPrecioc(tot);
                            
                            productos.get(g).setPreciov(tot*2);
                        }
                       
                       productos.get(g).setCantidad(cantmod+productos.get(g).getCantidad());
                    }
                }
                break;
            case 2:
                System.out.println("ingrese el id del producto que desea modificar");
                idbusq=sc.nextInt();
                for(int f=0;f<productos.size();f++){
                if(productos.get(f).getId()==idbusq){
                    int i,cant;
                    String nom, desc, uni;
                    float prec, precv;
                    System.out.println("Ingrese el id");
                    i=sc.nextInt();
                    productos.get(f).setId(i);
                    System.out.println("Ingrese el nombre");
                    sc.nextLine();//comer salto de linea generado con el int
                    nom=sc.nextLine();
                    productos.get(f).setNombre(nom);
                    System.out.println("Ingrese la descripcion");
                    desc=sc.nextLine();
                    productos.get(f).setDescripcion(desc);
                    System.out.println("Ingrese la unidad");
                    uni=sc.nextLine();
                    productos.get(f).setUnidad(uni);
                    System.out.println("Ingrese la cantidad");
                    cant=sc.nextInt();
                    productos.get(f).setCantidad(cant);
                    System.out.println("Ingrese el precio de compra");
                    prec=sc.nextFloat();
                    productos.get(f).setPrecioc(prec);
                    precv=prec*2;
                    productos.get(f).setPreciov(precv);
                }
                    }
                break;
        }   
        try{
            FileOutputStream fos=new FileOutputStream(".\\Inventario.bin");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            for(int j=0; j<productos.size();j++){
                oos.writeObject(productos.get(j));
            }
            fos.close();
            oos.close();
        }catch(IOException e11){
            System.err.println("Error: " +e11);
        }
        while(productos.size()!=0){
            productos.remove(productos.size()-1);
        }
    }
    public static void Vender(){
        File f=new File(".\\Registros.bin");
        Producto pventa, paux=new Producto();
        Venta venta, ventaact;
        int idventa=0, cantventa=0, opcion=0, id=0;
        Date fechav=null;
        float pfinal=0, total=0, tiva=0, iva=16, ivaaux=0, precioaux=0, ivafinal=0;
        try{
           FileInputStream fis=new FileInputStream(".\\Inventario.bin");
           ObjectInputStream ois=new ObjectInputStream(fis);
           while(fis.available()>0){
               pventa=((Producto) ois.readObject());
               productos.add(pventa);
           }
           fis.close();
           ois.close();
           if(f.exists()){
            FileInputStream fis2=new FileInputStream(".\\Registros.bin");
            ObjectInputStream ois2=new ObjectInputStream(fis2);
            while(fis2.available()>0){
               venta=((Venta) ois2.readObject());
               ventas.add(venta);
           }
           fis2.close();
           ois2.close();
           }else{
               f.createNewFile();
           }
           
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Ingrese el dia/mes/anio: ");
        sc.nextLine();
        try {
            fechav = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
        } catch (ParseException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventaact=new Venta(fechav);
        do{
            System.out.println("Ingrese el id de la venta: ");
            id=sc.nextInt();
            System.out.println("Ingrese el id del producto: ");
            idventa=sc.nextInt();
            System.out.println("Ingrese la cantidad: ");
            cantventa=sc.nextInt();
            for(int i=0;i<productos.size();i++){
                paux=((Producto) productos.get(i));
               if(paux.getId()==idventa&&productos.get(i).getCantidad()-cantventa>=0){
                   ivaaux=paux.getPreciov();
                   precioaux=paux.getPreciov();
                    ventaact.setProducto(paux,cantventa);
                    total=paux.getPreciov()*cantventa;
                    productos.get(i).setCantidad(paux.getCantidad()-cantventa);
               }
            }
            ivaaux=ivaaux*(iva/100);
            precioaux=precioaux-ivaaux;
            total=precioaux*cantventa;
            tiva=ivaaux*cantventa;
            System.out.println("IMPORTE: "+ (pfinal+=total));
            ivafinal+=tiva;
            System.out.println("IVA: "+ ivafinal);
            System.out.println("TOTAL: "+(pfinal+ivafinal));
            System.out.println("Desea aniadir otro producto?:");
            System.out.println("1. Si");
            System.out.println("2. No");
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    opcion=1;          
                    break;
                case 2:;
                    opcion=2;        
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }while(opcion!=2);
        ventaact.setTotal(pfinal);
        ventaact.setTiva(ivafinal);
        ventaact.setId(ventas.size()+1);
        if(ventaact.getTotal()>0){
            ventas.add(ventaact);  
        }else{
            System.out.println("Venta no realizada.");
        }
        try{
            FileOutputStream fos3=new FileOutputStream(".\\Inventario.bin");
            ObjectOutputStream oos3= new ObjectOutputStream(fos3);
            for(int j=0; j<productos.size();j++){
                oos3.writeObject(productos.get(j));
            }
            fos3.close();
            oos3.close();
            FileOutputStream fos4=new FileOutputStream(".\\Registros.bin");
            ObjectOutputStream oos4=new ObjectOutputStream(fos4);
            for(int k=0;k<ventas.size();k++){
                oos4.writeObject(ventas.get(k));
            }
            fos4.close();
            oos4.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(productos.size()!=0){
            productos.remove(productos.size()-1);
        }
        while(ventas.size()!=0){
            ventas.remove(ventas.size()-1);
        }
    }
    public static void MostrarVentas(){

        Venta venta;
        try{
           FileInputStream fis2=new FileInputStream(".\\Registros.bin");
           ObjectInputStream ois2=new ObjectInputStream(fis2);
           while(fis2.available()>0){
               venta=((Venta) ois2.readObject());
               ventas.add(venta);
           }
           fis2.close();
           ois2.close();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        float total;
        Producto produc;
        for(int i=0;i<ventas.size();i++){
            venta=((Venta) ventas.get(i));
            System.out.println("Fecha: "+venta.getFechav());
            for(int j=0;j<ventas.get(i).getTamanio();j++){
                produc= ((Producto)venta.getProducto(j));
                System.out.println("ID: "+produc.getId());
                total= (produc.getPreciov()*produc.getCantidad());
                System.out.println("Cantidad: "+produc.getCantidad()+" Producto: "+ produc.getNombre()+" Total: "+total);
            }
            System.out.println("IMPORTE: "+venta.getTotal());
            System.out.println("IVA: "+venta.getTiva());
            System.out.println("TOTAL: "+(venta.getTotal()+venta.getTiva()));
            System.out.println("------------------------------\n");
        }
        float iva=0, gananciatotal=0;
        for(int j=0;j<ventas.size();j++){
            iva+=ventas.get(j).getTiva();
            gananciatotal+=ventas.get(j).getTotal();
        }
        System.out.println("Total de IVA a pagar: " + iva);
        System.out.println("Total ganado: "+ gananciatotal);
        try{
        FileOutputStream fos4=new FileOutputStream(".\\Registros.bin");
            ObjectOutputStream oos4=new ObjectOutputStream(fos4);
            for(int k=0;k<ventas.size();k++){
                oos4.writeObject(ventas.get(k));
            }
            fos4.close();
            oos4.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(ventas.size()!=0){
            ventas.remove(ventas.size()-1);
        }
    }
    public static void mostrardesdefecha(Date inicio, Date fin){
        leerVentas();
        System.out.println("\tREPORTE DE VENTAS DESDE "+inicio+" HASTA "+fin);
        System.out.println("\t---------------------------------------------");
        Producto prto;
        Venta vnta;
        float total=0, totalganado=0;
        int posini=0, posfin=0;
        for(int i=0;i<ventas.size();i++){
            vnta=((Venta) ventas.get(i));
            if(vnta.getFechav().equals(inicio)||ventas.get(i).getFechav().after(inicio)&&ventas.get(i).getFechav().before(fin)||vnta.getFechav().equals(fin)){
                System.out.println("Fecha: "+vnta.getFechav());
                System.out.println("ID Venta: "+vnta.getId());
                if(vnta.getFechav().equals(inicio)){
                    posini=i;
                }if(vnta.getFechav().equals(fin)){
                    posfin=i;
                }
                for(int j=0;j<vnta.getTamanio();j++){
                    prto=((Producto) vnta.getProducto(j));
                    total=(prto.getPreciov()*prto.getCantidad());
                }
                System.out.println("IMPORTE: "+vnta.getTotal());
                System.out.println("IVA: "+vnta.getTiva());
                System.out.println("TOTAL: "+(vnta.getTotal()+vnta.getTiva()));
                System.out.println("------------------------------\n");
            }
        }
        float iva=0, gananciatotal=0;
        for(int j=posini;j<=posfin;j++){
            iva+=ventas.get(j).getTiva();
            gananciatotal+=ventas.get(j).getTotal();
        }
        System.out.println("Total de IVA a pagar: " + iva);
        System.out.println("Total ganado: "+ (gananciatotal/2));
        escribirVentas();
    }
    public static void leerVentas(){
        Venta venta;
        try{
        FileInputStream fis=new FileInputStream(".\\Registros.bin");
        ObjectInputStream ois=new ObjectInputStream(fis);
        while(fis.available()>0){
            venta = ((Venta)ois.readObject());
            ventas.add(venta);
        }
        fis.close();
        ois.close();
        }catch(IOException ex){
            
        }catch(ClassNotFoundException ex){
            
        }
    }
    public static void escribirVentas(){
        try{
            FileOutputStream fos=new FileOutputStream(".\\Registros.bin");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
                for(int i=0;i<ventas.size();i++){
                    oos.writeObject(ventas.get(i));
                }
                fos.close();
                oos.close();
        }catch(IOException ex){
            
        }
        while(ventas.size()!=0){
            ventas.remove(ventas.size()-1);
        }
    }
    public static void CalcularGanancia(){
        leerProductos(); 
        float ganancia=0, subganancia=0;
        for(int i=0;i<productos.size();i++){
            subganancia=productos.get(i).getPrecioc()*productos.get(i).getCantidad();
            ganancia+=subganancia;
        }
        System.out.println("La ganancia total del inventario es: "+ganancia+"$");
        escribirProductos();
    }
    public static void leerProductos(){
        Producto produc;
        try{
            FileInputStream fis = new FileInputStream(".\\Inventario.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available()>0){
                produc=((Producto)ois.readObject());
                productos.add(produc);
            }
            fis.close();
            ois.close();
            
        }catch(IOException ex){
            
        }catch(ClassNotFoundException ex1){
            
        }
    }
    public static void escribirProductos(){
        try{
            FileOutputStream fos=new FileOutputStream(".\\Inventario.bin");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            for(int i=0;i<productos.size();i++){
                oos.writeObject(productos.get(i));
            }
            fos.close();
            oos.close();
        }catch(IOException ex){   
        }
        while(productos.size()>0){
            productos.remove(productos.size()-1);
        }
    }
}
//UDACITY
//How to Use Git and GitHub