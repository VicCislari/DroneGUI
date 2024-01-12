/**
 * Represents a Drone with various attributes such as ID, type, creation date, serial number, carriage weight, and carriage type.
 * @author: Adizen
 * @version 1.0
 * @last_modified 2024.01.10
 */

import java.time.LocalDateTime;
public class Drone {
    private int id;
    private String drone_type;
    private LocalDateTime created;
    private String serial_number;
    private int carriage_weight;
    private String carriage_type;

    // Getter
    public int get_id(){
        return id;
    }
    public String get_drone_type(){
        return drone_type;
    }
    public LocalDateTime get_created(){
        return created;
    }
    public String get_serial_number(){
        return serial_number;
    }
    public int get_carriage_weight(){
        return carriage_weight;
    }
    public String get_carriage_type(){
        return carriage_type;
    }

    // Setter
    public void set_id(int id){
        this.id = id;
    }
    public void set_drone_type(String drone_type){
        this.drone_type = drone_type;
    }
    public void set_created(String created){
        this.created = LocalDateTime.parse(created);
    }
    public void set_serial_number(String serial_number){
        this.serial_number = serial_number;
    }
    public void set_carriage_weight(int carriage_weight){
        this.carriage_weight = carriage_weight;
    }
    public void set_carriage_type(String carriage_type){
        this.carriage_type = carriage_type;
    }
}
