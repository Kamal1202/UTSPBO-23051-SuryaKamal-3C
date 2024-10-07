package MainSystem;

public class Room implements RoomDetails {
    private String amenities;
    private int roomCount;
    private double price;
    private String roomType;

    public Room(String roomType, String amenities, int roomCount, double price) {
        this.roomType = roomType;
        this.amenities = amenities;
        this.roomCount = roomCount;
        this.price = price;
    }
    
    @Override
    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    @Override
    public void setRoomCount(int count) {
        this.roomCount = count;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getAmenities() {
        return this.amenities;
    }

    @Override
    public int getRoomCount() {
        return this.roomCount;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public String getRoomType() {
        return this.roomType;
    }
    
    public void displayDetails() {
        System.out.println("\nTipe Kamar: " + getRoomType());
        System.out.println("Fasilitas: " + getAmenities());
        System.out.println("Jumlah Kamar Tersedia: " + getRoomCount());
        System.out.println("Harga per Malam: " + getPrice());
    }
    
    public boolean bookRoom(int count) {
        if (roomCount >= count) {//Pengkondisian yang akan memeriksa apakah jumlahKamar yang tersedia lebih dari jumlahKamar yang dipesan
            roomCount -= count;//Jika iya, maka pemesanan akan terjadi dan jumlahKamar asli akan dikurangi jumlahKamar yang dipesan
            System.out.println(count + " kamar bertipe " + getRoomType() + " berhasil dipesan.");
            return true;
        } else {
            System.out.println("Maaf, jumlah kamar yang tersedia tidak mencukupi.");
            return false;
        }
    }
    
    public int checkAvailability() {
        return roomCount;
    }
}