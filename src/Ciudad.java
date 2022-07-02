public class Ciudad {
    // Atributos
    private String nombreCiudad;
    private int poblacion;
    private float latitud;
    private float longitud;

    // Contructores
    public Ciudad(String nombreCiudad, int poblacion, float latitud, float longitud) {
        this.nombreCiudad = nombreCiudad;
        this.poblacion = poblacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
        this.poblacion = -1;
        this.latitud = 0;
        this.longitud = 0;
    }

    // Observadores
    public String getNombreCiudad() {
        return this.nombreCiudad;
    }

    public int getPoblacion() {
        return this.poblacion;
    }

    public float getLatitud() {
        return this.latitud;
    }

    public float getLongitud() {
        return this.longitud;
    }

    // Modificadores
    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    // Propias del tipo
    public boolean equals(Ciudad otraCiudad) {
        return (this.nombreCiudad.equalsIgnoreCase(otraCiudad.getNombreCiudad()));
    }

    public int compareToCiudad(Ciudad otraCiudad) {
        return this.nombreCiudad.compareTo(otraCiudad.getNombreCiudad());
    }

    public String toString() {
        String cadena = "Ciudad: " + this.getNombreCiudad() + " | Poblacion: " + this.getPoblacion() + " | Latitud: "
                + this.getLatitud() + " | Longitud: " + this.getLongitud();
        return cadena;
    }

    public String abreviaturaNombre(int posicion){
        //Abrevia el nombre de las ciudades sacando las vocales.
        String cadena="", ciudad;
        char caracter;
        ciudad = this.getNombreCiudad().replaceAll(" ", "");
        if(posicion>=0){
            caracter = ciudad.toLowerCase().charAt(posicion);
            if(caracter == 'a' ||caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u'){
                cadena = abreviaturaNombre(posicion-1);
            }else{
                cadena = abreviaturaNombre(posicion-1) + ciudad.charAt(posicion);
            }
        }
        return cadena;
      }

    public static Ciudad[] copiarArreglo(Ciudad[] arregloCiudad) {
        //Dado un arreglo por parametro, retorna un arrglo copiado del original.
        int length = arregloCiudad.length;
        Ciudad[] copia = new Ciudad[length];
        System.arraycopy(arregloCiudad, 0, copia, 0, length);
        return copia;
    }

    public static void arrOrdenadoSeleccion(Ciudad[] arreglo, char tipoOrden) {
        int i, longitud = arreglo.length, posCiudadMenor;
        if (tipoOrden == 'a') {
            for (i = 0; i < longitud; i++) {
                posCiudadMenor = buscarPosMenorAscendente(arreglo, i);
                if (arreglo[posCiudadMenor].compareToCiudad(arreglo[i]) < 0) {
                    intercambiar(arreglo, posCiudadMenor, i);
                }
            }
        }else{
            for(i = longitud-1; i>=0; i--){
                posCiudadMenor = buscarPosMenorDescendente(arreglo, i);
                if (arreglo[posCiudadMenor].compareToCiudad(arreglo[i]) < 0) {
                    intercambiar(arreglo, posCiudadMenor, i);
                }
            }
        }
    }

    private static int buscarPosMenorAscendente(Ciudad[] arreglo, int i) {
        Ciudad menorCiudad = new Ciudad("ZZZZzzzzzzzzzzzzzzz");
        int posicion = i;
        for (int j = i; j < arreglo.length; j++) { //Variacion en el for
            if (arreglo[j].compareToCiudad(menorCiudad) < 0) {
                menorCiudad = arreglo[j];
                posicion = j;
            }
        }
        return posicion;
    }

    private static int buscarPosMenorDescendente(Ciudad[] arreglo, int i) {
        Ciudad menorCiudad = new Ciudad("ZZZZzzzzzzzzzzzzzzz");
        int posicion = i;
        for (int j = 0; j <=i; j++) { //Variacion en el for
            if (arreglo[j].compareToCiudad(menorCiudad) < 0) {
                menorCiudad = arreglo[j];
                posicion = j;
            }
        }
        return posicion;
    }

    private static void intercambiar(Ciudad[] arreglo, int pos1, int pos2) {
        //Intercambia dos posiciones.
        Ciudad aux;
        aux = arreglo[pos1];
        arreglo[pos1] = arreglo[pos2];
        arreglo[pos2] = aux;
    }

    public static void mergeSort(Ciudad[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            // Encuentra el punto medio del arreglo.
            int medio = (izquierda + derecha) / 2;

            // Divide la primera y segunda mitad (llamada recursiva).
            mergeSort(arr, izquierda, medio);
            mergeSort(arr, medio + 1, derecha);

            // Une las mitades.
            merge(arr, izquierda, medio, derecha);
        }
    }

    private static void merge(Ciudad[] arr, int izq, int medio, int der) {
        int i, j, k;
        Ciudad[] arrAux = new Ciudad[arr.length]; // arreglo auxiliar
        for (i = izq; i <= der; i++) {// copia ambas mitades en el arreglo auxiliar
            arrAux[i] = arr[i];
        }

        // inicializaciones
        i = izq;
        j = medio + 1;
        k = izq;

        while (i <= medio && j <= der) {// copia el siguiente elemento más grande
            if (arrAux[i].compareToCiudad(arrAux[j]) < 0) { //evalua cual va primero lexicograficamente. 
                arr[k] = arrAux[i];
                k++;
                i++;
            } else {
                arr[k] = arrAux[j];
                k++;
                j++;
            }
        }

        while (i <= medio) {// copia los elementos que quedan de la primera mitad (si los hay)
            arr[k++] = arrAux[i++];
        }
    }
    

}
