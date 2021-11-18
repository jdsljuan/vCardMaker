import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.Exception;

public class VCardMaker{

    public static void main(String[] args){
        if(!(args.length <= 2 && args.length >= 1)){
            System.out.println("LISTA DE ARGUMENTOS INVALIDOS!!!");
        }else{
            try{
                BufferedReader bfr = new BufferedReader(new FileReader(args[0]));
                if(bfr.ready()){
                    String line = bfr.readLine();
                    String finalFileString = "";
                    int count = 0;
                    while(line != null){
                        count++;
                        finalFileString += makeContact(line);
                        line = bfr.readLine();
                    }
                    if(args.length == 2){
                        makeFile(args[1], finalFileString);
                        System.out.println("CONTACTOS CREADOS: "+count +" EN ARCHIVO: "+args[1]+".vcf");
                    }else{
                        makeFile("Contacts", finalFileString);
                        System.out.println("CONTACTOS CREADOS: "+count +" EN ARCHIVO: Contacts.vcf");
                    }
                }
                bfr.close();
            }catch(Exception e){
                System.out.println("NO SE ENCUENTRA EL ARCHIVO!");
            }
            
        }
    }

    /**
     * Data orden Nombre, Primer Apellido, Segundo Apellido, Telefono, Genero, Grupo, Edad
    */
    public static String makeContact(String line){
        String[] dataSplit = line.replace("\"", "").split(",");
        String[] vCard = {"BEGIN:VCARD\n","VERSION:4.0\n","FN:","TEL;TYPE=home:tel:","GENDER:", "ORG:","END:VCARD\n"};
        vCard[2] += dataSplit[0]+" "+dataSplit[1]+" "+dataSplit[2]+"\n";//Nombres
        vCard[3] += dataSplit[3]+"\n";//Telefono
        vCard[4] += dataSplit[4]+"\n";//Genero
        vCard[5] += dataSplit[5]+"\n";//Grupo
        //vCard[6] += dataSplit[6]+"\n";//Titulo - Edad

        String finalVCard = "";
        for (String string : vCard) {
            finalVCard += string;
        }
        return finalVCard;

    }

    public static void makeFile(String fileName, String source){
        try{
            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName+".vcf"));
            bfw.write(source);
            bfw.close();
        }catch(Exception e){
            System.err.print(e);
        }
    }

}