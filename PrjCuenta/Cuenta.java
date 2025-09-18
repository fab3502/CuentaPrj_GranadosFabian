/**
 * @author Fabian Granados
 * @version 9/17/25
 */

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cuenta
{
    private String codCuenta;
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas;
    
    public Cuenta(String nombreCuentaHabiente, double pSaldo){ 
        cantCuentasCreadas++;
        codCuenta = "cta-"+cantCuentasCreadas;
        saldo = pSaldo;
        setNombreCuentaHabiente(nombreCuentaHabiente);
        fechaCreacion = establecerFechaCreacionCuenta();
        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;
    }
    
    public Cuenta(double pSaldo){
        this("Sin nombre", pSaldo);
    }
    
    private String establecerFechaCreacionCuenta(){
        Date fecha =new Date(System.currentTimeMillis()); 
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formatoFecha.format(fecha);
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente){
        nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta(){
        return codCuenta;
    }
    
    public String getNombreCuentaHabiente(){
        return nombreCuentaHabiente;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public double depositar(double monto){
        cantDepositosRealizados++;
        return saldo+=monto;
     }
    
    public double retirar(double monto){
        if (validarRetiro(monto)){
            cantRetirosExitososRealizados++;
            saldo-=monto;}
        return saldo;
    }
    
    private boolean validarRetiro(double monto){
        return saldo>=monto;
    }
    
    public static int getCantCuentasCreadas(){
        return cantCuentasCreadas;
    }
    
    public String toString(){
        return "Codigo de cuenta: "+codCuenta+"\n"+"Saldo: "+saldo+"\n"+"Nombre del cuentahabiente: "+nombreCuentaHabiente+
                "\n"+"Fecha de creacion: "+fechaCreacion+"\n"+"Cantidad de depositos realizados: "+cantDepositosRealizados+
                "\n"+"Cantidad de retiros exitosos realizados: "+cantRetirosExitososRealizados;
    }
}