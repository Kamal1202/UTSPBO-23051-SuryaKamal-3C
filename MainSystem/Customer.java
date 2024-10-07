package MainSystem;
import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<String> orders; //Pendeklarasian variable orders dengan tipe data Array String

    public Customer(String username, String password) {//Konstruktor class Customer yang menampung username, password, dan orders
        super(username, password);
        this.orders = new ArrayList<>();
    }

    public void bookRoom(Room room, int count) {
        String formattedPrice = String.format("%,.0f", room.getPrice() * count);//Baris ini berguna untuk menambahkan angka dibelakang koma
        
        if (room.bookRoom(count)) {
            orders.add("\nTipe Kamar : " + room.getRoomType() + "\nFasilitas : " + room.getAmenities() + "\nHarga per Malam: " + room.getPrice() + "\nJumlah Kamar yang dipesan : " + count + "\nTotal Harga : " + formattedPrice);
        }//mengisi variable order dengan String
    }

    @Override
    public void checkAvailability(Room room) {
        room.displayDetails();
    }

    @Override
    public void viewOrderDetails() {
        System.out.println("Detail pesanan : ");
        for (String order : orders) {//For loop untuk menampilkan beberapa pesanan dari customer
            System.out.println(order);
        }
    }
}