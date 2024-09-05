//Práctica – Aproximación Intuitiva a la Complejidad Algoritmica
//Autor: Diego Andrés Gonzalez Graciano
//Fecha: 05/09/2024

import java.io.File;     
import java.util.Scanner;  //Se importan las librerías io.File y Scanner para leer fácilmente el archivo de texto
import java.util.ArrayList;
import java.util.List;

class Practica {
  
  public static void main(String[] args) {
    
    int n = 10000 ; // Variable para controlar el número de palabras a ordenar
    String[] palabras = crearArreglo("palabras.txt", n); //Se crea el arreglo que contendrá las palabras extraídas del archivo de texto

    double tiempoInicialBubble = System.nanoTime();  //Con la ayuda del método nanoTime se mide el tiempo de ejecución
    bubbleSort(palabras, n);  // Se ordena el arreglo mediante el BubbleSort
    double tiempoFinalBubble = System.nanoTime();
    double duracionBubble = (tiempoFinalBubble - tiempoInicialBubble) / (1000000000);

    double tiempoInicialBucket = System.nanoTime();
    bucketSort(palabras, n); // Se ordena el arreglo mediante el BucketSort
    double tiempoFinalBucket = System.nanoTime();
    double duracionBucket = (tiempoFinalBucket - tiempoInicialBucket) / (1000000000);

    imprimirPalabras(palabras, n);  //Se imprimen las palabras ya ordenadas lexicograficamente

    System.out.println("\nEl tiempo total de ejecución al ordenar " + n + " palabras con el BubbleSort fue de: "  + duracionBubble + " Segundos");
    System.out.println("El tiempo total de ejecución al ordenar " + n + " palabras con el BucketSort fue de: "  + duracionBucket + " Segundos");
  }
  public static String[] crearArreglo(String nombreArchivo, int n) {  //Se crea la función para leer las palabras del archivo txt y almacenarlas en un arreglo
    
    String[] arreglo = new String[n];
    try {
      File archivo = new File(nombreArchivo);
      Scanner scan = new Scanner(archivo);
      
      for (int i = 0; i < n && scan.hasNextLine(); i++) {
        arreglo[i] = scan.nextLine();
      }
    }
    catch (Exception e) {
      System.out.println("No se ha encontrado el archivo: " + nombreArchivo);
    }
    
    return arreglo; //Devuelve un arreglo con las palabras extraídas
  }
  public static void imprimirPalabras(String[] arreglo, int n) { // Se crea la función para imprimir las palabras del arreglo
    
    System.out.print(
      
    "▀██▀▀█▄      █     ▀██▀          █     ▀██▀▀█▄   ▀██▀▀█▄       █      ▄█▀▀▀▄█  \r\n" +
    " ██   ██    ███     ██          ███     ██   ██   ██   ██     ███     ██▄▄  ▀  \r\n" +
    " ██▄▄▄█▀   █  ██    ██         █  ██    ██▀▀▀█▄   ██▀▀█▀     █  ██     ▀▀███▄  \r\n" +
    " ██       ▄▀▀▀▀█▄   ██        ▄▀▀▀▀█▄   ██    ██  ██   █▄   ▄▀▀▀▀█▄  ▄     ▀██ \r\n" +
    "▄██▄     ▄█▄  ▄██▄ ▄██▄▄▄▄▄█ ▄█▄  ▄██▄ ▄██▄▄▄█▀  ▄██▄  ▀█▀ ▄█▄  ▄██▄ █▀▄▄▄▄█▀  \r\n" +
    "                                                                               \r\n");
    
    for (int i = 0; i < n; i++) {
      System.out.println((i + 1) + ": " + arreglo[i]);
    }
  }
  public static void bubbleSort(String[] arreglo, int n) { //Función que me ordena las palabras mediante el algoritmo de ordenamiento burbuja
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arreglo[j].compareTo(arreglo[j + 1]) > 0) { //Con ayuda del método compareTo() se comparan las cadenas para determinar si se hace el swap
          String aux = arreglo[j];
          arreglo[j] = arreglo[j + 1];
          arreglo[j + 1] = aux;
        }
      }
    }
  }
  public static void insertionSort(List<String> bucket, int n) {

    for (int i = 1; i < n; ++i) {
      String key = bucket.get(i);
      int j = i - 1;

      while (j >= 0 && bucket.get(j).compareTo(key) > 0) {
        bucket.set(j + 1, bucket.get(j));
        j--;
      }
      bucket.set(j + 1, key);
    }
  }
  public static void bucketSort(String[] arr, int n) {
    
    String min = arr[0];
    String max = arr[0];

    for (int i = 1; i < n; i++) {
      if (arr[i].compareTo(min) < 0) min = arr[i];
      if (arr[i].compareTo(max) > 0) max = arr[i];
    }
    
    int bucketCount = (max.compareTo(min) + 1);
    List<List<String>> buckets = new ArrayList<>(bucketCount);
    
    for (int i = 0; i < bucketCount; i++) {
      buckets.add(new ArrayList<>());
    }
    
    for (int i = 0; i < n; i++) {
      String str = arr[i];
      int bucketIndex = (str.compareTo(min));
      buckets.get(bucketIndex).add(str);
    }
    int index = 0;

    for (int i = 0; i < bucketCount; i++) {
      List<String> bucket = buckets.get(i);
      insertionSort(bucket, bucket.size());
      for (int j = 0; j < bucket.size(); j++) {
        arr[index++] = bucket.get(j);
      }
    }
  } 
}

//Estructuras de Datos y Algoritmos II