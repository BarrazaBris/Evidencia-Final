package Consultorio;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

public class CITAS {
    public static void main(String[] args){
        HashMap<String, String> mapaCon = new HashMap<String, String>();
        Scanner leer = new Scanner(System.in);

        int opc, ban = 0;
        String paciente, doctor;
        System.out.println("***Agende su cita***");
        do {
            try{
                System.out.println("Seleccione la opción que desee:");
                System.out.println("Cargar Datos --------------[1]");
                System.out.println("Guardar Datos -------------[2]");
                System.out.println("Mostrar Pacientes ---------[3]");
                System.out.println("Mostrar Doctores ----------[4]");
                System.out.println("Borrar Paciente -----------[5]");
                System.out.println("Salir ---------------------[6]");

                opc = leer.nextInt();
                switch (opc)
                {
                    case 1:
                        load(mapaCon);
                        break;

                    case 2:
                        save(mapaCon);
                        break;

                    case 3:
                        list(mapaCon);

                    case 4:
                        System.out.println("Ingrese el nombre del paciente:");
                        paciente = leer.next();
                        System.out.println("Ingrese la especialidad del doctor que requiere");
                        doctor = leer.next();
                        create(mapaCon,paciente,doctor);
                        break;

                    case 5:
                        System.out.println("Ingrese el nombre del paciente a eliminar:");
                        paciente = leer.next();

                        delete(mapaCon, paciente);
                        break;

                    case 6:
                        System.out.println("Saliendo\n");
                        ban = 1;
                        break;

                    default:
                        System.out.println("Opción Incorrecta\n");
                        break;
                }
            }
            catch(Exception e)
            {
                System.out.println("ERROR !!\n");
                break;
            }

        }while (ban == 0);
    }
    public static void list(HashMap<String, String>mapaCon)
    {
        Iterator<String> iterator = mapaCon.keySet().iterator();

        System.out.println("Datos:\n");
        System.out.println("Paciente\t|\tDoctor");
        System.out.println("--------------------");
        while(iterator.hasNext())
        {
            String llave = iterator.next();

            System.out.println(" "+llave+"\t|\t"+mapaCon.get(llave));
        }
    }

    public static void create(HashMap<String, String> mapaCon, String tel, String nom)
    {
        if(mapaCon.containsKey(tel))
        {
            System.out.println("\nError!\nNose puede registrar dos veces el mismo paciente\n");
        }
        else
        {
            mapaCon.put(tel, nom);
            System.out.print("\nPaciente Agregado");
        }
    }
    public static void delete (HashMap<String, String> mapaCon, String tel)
    {
        if (mapaCon.containsKey(tel))
        {
            System.out.println("\nPaciente Eliminado: "+mapaCon.get(tel)+"\n");
            mapaCon.remove(tel);
        }
        else
        System.out.println("\nEl paciente no existe\n");
    }
    public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C\\Users\\Briseida\\IdeaProjects\\Evidencia";
        String a [];
        BufferedReader bufferedReader = null ;

        try{
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) !=null){
                a = line.split(",");
                m.put(a[0],a[1]);
            }
        }catch(IOException e){
            System.out.println("IOException catched while reading: "+e.getMessage());
        }finally {
            try{
                if (bufferedReader !=null){
                    bufferedReader.close();
                    System.out.println("\nPaciente cargado");
                }
            }catch (IOException e) {
                System.out.println("IOException catched while closing: "+e.getMessage());
            }
        }
    }
    public static void save (HashMap<String, String> m)
    {
        Iterator<String> iterator = m.keySet().iterator();
        String inputFilename = "C\\\\Users\\\\Briseida\\\\IdeaProjects\\\\Evidencia";

        BufferedWriter bufferedWriter = null;

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));

            while(iterator.hasNext())
            {
                String llave = iterator.next();

                bufferedWriter.write(llave+","+m.get(llave)+"\n");
            }

        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: "+e.getMessage());
        }finally {
            try{
                if (bufferedWriter !=null){
                    bufferedWriter.close();
                    System.out.println("\nCambios Guardados");
                }
            }catch (IOException e) {
                System.out.println("IOException catched while closing: "+e.getMessage());
            }
        }

    }
}
