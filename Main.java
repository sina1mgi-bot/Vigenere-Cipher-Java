import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner cifrado = new Scanner(System.in);
        String mensaje = " ";
        String llave = " ";
        int opcion;

        System.out.println("- CIFRADO VIGENERE -");
        do {
            do{
                System.out.println("¿QUÉ ACCIÓN DESEA REALIZAR?\n( 0 ) SALIR\n( 1 ) CIFRAR\n( 2 ) DESCIFRAR");
                opcion = leerNumeroEntero(cifrado, "ELIGE UNA OPCIÓN: "); //LLAMAMOS AL METODO LEERNUMEROE PARA VALIDAR LA ENTRADA
                cifrado.nextLine(); //LIMPIAR SIG ENTRADA
                if (opcion < 0 || opcion > 2) { //SI LA OPCIÓN INGRESADA POR EL USUARIO ESTÁ FUERA DEL RANGO DE 0 A 2, SE MUESTRA EL SIG MENSAJE
                    System.out.println("¡ OPCIÓN NO ENCONTRADA !\nINGRESE UNA OPCIÓN VALIDA. (0, 1 o 2) ");
                }
            } while (opcion < 0 || opcion > 2); //SE REPITE SI ESTPA FUERA DEL TANGO, HASTA QUE INGRESE UNA OPCION VALIDA
            switch (opcion){
                case 1:
                    System.out.println("- CIFRAR -\nINGRESA TU MENSAJE:");
                    mensaje = cifrado.nextLine();

                    System.out.println("INGRESA TU LLAVE: ");
                    llave = cifrado.nextLine();

                    String mensajeCifrado = cifrarMensajeVigenere(mensaje, llave); //LLAMAMOS AL METODO DE CIFRAR MENSAJE PARA CIFRAR CON LO INGRESADO POR EL USUARIO
                    System.out.println("TU MENSAJE CIFRADO ES: " + mensajeCifrado); //IMPRIMIMOS EL RESULTADO DE AQUEL RESULTADO
                    break;
                case 2:
                    System.out.println("- DESCIFRAR -");
                    System.out.println("- INGRESA TU MENSAJE CIFRADO:");
                    mensaje = cifrado.nextLine();
                    System.out.println("INGRESA TU LLAVE: ");
                    llave = cifrado.nextLine();
                    String mensajeDescifrado = descifrarMensajeVigenere(mensaje, llave); //LLAMAMOS AL METODO DE DESCIFRADO PARA DESCIFRAR CON LO INGRESADO POR EL USUARIO
                    System.out.println("TU MENSAJE DESCIFRADO ES: " + mensajeDescifrado); //IMPRIMIMOS EL RESULTADOD AL USAR EL METODO
                    break;
                case 0:
                    System.out.println("GRACIAS POR VISITAR EL PROGRAMA ! :)");
                    System.exit(0); //SE CIERRA EL PROGRAMA
                default:
                    System.out.println("ERROR ! ");
                    break;
            }
            do{
                System.out.println("¿DESEA REALIZAR OTRA ACCIÓN?\n( 1 ) SI\n( 2 ) NO"); //AL TERMINAR UN CASO SE LE PREGUNTA SI QUIERE REALIZAR OTRA ACCION
                opcion = leerNumeroEntero(cifrado, "ELIGE UNA OPCIÓN: "); //LLAMAMOS AL METODO LEERNUMEROE PARA VALIDAR LA ENTRADA
                cifrado.nextLine(); //LIMPIAR SIG ENTRADA
                if (opcion < 1 || opcion > 2) { //SI EL VALOR ESTÁ FUERA DEL RANGO DE 1 Y 2, SE MUESTRA EL MENSAJE
                    System.out.println("¡ OPCIÓN NO ENCONTRADA !\nINGRESE UNA OPCIÓN VALIDA. (1 o 2) ");
                }
            } while (opcion < 1 || opcion > 2); //SE REPITE CADA QUE ESTÁ FUERA DEL RANGO
            if (opcion == 2){ //SI EL USUARIO INGRESA EL 2, ELIGIENDO NO HACER OTRA ACCION SE TERMINAR EL PROGRAMA
                System.out.println("GRACIAS POR VISITAR EL PROGRAMA ! :)");
                System.exit(0); //SE CIERRA
            }
        } while (opcion == 1);
    }
    public static int leerNumeroEntero(Scanner scanner, String mensajeMostrar) {
        //VALIDANDO ENTRADA POR ENTRADA DE DATOS
        int numero = 0;
        boolean entradaValida = false; //DEFINIR ENTRADA FALSE
        while (!entradaValida) { //SI LA ENTRADA ES INVALIDA SE ESJECUTA HASTA QUE EL VALOR LLEGUE A SER UN NUMERO ENTERO
            System.out.print(mensajeMostrar); //SE IMPRIME EL SIGUIENTE MENSAJE, PARA CONTINUAR CON LA ENTRADA DE DATOS
            if (scanner.hasNextInt()){ //SI LA ENTRADA DE DATOS ES DE NUMEROS ENTEROS
                numero = scanner.nextInt(); //LEE LA ENTRADA Y ALMACENA EL VALOR INGRESADO
                entradaValida = true; //SE VALIDA LA ENTRADA SI ES UN ENTERO PARA SEGUIR SOLICITANDO LA PROX ENTRADA Y TERMINA HASTA
                //QUE CUMPLA CON LAS ENTRADAS REQUERIDAS
            } else{
                System.out.println("- ERROR, LA OPCIÓN DEBE DE SER UN NUMERO ENTERO PARA VALIDARLA. INTENTELO DE NUEVO. -");
                scanner.next(); //UN SALTO PARA LIMPIAR LA PROXIMA ENTRADA DE DATOS,
            }
        }
        return numero;
    }
    //VERIFICAR CARACTERES MAYUSCULAS
    public static boolean esMayuscula(char caracter) { //RETORNA TRUE SI EL CARACTER ES MAYUSCULA
        return caracter >= 'A' && caracter <= 'Z' || caracter == 'Ñ'; //COMPROBAMOS SI EL CARACTER ESTÁ DENTRO DEL RANGO DEL ALFABETO EN MAYUSCULAS, "AÑADIENDO" LA Ñ
    }
    //VERIFICAR CARACTERES MINUSCULAS
    public static boolean esMinuscula(char caracter) {//RETORNA TRUE SI EL CARACTER ES MINUSCULA
        return caracter >= 'a' && caracter <= 'z' || caracter == 'ñ'; //COMPROBAMOS SI EL CARACTER ESTÁ DENTRO DEL RANGO DEL ALFABETO EN MINUSCULAS, "AÑADIENDO" LA ñ
    }
    //BUSCAR LA POSICION DE LOS CARACTERES DEL MENSAJE EN EL ALFAFETO MINUS O MAYUS
    public static int obtenerPosicion(char caracter, char[] alfabetoMayus, char[] alfabetoMinus) { //BUSCAMOS LA POSICION DE UN CARACTER EN LOS ALFABETOS QUE TENEMOS (MAYUS Y MINUS)
        for (int i = 0; i < alfabetoMayus.length; i++) { //EL CARACTER RECORRE CADA ALFABETO PARA BUSCAR "SU POSICION"
            if (caracter == alfabetoMayus[i] || caracter == alfabetoMinus[i]) {
                return i; //SE RETORNA SU POSICION EN CASO DE ENCONTRARLO EN ALUN ALFABETO
            }
        }
        return -1; // RETORNA EN -1 SI NO SE ENCUENTRA UN CARACTER (CASO CUANDO NO SON DEL ALFAETO, *()/%&1234, ETC)
    }
    //CIFRAR EL MENSAJE
    public static String cifrarMensajeVigenere (String mensaje, String llave){
        //CONSIDERAMOS EL ALFABETO EN MAYUS Y MINUS, QUE INCLUYEN LA Ñ
        char alfabetoMayus[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char alfabetoMinus[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        StringBuilder mensajeCifrado = new StringBuilder(); //SE IRÁ GUARDANDO EL MENSAJE CIFRADO MIENTRAS SE VAYA CIFRANDO
        int longitudLlave = llave.length(); //SE GUARDA EL TAMAÑO DE LA LALVE PARA USARLA DESPUÉS
        int indiceLlave = 0; //INICIAMOS EL INDICE DE LA LLAVE EN 0, PARA IRLO "ACTUALIZANDO"

        //RECORRE CADA CARACTER DEL MENSAJE PARA CIFRAR
        for (int i = 0; i < mensaje.length(); i++) { //SE RECORRE CADA CARACTER DEL MENSAJE
            char caracterMensaje = mensaje.charAt(i); //EL BUCLE TERMINA CUANDO SE HAYA RECORRIDO TODOS LOS CARACTERES DEL MENSAJE


            //SE SELECCIONA EL CARACTER CORRESPONDIENTE DE LA LLAVE QUE SE USARA PARA CIFRAR EL MENSAJE
            char caracterLlave = llave.charAt(indiceLlave % longitudLlave);
            //ESTE PERMITE QUE LA LALVE PUEDA REPETIRSE HASTA CUMPLIR CON EL TAMAÑO DEL MENSAJE

            //TENEMOS COMO DESPLAZAMIENTO AQUELLAS POSICIONES DE LOS CARACTERES DE LA LLAVE
            int desplazamiento = obtenerPosicion(caracterLlave, alfabetoMayus, alfabetoMinus);

            // VERIFICAR SI EL CARACTER ES MAYUSCULA LLAMANDO AL METODO
            if (esMayuscula(caracterMensaje)) {
                int posicionOriginal = obtenerPosicion(caracterMensaje, alfabetoMayus, alfabetoMinus); //LLAMAMOS AL METODO
                //PARA SACAR LA POSICION DE LOS CARACTERES DEL MENSAJE, DENTRO DEL ARREGLO DE MAYUS
                char caracterCifrado = alfabetoMayus[(posicionOriginal + desplazamiento) % alfabetoMayus.length]; //SE HACE LA SUMA DE LA POSICION ORIGILA DE LOS CARACTERES DEL MENSAJE
                //MAS LOS DE LA LLAVE, A ESO SE LE SACA EL MODUALAR DE ACUERDO AL NUMERO DE CARACTERES DEL ALFABETO EN EL QUE SE "ENCUENTRAN"
                mensajeCifrado.append(caracterCifrado); //SE LE AGREGAR EL CARACTER ENCONTRADO AL MENSAJE CIFRADO
                indiceLlave++; //"AVANZAMOS" AL SIGUEINTE CARACTER DE LA LLAVE
            }
            // VERIFICAR SI EL CARACTER ES MINUSCULA LLAMANDO AL METODO
            else if (esMinuscula(caracterMensaje)) {
                int posicionOriginal = obtenerPosicion(caracterMensaje, alfabetoMayus, alfabetoMinus); //LLAMAMOS AL METODO PARA OBTENER LA POSICIONDE LOS CARACTERES
                //DEL MENSAJE, DENTRO DE LOS ARREGLOS DE ALFABETOS
                char caracterCifrado = alfabetoMinus[(posicionOriginal + desplazamiento) % alfabetoMinus.length]; //SE HACE LA SUMA DE LOS INDICES DE LOS CARACTERES DEL MENSAJE MAS
                // EL DESPLAZAMIENTO QUE ES EL INDICE DEL CARACTER DE LA LLAVE, PARA DESPUES SACARLE EL MODULO Y ONTENER EL NUEVO CARACTER DEL MENSAJE CIFRADO
                mensajeCifrado.append(caracterCifrado); //ESE CARACTER SE GUARDA EN NUESTRO MENSAJE CIFRADO
                indiceLlave++; //"AVANZAMOS" AL SIGUIENTE CARACTER DE LA LLAVE
            }
            // SI NO ES DEL ALFABETO SE GUARDA SIN HACER CAMBIO ALGUNO, Y EN LA POSICION QUE SE ENCUENTRE
            else {
                mensajeCifrado.append(caracterMensaje);
            }
        }
        return mensajeCifrado.toString(); //CUANDO SE TERMINA CON TODOS LOS CARACTERES DEL MENSAJE, SE RETORNA TODOS LOS CARACTERES CIFRADOS A STRING
    }
    //PARA DESCIFRAR EL MENSAJE ES EXACTAMENTE EL MISMO PROCEDIMIENTO, SOLAMENTE QUE AHORA LA POSICION ORIGINAL (DEL MENSAJE A DESCIFRAR),
    // SE LE RESTARÁ EL DESPLAZAMIENTO (POSICION DE CARACTER DE LA LLAVE) Y SUMAR LA CANT DE CARACTERES QUE HAY EN EL ARREGLO, PARA EVITAR AQUELLOS RESULTADOS NEGATIVOS Y NOS
    //DE LA POSICION COMO CORRESPONDE Y PODER TERMINAR CON EL DESCIFRADO
    public static String descifrarMensajeVigenere(String mensajeCifrado, String llave) {
        char alfabetoMayus[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char alfabetoMinus[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        StringBuilder mensajeDescifrado = new StringBuilder();
        int longitudLlave = llave.length(); //TENEMOS UNA LONGITUD DE LA LLAVE
        int indiceLlave = 0; //INICIAMOS EL INDICE DE LA LLAVE EN CERO

        for (int i = 0; i < mensajeCifrado.length(); i++) { //EMPEZAMOS EL CICLO PARA CIFRAR CADA CARACTER DEL MENSAJE
            char caracterMensaje = mensajeCifrado.charAt(i);
            char caracterLlave = llave.charAt(indiceLlave % longitudLlave);
            int desplazamiento = obtenerPosicion(caracterLlave, alfabetoMayus, alfabetoMinus);

            if (esMayuscula(caracterMensaje)) {
                int posicionOriginal = obtenerPosicion(caracterMensaje, alfabetoMayus, alfabetoMinus);
                char caracterDescifrado = alfabetoMayus[(posicionOriginal - desplazamiento + alfabetoMayus.length) % alfabetoMayus.length];
                mensajeDescifrado.append(caracterDescifrado);
                indiceLlave++;
            } else if (esMinuscula(caracterMensaje)) {
                int posicionOriginal = obtenerPosicion(caracterMensaje, alfabetoMayus, alfabetoMinus);
                char caracterDescifrado = alfabetoMinus[(posicionOriginal - desplazamiento + alfabetoMinus.length) % alfabetoMinus.length];
                mensajeDescifrado.append(caracterDescifrado);
                indiceLlave++;
            } else {
                mensajeDescifrado.append(caracterMensaje);
            }
        }
        return mensajeDescifrado.toString();
    }
}