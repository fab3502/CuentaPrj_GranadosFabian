import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Fabian Granados
 * @version 9/17/25
 */
public class PrincipalCuenta
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1; 
        
        System.out.println("======================================");
        System.out.println("   CLI de Prueba - Clase Cuenta");
        System.out.println("======================================");

        boolean salir = false;
        
        while (!salir){
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("9) Modificar nombre de cuentahabiente");
            System.out.println("0) Salir");
            
            System.out.print("Opción: ");
            String opc = sc.nextLine().trim();
            
            switch(opc){
                case "1": { //Crear cuenta
                    System.out.print("Ingrese el nombre del cuentahabiente (Enter para omitir): ");
                    String nombreCuentaHabiente = sc.nextLine().trim();
                    Cuenta c;
                    double saldoDouble;
                    while(true){
                        System.out.print("Ingrese el saldo inicial de la cuenta: ");
                        String saldoString = sc.nextLine().trim();
                        try{
                            saldoDouble = Double.parseDouble(saldoString);
                            break;
                        } catch (NumberFormatException e){
                            System.out.println("Numero invalido");
                        }
                    }
                    if (nombreCuentaHabiente.isEmpty()){
                        c = new Cuenta(saldoDouble);
                    } else{
                        c = new Cuenta(nombreCuentaHabiente, saldoDouble);
                    }
                    cuentas.add(c);
                    actual = cuentas.size()-1;
                    System.out.println("Cuenta creada y seleccionada (indice "+actual+").");
                    break;
                }
                case "2": { //Conocer cantidad de cuentas creadas
                    System.out.println("Cantidad de cuentas creadas: "+ cuentas.size());
                    break;
                }
                case "3": { //Listar cuentas
                    if (cuentas.isEmpty()){
                        System.out.println("No hay cuentas creadas.");
                        break;
                    } else {
                        System.out.println("Indice | Nombre | Saldo");
                        for (int i = 0;i<cuentas.size();i++){
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %s | %.2f%n", i, c.getNombreCuentaHabiente(), c.getSaldo());
                        }
                    }
                    
                    break;
                }
                case "4": { //Seleccionar cuenta
                    if (cuentas.isEmpty()){
                        System.out.println("No hay cuentas creadas");
                        break;
                    }
                    System.out.println("Indice de la cuenta a seleccionar: ");
                    String idxS = sc.nextLine().trim();
                    try{
                        int idx = Integer.parseInt(idxS);
                        if (idx >= 0 && idx < cuentas.size()){
                            actual = idx;
                            System.out.println("Cuenta indice "+actual+" seleccionada.");
                        } else {
                            System.out.println("Indice fuera de rango");
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Indice invalido, introduzca un numero.");
                    }
                    break;
                }
                case "5": { //Depositar
                    if (actual < 0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero");
                        break;
                    }
                    Cuenta c = cuentas.get(actual);
                    double monto;
                    System.out.print("Ingrese el monto a depositar: ");
                    String montoS = sc.nextLine().trim();
                    try{
                        monto = Double.parseDouble(montoS);
                    } catch (NumberFormatException e){
                        System.out.println("Monto invalido");
                        break;
                    }
                    if (monto <= 0){
                        System.out.println("Monto invalido");
                        break;
                    }
                    double nuevoSaldo = c.depositar(monto);
                    System.out.println("Deposito exitoso. Nuevo saldo: "+nuevoSaldo);
                    break;
                }
                case "6": { //Retirar
                    if (actual < 0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero");
                        break;
                    }
                    Cuenta c = cuentas.get(actual);
                    double monto;
                    System.out.print("Ingrese el monto a retirar: ");
                    String montoS = sc.nextLine().trim();
                    try{
                        monto = Double.parseDouble(montoS);
                    } catch (NumberFormatException e){
                        System.out.println("Monto invalido");
                        break;
                    }
                    if (monto <= 0 || monto > c.getSaldo()){
                        System.out.println("Monto invalido o fondos insuficientes.");
                        break;
                    }
                    double nuevoSaldo = c.retirar(monto);
                    System.out.println("Retiro exitoso. Nuevo saldo: "+nuevoSaldo);
                    break;
                }
                case "7": { //Consultar saldo
                    if (actual < 0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero");
                        break;
                    }
                    Cuenta c = cuentas.get(actual);
                    System.out.println("Saldo de la cuenta " + c.getCodCuenta() + ": " + c.getSaldo());
                    break;
                }
                case "8": { //Consultar estado(toString())
                    if (actual < 0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero");
                        break;
                    }
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }
                case "9": { //Cambiar/agregar nombre de cuentahabiente
                    if (actual < 0 || cuentas.isEmpty()){
                        System.out.println("Debe crear y seleccionar una cuenta primero");
                        break;
                    }
                    System.out.println("Ingrese el nuevo nombre de cuentahabiente: ");
                    String nuevoNombre = sc.nextLine().trim();
                    if(nuevoNombre.isEmpty()){
                        System.out.println("Nombre invalido");
                        break;
                    }
                    Cuenta c = cuentas.get(actual);
                    c.setNombreCuentaHabiente(nuevoNombre);
                    System.out.println("Nombre de cuentahabiente modificado con exito.");
                    break;
                }
                case "0": {
                    salir = true;
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}