package MainSystem;

public interface RoomDetails {
    //Mendeklarasikan method Fasilitas, JumlahKamar, dan Harga
    void setAmenities(String amenities);
    void setRoomCount(int count);
    void setPrice(double price);
    
    String getAmenities();
    int getRoomCount();
    double getPrice();
    
    void displayDetails();
}