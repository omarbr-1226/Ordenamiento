import java.util.Scanner;
public class Ordenacion {
    static Scanner sc = new Scanner(System.in);
    static int T;
    static People [] register;
    static char resp= 's';
    public static void main(String[] args) {
        System.out.println("Write the size of the register:");
        T = sc.nextInt();
        register = new People[T];
        do {
            MenuRegister();
        }while (resp=='s');

    }
    public static void MenuRegister(){
        do {
            System.out.println("Selection option (1 - 3) \n 1.- Fill out array \n 2.- Selection\n 3.-Exit ");
            int option = sc.nextInt();

            switch (option){
                case 1:
                    FillOutArray();
                    //PrintRegister();
                    break;
                case 2:
                    ChoseField();
                    //Ordenar, se mandara a llamar otros metodos seleccionar el tipo de campo
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Option wrong, try again!");
            }
        }while (resp != 's');

    }

    public static void ChoseField(){ //   elegir campo
        System.out.println("Enter the field that you would like to order\n1.-ID\n2.-Name\n3.-Age");
        int option=sc.nextInt();

        switch (option){
            case 1:
                OrderById();
                break;
            case 2:
                OrderByName();
                break;
            case 3:
                OrderByAge();
                break;
        }
    }

    //Ordenar por ID
    public static  void OrderById() {
        System.out.println("Choose the method to order the register:\n1.-Radix Sort\n2.-Buble Sort");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                OrderByRadixID();
                break;
            case 2:
                OrderByBubbleID(T,register);
                break;
            default:
                System.out.println("Option wrong, Try Again!");

        }
    }

    public static void OrderByRadixID(){
        System.out.println("You have selected Radix ID");
        System.out.println("Register without order");
        for (int i = 0; i <T; i++) {
            System.out.println(register[i]);
        }
        radixSortID(T,register);
        System.out.println("Register with order");
        for (int i = 0; i <T; i++) {
            System.out.println(register[i]);
        }
        System.out.println("Back to main menu?");
        resp=sc.next().charAt(0);
    }
    public static void radixSortID(int t, People[] vector) {
        // Encuentra el número máximo para saber el número de dígitos
        int max = encontrarMaxId(t,vector);
        // Aplica Counting Sort para cada dígito
        for (int dígito = 1; max / dígito > 0; dígito *= 10) {
            countingSortId(t, vector, dígito);
        }
    }
    private static int encontrarMaxId(int t,People[] vector) {
        int max = vector[0].getID();
        for(int i = 1; i < t; i++){
            if(vector[i].getID() > max){
                max = vector[i].getID();
            }
        }
        return max;
    }
    private static void countingSortId(int t, People[] vector, int dígito) {
        People[] salida = new People[t]; // array de salida
        int[] conteo = new int[10]; // array de conteo para dígitos 0-9
        // Almacena el conteo de ocurrencias de cada dígito
        for (int i = 0; i < t; i++) {
            int dígitoActual = (vector[i].getID() / dígito) % 10;
            conteo[dígitoActual]++;
        }
        // Cambia conteo[i] para que conteo[i] contenga la posición
        // final de este dígito en salida
        for(int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }
        // Construye el array de salida
        for(int i = t - 1; i >= 0; i--){
            int dígitoActual = (vector[i].getID() / dígito) % 10;
            salida[conteo[dígitoActual] - 1] = vector[i];
            conteo[dígitoActual]--;
        }
        // Copia el array de salida en el array original para la siguiente iteración
        for(int i = 0; i < t; i++) {
            vector[i] = salida[i];
        }
    }

    public static void OrderByBubbleID(int T,People[]registro){
            boolean intercambiado;
            System.out.println("You have selected Bubble ID");
            System.out.println("Arrays without order");
            for (int i = 0; i < T; i++) {
                System.out.println(registro[i]);
            }
            for (int i = 0; i < T; i++) {
                intercambiado = false;
                for(int j = 0; j < T - i - 1; j++){
                    if(registro[j].getID() > registro[j + 1].getID()){
                        // Intercambia los elementos si están en el orden incorrecto
                        People temp = registro[j];
                        registro[j] = registro[j + 1];
                        registro[j+1]=temp;
                        intercambiado = true;
                    }
                }
                // Si no hubo intercambio en la pasada, la lista ya está ordenada
                if(!intercambiado){
                    break;
                }
            }
            System.out.println("Arrays with order");
            for (int i = 0; i < T; i++) {
                System.out.println(registro[i]);
            }
            System.out.println("Back to main menu?");
            resp=sc.next().charAt(0);
    }

    // Ordenar por Nombre
    public static void OrderByName(){
        System.out.println("Choose the method to order the register:\n1.-Radix Sort\n2.-Buble Sort");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                OrderByRadixName();
                break;
            case 2:
                OrderByBubbleName(T,register);
                break;
            default:
                System.out.println("Option wrong, Try Again!");

        }
    }

    public static void OrderByRadixName(){
        System.out.println("You have selected Radix Name");
        System.out.println("Register without order");
        for (int i = 0; i <T; i++) {
            System.out.println(register[i]);
        }
        radixSortName(T,register);
        System.out.println("Register with order");
        for (int i = 0; i <T; i++) {
            System.out.println(register[i]);
        }
        System.out.println("Back to main menu?");
        resp=sc.next().charAt(0);
    }
    public static void radixSortName(int t, People[] vector) {
        // Encuentra el número máximo para saber el número de dígitos
        int max = encontrarMaxName(t,vector);
        // Aplica Counting Sort para cada dígito
        for (int dígito = 1; max / dígito > 0; dígito *= 10) {
            countingSortName(t, vector, dígito);
        }
    }
    private static int encontrarMaxName(int t,People[] vector) {
        int max = vector[0].getName().length();
        for(int i = 1; i < t; i++){
            int len1=vector[i].getName().length();
            if(len1 > max){
                max = vector[i].getName().length();
            }
        }
        return max;
    }
    private static void countingSortName(int t, People[] vector, int dígito) {
        People[] salida = new People[t]; // array de salida
        int[] conteo = new int[10]; // array de conteo para dígitos 0-9
        // Almacena el conteo de ocurrencias de cada dígito
        for (int i = 0; i < t; i++) {
            int dígitoActual = (vector[i].getName().length() / dígito) % 10;
            conteo[dígitoActual]++;
        }
        // Cambia conteo[i] para que conteo[i] contenga la posición
        // final de este dígito en salida
        for(int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }
        // Construye el array de salida
        for(int i = t - 1; i >= 0; i--){
            int dígitoActual = (vector[i].getName().length() / dígito) % 10;
            salida[conteo[dígitoActual] - 1] = vector[i];
            conteo[dígitoActual]--;
        }
        // Copia el array de salida en el array original para la siguiente iteración
        for(int i = 0; i < t; i++) {
            vector[i] = salida[i];
        }
    }

    public static void OrderByBubbleName(int T,People[] registro){
        boolean intercambiado;
        System.out.println("You have selected Bubble Name");
        System.out.println("Arrays without order");
        for (int i = 0; i < T; i++) {
            System.out.println(registro[i]);
        }
        for (int i = 0; i < T; i++) {
            intercambiado = false;
            for(int j = 0; j < T - i - 1; j++){
                int len1=registro[j].getName().length();
                int len2=registro[j+1].getName().length();
                if(len1 > len2){
                    // Intercambia los elementos si están en el orden incorrecto
                    People temp = registro[j];
                    registro[j] = registro[j + 1];
                    registro[j+1]=temp;
                    intercambiado = true;
                }
            }
            // Si no hubo intercambio en la pasada, la lista ya está ordenada
            if(!intercambiado){
                break;
            }
        }
        System.out.println("Arrays with order");
        for (int i = 0; i < T; i++) {
            System.out.println(registro[i]);
        }
        System.out.println("Back to main menu?");
        resp=sc.next().charAt(0);
    }

    //Ordenar por Edad
    public static void OrderByAge() {
        System.out.println("Choose the method to order the register:\n1.-Radix Sort\n2.-Buble Sort");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                OrderByRadixAge();
                break;
            case 2:
                OrderByBubbleAge(T,register);
                break;
            default:
                System.out.println("Option wrong, Try Again!");
        }
    }

    public static void OrderByRadixAge(){
        System.out.println("You have selected Radix Age");
        System.out.println("Register without order");
        for (int i = 0; i <T; i++) {
            System.out.println(register[i]);
        }
        radixSortAge(T,register);
        System.out.println("Register with order");
        for (int i = 0; i <T; i++) {
            System.out.println(register[i]);
        }
        System.out.println("Back to main menu?");
        resp=sc.next().charAt(0);
    }

    public static void radixSortAge(int t, People[] vector) {
        // Encuentra el número máximo para saber el número de dígitos
        int max = encontrarMaxAge(t,vector);
        // Aplica Counting Sort para cada dígito
        for (int dígito = 1; max / dígito > 0; dígito *= 10) {
            countingSortAge(t, vector, dígito);
        }
    }
    private static int encontrarMaxAge(int t,People[] vector) {
        int max = vector[0].getAge();
        for(int i = 1; i < t; i++){
            if(vector[i].getAge() > max){
                max = vector[i].getAge();
            }
        }
        return max;
    }
    private static void countingSortAge(int t, People[] vector, int dígito) {
        People[] salida = new People[t]; // array de salida
        int[] conteo = new int[10]; // array de conteo para dígitos 0-9
        // Almacena el conteo de ocurrencias de cada dígito
        for (int i = 0; i < t; i++) {
            int dígitoActual = (vector[i].getAge() / dígito) % 10;
            conteo[dígitoActual]++;
        }
        // Cambia conteo[i] para que conteo[i] contenga la posición
        // final de este dígito en salida
        for(int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }
        // Construye el array de salida
        for(int i = t - 1; i >= 0; i--){
            int dígitoActual = (vector[i].getAge() / dígito) % 10;
            salida[conteo[dígitoActual] - 1] = vector[i];
            conteo[dígitoActual]--;
        }
        // Copia el array de salida en el array original para la siguiente iteración
        for(int i = 0; i < t; i++) {
            vector[i] = salida[i];
        }
    }

    public static void OrderByBubbleAge(int T,People[] registro){
        boolean intercambiado;
        System.out.println("You have selected Bubble Age");
        System.out.println("Arrays without order");
        for (int i = 0; i < T; i++) {
            System.out.println(registro[i]);
        }
        for (int i = 0; i < T; i++) {
                intercambiado = false;
                for(int j = 0; j < T - i - 1; j++){
                    if(registro[j].getAge() > registro[j + 1].getAge()){
                        // Intercambia los elementos si están en el orden incorrecto
                        People temp = registro[j];
                        registro[j] = registro[j + 1];
                        registro[j+1]=temp;
                        intercambiado = true;
                    }
                }
                // Si no hubo intercambio en la pasada, la lista ya está ordenada
                if(!intercambiado){
                    break;
                }
        }
        System.out.println("Arrays with order");
        for (int i = 0; i < T; i++) {
            System.out.println(registro[i]);
        }
        System.out.println("Back to main menu?");
        resp=sc.next().charAt(0);
    }


    public static void FillOutArray(){
        for (int i = 0; i <T ; i++) {
            System.out.println("REGISTER NUMBER ["+(i+1)+"]");
            System.out.println("Write your ID:");
           int id=sc.nextInt();
            System.out.println("Write your name:");
           String name=sc.next();
            System.out.println("Write your old");
           int old= sc.nextInt();
            register[i]= new People(id,name,old);
        }
        System.out.println("Back to main menu?");
        resp=sc.next().charAt(0);
    }
    /*public static void PrintRegister(){
        for (int i = 0; i <T ; i++) {
            System.out.println(register[i]);
        }
    }*/

    //METODO RADIX
    public static void radixSort(int t, int[] vector) {
        // Encuentra el número máximo para saber el número de dígitos
        int max = encontrarMax(t,vector);
        // Aplica Counting Sort para cada dígito
        for (int dígito = 1; max / dígito > 0; dígito *= 10) {
            countingSort(t, vector, dígito);
        }
    }
    private static int encontrarMax(int t, int[] vector) {
        int max = vector[0];
        for(int i = 1; i < t; i++){
            if(vector[i] > max){
                max = vector[i];
            }
        }
        return max;
    }
    private static void countingSort(int t, int[] vector, int dígito) {
        int[] salida = new int[t]; // array de salida
        int[] conteo = new int[10]; // array de conteo para dígitos 0-9
        // Almacena el conteo de ocurrencias de cada dígito
        for (int i = 0; i < t; i++) {
            int dígitoActual = (vector[i] / dígito) % 10;
            conteo[dígitoActual]++;
        }
        // Cambia conteo[i] para que conteo[i] contenga la posición
        // final de este dígito en salida
        for(int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }
        // Construye el array de salida
        for(int i = t - 1; i >= 0; i--){
            int dígitoActual = (vector[i] / dígito) % 10;
            salida[conteo[dígitoActual] - 1] = vector[i];
            conteo[dígitoActual]--;
        }
        // Copia el array de salida en el array original para la siguiente iteración
        for(int i = 0; i < t; i++) {
            vector[i] = salida[i];
        }
    }

    // METODO BURBUJA MEJORADO
    public static void bubbleSortMejorado(int t, int[] vector) {
        int i, j;
        boolean intercambiado;

        for(i = 0; i < t - 1; i++){
            intercambiado = false;
            for(j = 0; j < t - i - 1; j++){
                if(vector[j] > vector[j + 1]){
                    // Intercambia los elementos si están en el orden incorrecto
                    int temp = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = temp;
                    intercambiado = true;
                }
            }
            // Si no hubo intercambio en la pasada, la lista ya está ordenada
            if(!intercambiado){
                break;
            }
        }
    }
     static class People{
        private int ID;
        private String Name;
        private int Age;

        public People(){
        }

        public People(int ID, String Name, int Age){
            this.ID = ID;
            this.Name = Name;
            this.Age = Age;
        }

         public int getAge() {
             return Age;
         }

         public void setAge(int age) {
             Age = age;
         }

         public int getID() {
             return ID;
         }

         public void setID(int ID) {
             this.ID = ID;
         }

         public String getName() {
             return Name;
         }
         public void setName(String Name) {
            this.Name = Name;
         }

         public String toString(){
            return "[ID: "+ID+", Nombre:"+Name+", Edad:"+Age+"]";
        }
    }
}