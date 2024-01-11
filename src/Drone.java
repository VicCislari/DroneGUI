//author: Adizen

import java.time.ZonedDateTime;
public class Drone {
    private int id;
    private int drone_type;
    private ZonedDateTime created;
    private String serial_number;
    private int carriage_weight;
    private String carriage_type;

    // Getter
    public int get_id(){
        return id;
    }
    public int get_drone_type(){
        return drone_type;
    }
    public ZonedDateTime get_created(){
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
    public void set_drone_type(int drone_type){
        this.drone_type = drone_type;
    }
    public void set_created(String created){
        this.created = ZonedDateTime.parse(created);
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
