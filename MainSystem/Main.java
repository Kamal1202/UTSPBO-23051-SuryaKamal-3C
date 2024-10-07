package MainSystem;
import InitialView.Greeting;//Pengimportan dari class Greeting project InitialView
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //Pembuatan object dari class Greeting dan Scanner
    private static Greeting gr = new Greeting();
    private static Scanner scanner = new Scanner(System.in);
    
    //Pendeklarasian array untuk penyimpanan data
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    
    //Mendeklarasikan variabel tipe kamar dengan tipe data Room yang merepresentasikan atribut dari class Room
    private static Room standardRoom;
    private static Room deluxeRoom;
    private static Room presidentialRoom;

    public static void main(String[] args) {
        gr.Greetings();
        setupRoomsAndUsers();

        boolean running = true; //Mendeklarasiskan variable running dengan nilai true dan akan di masukkan ke while loop
        while (running) {
            int roleChoice = promptUserRole();
            
            if (roleChoice == 1) {
                loginAsCustomer();
            } else if (roleChoice == 2) {
                loginAsAdmin();
            } else if (roleChoice == 3) {
                System.out.println("Terima kasih telah berkunjung ke Tepi Hotel.");
                running = false;//Jika nilai running berubah menjadi false maka looping while akan berakhir dan program akan selesai
            } else {
                System.out.println("Pilihan peran tidak valid.");
            }
        }
        
        scanner.close();
    }

    private static void setupRoomsAndUsers() {//Method untuk mengatur fasilitas dan harga room. Serta mengatur username dan password user
        standardRoom = new Room("Standard Room", "Single bed, TV, Wi-Fi", 10, 500000);
        deluxeRoom = new Room("Deluxe Room", "Double bed, TV, Wi-Fi, Mini fridge", 5, 1500000);
        presidentialRoom = new Room("Presidential Room", "King-size bed, TV, Wi-Fi, Private Pool, Personal Assistant", 3, 5000000);

        customers.add(new Customer("customer", "customer"));
        customers.add(new Customer("kamal", "kamal"));
        admins.add(new Admin("admin", "admin"));
    }

    private static int promptUserRole() {
        System.out.println("\nPilih peran untuk login:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("3. Keluar dari program");
        System.out.print("Input: ");
        return scanner.nextInt();
    }

    private static void loginAsCustomer() {//Method login untuk user customer
        scanner.nextLine(); //penggunaan nexLinet() setelah nextInt hukumnya wajib agar inputannya tidak terskip
        System.out.print("Masukkan username : ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password : ");
        String password = scanner.nextLine();
        
        //Pendeklarasian variabel curretCustomer dengan tipe data Customer yang akan menyimpan data dari class Customer
        //findCustomerByUsernameAndPassword(username, password) adalah sebuah method yang bertugas mencari username dan password yang sesuai dengan inputan user
        Customer currentCustomer = findCustomerByUsernameAndPassword(username, password);

        if (currentCustomer != null) {
            customerMenu(currentCustomer);//Jika username dan password yang dimasukkan sesuai dan ada di variabel customers maka sistem akan menampilkan customerMenu
        } else {
            System.out.println("Login gagal! Username atau password salah.");
        }
    }

    private static Customer findCustomerByUsernameAndPassword(String username, String password) {
        for (Customer customer : customers) {//Ini adalah looping dan pengkondisian yang mencocokkan username dan password yang ada di variabel customers
            if (customer.username.equals(username) && customer.password.equals(password)) {
                return customer;
            }
        }
        return null;
    }

    private static void customerMenu(Customer currentCustomer) {
        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("\nMenu Customer:");
            System.out.println("1. Pesan Kamar");
            System.out.println("2. Cek Ketersediaan Kamar");
            System.out.println("3. Lihat Detail Pesanan");
            System.out.println("4. Keluar");
            System.out.print("Input: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookRoomMenu(currentCustomer);
                    break;
                case 2:
                    checkRoomAvailability(currentCustomer);
                    break;
                case 3:
                    currentCustomer.viewOrderDetails();
                    break;
                case 4:
                    customerRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void bookRoomMenu(Customer customer) {
        System.out.println("\nPilih tipe kamar:\n1. Standard\n2. Deluxe\n3. Presidential");
        System.out.print("Input: ");
        int roomChoice = scanner.nextInt();
        System.out.println("\nMasukkan jumlah kamar yang ingin dipesan:");
        System.out.print("Input: ");
        int roomCount = scanner.nextInt();

        switch (roomChoice) {
            case 1:
                customer.bookRoom(standardRoom, roomCount);
                break;
            case 2:
                customer.bookRoom(deluxeRoom, roomCount);
                break;
            case 3:
                customer.bookRoom(presidentialRoom, roomCount);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void checkRoomAvailability(Customer customer) {
        System.out.println("\nPilih tipe kamar untuk dicek :\n1. Standard\n2. Deluxe\n3. Presidential");
        System.out.print("Input: ");
        int roomChoice = scanner.nextInt();

        switch (roomChoice) {
            case 1:
                customer.checkAvailability(standardRoom);
                break;
            case 2:
                customer.checkAvailability(deluxeRoom);
                break;
            case 3:
                customer.checkAvailability(presidentialRoom);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void loginAsAdmin() {
        scanner.nextLine(); // Consume newline
        System.out.print("Masukkan username : ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password : ");
        String password = scanner.nextLine();

        Admin currentAdmin = findAdminByUsernameAndPassword(username, password);//sama seperti yang di method loginAdCustomer

        if (currentAdmin != null) {
            adminMenu(currentAdmin);
        } else {
            System.out.println("Login gagal! Username atau password salah.");
        }
    }

    private static Admin findAdminByUsernameAndPassword(String username, String password) {
        for (Admin admin : admins) {
            if (admin.username.equals(username) && admin.password.equals(password)) {
                return admin;
            }
        }
        return null;
    }

    private static void adminMenu(Admin currentAdmin) {
        boolean running = true;
        while (running) {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Cek Ketersediaan Kamar");
            System.out.println("2. Lihat Semua Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Input : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkRoomAvailabilityAdmin(currentAdmin);
                    break;
                case 2:
                    currentAdmin.viewAllOrders(customers);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void checkRoomAvailabilityAdmin(Admin admin) {
        System.out.println("\nPilih tipe kamar untuk dicek :\n1. Standard\n2. Deluxe\n3. Presidential");
        System.out.print("Input: ");
        int roomChoice = scanner.nextInt();

        switch (roomChoice) {
            case 1:
                admin.checkAvailability(standardRoom);
                break;
            case 2:
                admin.checkAvailability(deluxeRoom);
                break;
            case 3:
                admin.checkAvailability(presidentialRoom);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
}