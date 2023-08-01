import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class XMLDocToString
{
    public static void main(String[] args)
    {

        String xmlOutPut = readFromFile("bookstore.xml");

        System.out.println(xmlOutPut);


        List<Book> bookstore = ReadFile(xmlOutPut);

        bookstore.forEach(i-> System.out.println(i));
        bookstore.add(new Book());

        System.out.println("\n\n"+writeDown(bookstore));

        saveXml("bookstore.xml",writeDown(bookstore));
    }

    private static List<Book> ReadFile(String inputFile){
        List<Book> result = new ArrayList<>();
        String[] lines = inputFile.split("book");
        for (String line: lines) {
            if (line.contains("id")) {
                HashMap <String,String> filler = new HashMap<>();
                filler.put("price",line.substring(line.indexOf("ce>")+3, line.indexOf("</p")));
                filler.put("name", line.substring(line.indexOf("me>")+3, line.indexOf("</n")));
                filler.put("id",line.substring(line.indexOf('\"')+1,line.indexOf("\">")));
                result.add(new Book(filler));
            }
        }
        return result;
    }

    private static String readFromFile(String filename){
        String result = null;
        try {
            FileReader fr  = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            try {
                String line = reader.readLine();
                while (line!=null) {
                    result += line;
                    line = reader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static String writeDown(List<Book> inpList){
        StringBuilder protoFile = new StringBuilder();
        protoFile.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<bookstore>\n");
        inpList.forEach((i)->{
            HashMap<String, String> temp = BookToHashMap(i);
            protoFile.append(String.format("<book id = \"%s\">\n", i.getId()));
            temp.forEach((k,v)-> {
                if (!(k.equals("id")))
                    protoFile.append(String.format("<%s> %s </%s>\n", k, v, k));
            });
            protoFile.append("</book>\n");
        });
        protoFile.append("</bookstore>");
        return protoFile.toString();
    }

    private static HashMap<String, String> BookToHashMap (Book b){
        HashMap<String, String> result = new HashMap<>();
        result.put("id", b.getId());
        result.put("name", b.getName());
        result.put("price", b.getPrice());
        return result;
    }

    private static void saveXml (String fileName, String fileInput){
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(fileInput);
            fw.flush();
        } catch (IOException e) { throw new RuntimeException(e); }
    }

}