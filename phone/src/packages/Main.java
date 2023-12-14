package packages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        displayMenu();
    }


    public static void callContact (String name){
        String[] s = findNumber(name);
        if(s != null)
            System.out.println("calling " + name + " : " + s[1]);
        else
            System.out.println("person " + name + " not found");
    }



    public static void saveContact(String name, long number){
        System.out.println("saving contact "+ name +" : " +number);
                try {
                        File file= new File("file.txt");
                        if (!file.exists())
                            file.createNewFile();

                        PrintWriter pw = new PrintWriter(new FileWriter(file, true));
                        pw.println(name + " : " + number);
                        pw.close();

                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
    }

    public static  String[] findNumber(String name){
            try (Scanner in = new Scanner(new File("file.txt"))) {
                String[] s = new String[0];

                boolean foundPerson = false;

                while (in.hasNextLine()){
                    s= in.nextLine().split(" :");
                    if (s[0].equals(name)){
                        System.out.println("name " +name + " : "+ s[1]);
                        foundPerson=true;
                        break;
                    }
                }
                if(!foundPerson)
                    System.out.println("could not find "+ name);
                s= null;

                return  s;


            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        return  null;
    }
    public static void displayMenu(){


        try (Scanner in = new Scanner(System.in)) {
            String name;

            do {
                System.out.println("Enter your operation");
                System.out.println("1.call contact");
                System.out.println("2.Save contact");
                System.out.println("3.Find contact");


                int choice = in.nextInt();
                in.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\nWhom to call? (Enter the name");
                        name = in.nextLine();

                        callContact(name);
                        break;

                    case 2:
                        System.out.println("\nEnter the name to save");
                        name = in.nextLine();

                        System.out.println("\nEnter the number to save");
                        long number = in.nextLong();
                        in.nextLine();

                        saveContact(name, number);

                        break;

                    case 3:
                        System.out.println("Whom to find");
                        findNumber(in.nextLine());
                        break;

                    default:

                        break;
                }
                //        in.close();
                System.out.println("One more task? (Y/N)");
            } while (in.next().toLowerCase().charAt(0) == 'y');
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}