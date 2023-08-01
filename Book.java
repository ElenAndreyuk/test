import java.util.HashMap;
import java.util.Scanner;

public class Book {

    private String id;
    private String name;
    private String price;

    public Book(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Book(HashMap<String, String> book) {
        this.id = book.get("id");
        this.name = book.get("name");
        this.price = book.get("price");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }



    @Override
    public String toString() { return String.format("book [id= %s, name= %s, price= %s]",
            getId(), getName(), getPrice()); }

    private String readString(String messege){
        System.out.println(messege);
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        return res;
    }

    public Book(){
        this.id = readString("Введите id: ");
        this.name = readString("Введите название книги: ");
        this.price = readString("Введите цену");
    }
}
