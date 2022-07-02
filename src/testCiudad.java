import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testCiudad {
    public static void main(String[] args) {
        System.out.println("Se cargará el arreglo de Ciudades.");
        Ciudad[] arregloCiudades = new Ciudad[100];
        int longitudArreglo = arregloCiudades.length;
        cargarArregloCiudades(arregloCiudades, longitudArreglo);
        menu(arregloCiudades);
    }

    public static void menu(Ciudad[] arreglo) {
        int respuesta, posArr, longitud;
        char tipoOrden, otraRep = 'n';
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(
                    "Ingrese la opción: \n1) Ver el arreglo. \n2) Copiar y ordenar el arreglo. \n3) Ver abreviatura de alguna ciudad. \nCualquier otro para terminar");
            respuesta = sc.nextInt();

            switch (respuesta) {
                case 1:
                    leerArreglo(arreglo);
                    System.out.println("Desea realizar otra funcion? s para si, cualquier otra para terminar.");
                    otraRep = sc.next().charAt(0);
                    break;
                case 2:
                    Ciudad[] copiaArr = Ciudad.copiarArreglo(arreglo);
                    System.out.println("Se ha creado una copia del arreglo.");
                    do {
                        System.out.println("Ingrese el tipo de orden (Seleccion).\na) ascendente d) descendente.");
                        tipoOrden = sc.next().charAt(0);
                    } while (tipoOrden != 'a' && tipoOrden != 'd');
                    Ciudad.arrOrdenadoSeleccion(copiaArr, tipoOrden);
                    System.out.println("Arreglo original: ");
                    leerArreglo(arreglo);
                    System.out.println("Arreglo copiado y ordenado: ");
                    leerArreglo(copiaArr);
                    System.out.println("Desea realizar otra funcion? s para si, cualquier otra para terminar.");
                    otraRep = sc.next().charAt(0);
                    break;
                case 3:
                    Ciudad.arrOrdenadoSeleccion(arreglo, 'a');
                    leerArreglo(arreglo);
                    System.out.println("Ingrese la posicion de la ciudad que quiere ver la abreviatura.");
                    posArr = sc.nextInt();
                    longitud = arreglo[posArr].getNombreCiudad().replaceAll(" ", "").length();
                    System.out.println(arreglo[posArr].abreviaturaNombre(longitud - 1));
                    System.out.println("Desea realizar otra funcion? s para si, cualquier otra para terminar.");
                    otraRep = sc.next().charAt(0);
                    break;
                case 4:
                    System.out.println("A contunuacion se enlistaran las ciudades que tengan al menos dos letras iguales en su nombre:");
                    //logica
                    System.out.println("Desea realizar otra funcion? s para si, cualquier otra para terminar.");
                    otraRep = sc.next().charAt(0);
                    break;
                default:
                    System.out.println("Fin del programa.");
            }
        } while (otraRep == 's');
        System.out.println("Fin del programa.");
        sc.close();
    }

    public static void cargarArregloCiudades(Ciudad[] arrCiudad, int longitudArr) {
        // Este modulo carga el arreglo del objeto Ciudad con los datos obtenidos de
        // ciudades.txt.
        String ruta = "src/ciudades.txt", linea;
        try {
            BufferedReader buff = new BufferedReader(new FileReader(ruta));
            Scanner sc = new Scanner(buff);
            for (int i = 0; i < longitudArr; i++) {
                linea = sc.nextLine();
                String[] datos = linea.split(";");
                arrCiudad[i] = new Ciudad(datos[0], Integer.parseInt(datos[1]), Float.parseFloat(datos[2]),
                        Float.parseFloat(datos[3]));
            }
            buff.close();
            sc.close();
        } catch (FileNotFoundException ex) { // a continuacion se muestran los posibles errores que pueden salir.
            System.err.println(ex.getMessage() + "\nEl archivo que desea leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }

    public static void leerArreglo(Ciudad[] arr) {
        //Lee un arreglo unidimensional.
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Posicion: " + i + " | ");
            System.out.println(arr[i].toString());
        }
    }
}
