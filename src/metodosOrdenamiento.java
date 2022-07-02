import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class metodosOrdenamiento {
    public static void main(String[] args) {
        Ciudad[] arregloCiudadesS = new Ciudad[100];
        Ciudad[] arregloCiudadesM = new Ciudad[100];
        Ciudad[] arregloCiudadesLR = new Ciudad[100];
        int longitudArreglo = 100;
        cargarArregloCiudades(arregloCiudadesS, longitudArreglo);
        cargarArregloCiudades(arregloCiudadesM, longitudArreglo);
        cargarArregloCiudades(arregloCiudadesLR, longitudArreglo);

        /** Prueba empirica de los tiempos de ejecucion. **/
        // Para Ordenamiento por Seleccion.
        long inicioS = System.nanoTime();
        Ciudad.arrOrdenadoSeleccion(arregloCiudadesS, 'a');
        long finS = System.nanoTime();
        long totalS = (finS - inicioS) / 10000;
        System.out.println("Tiempo total para Seleccion: " + totalS + " milisegundos.");

        // Para ordenamiento por MergeSort.
        long inicioM = System.nanoTime();
        Ciudad.mergeSort(arregloCiudadesM, 0, longitudArreglo - 1);
        long finM = System.nanoTime();
        long totalM = (finM - inicioM) / 10000;
        System.out.println("Tiempo total para MergeSort: " + totalM + " milisegundos.");

        // Para listar ciudades con letras repetidas.
        long inicioLR = System.nanoTime();
        Ciudad.listaCiudadesRepLetra(arregloCiudadesLR);
        long finLR = System.nanoTime();
        long totalLR = (finLR - inicioLR) / 10000;
        System.out.println("Tiempo total para Listar Ciudades con Letra Repetida: " + totalLR + " milisegundos.");

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
        // Lee un arreglo unidimensional.
        boolean repite=true;
        int i=0;
        while(i<arr.length && repite){
            if (arr[i] != null) {
                System.out.print("Posicion: " + i + " | ");
                System.out.println(arr[i].toString());
                i++;
            }else{
                repite = false;
            }
            
        }
    }
}
