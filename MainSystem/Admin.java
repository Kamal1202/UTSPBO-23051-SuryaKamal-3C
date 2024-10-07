package MainSystem;

import java.util.ArrayList;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void checkAvailability(Room room) {
        room.displayDetails();
    }

    @Override
    public void viewOrderDetails() {
        //Implementasi ini kosong karena tujuan dibuatnya hanya untuk memenuhi syarat ekstends dari class absract user
    }
    
    public void viewAllOrders(ArrayList<Customer> customers) {
        for (Customer customer : customers) {//Menampilkan pesanan dari customer yang diambil dari class customer
            customer.viewOrderDetails();
        }
    }
}