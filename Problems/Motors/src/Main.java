import java.util.Scanner;

/* Product - Motor */
abstract class Motor {

    String model;
    long power;

    public Motor() {
        this.model = model;
        this.power = power;
    }



    @Override
    public String toString() {
        return "motor={model:" + model + ",power:" + power + "}";
    }
}

class PneumaticMotor extends Motor {
    // write your code here ..

    public PneumaticMotor(String model, long power) {
        super();

    }

 
    @Override
    public String toString() {
        return "Pneumatic " + super.toString();
    }
}

class HydraulicMotor extends Motor {
    // write your code here ...
        
    //private String model;
    //private long power;

    public HydraulicMotor(String model, long power) {
        super();
        //this.model = model;
        //this.power = power;
    }

    @Override
    public String toString() {
        return "Hydraulic " + super.toString();
    }
}

class ElectricMotor extends Motor {
    // write your code here ...
        
    //private String model;
    //private long power;

    public ElectricMotor(String model, long power) {
        super();
        //this.model = model;
        //this.power = power;
    }

    @Override
    public String toString() {
        return "Electric " + super.toString();
    }
}

class WarpDrive extends Motor {
    // write your code here ...   
    //private String model;
    //private long power;

    public WarpDrive(String model, long power) {
        super();
        //this.model = model;
        //this.power = power;
    }

    @Override
    public String toString() {
        return "WarpDrive " + super.toString();
    }
}

class MotorFactory {

    /**
     * It returns an initialized motor according to the specified type by the first character:
     * 'P' or 'p' - pneumatic, 'H' or 'h' - hydraulic, 'E' or 'e' - electric, 'W' or 'w' - warp.
     */
    public static Motor make(char type, String model, long power) {
        // write your code here ...
        if (Character.toLowerCase(type) == 'p') {
            return new PneumaticMotor(model, power);
        } else if (Character.toLowerCase(type) == 'e') {
            return new ElectricMotor(model, power);
        } else if (Character.toLowerCase(type) == 'h') {
            return new HydraulicMotor(model, power);
        } else if (Character.toLowerCase(type) == 'w') {
            return new WarpDrive(model, power);
        } else {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char type = scanner.next().charAt(0);     
        final String model = scanner.next();
        final long power = scanner.nextLong();
        // write your code here ...

        Motor motor = MotorFactory.make(type, model, power);
        motor.model = model;
        motor.power = power;
        //System.out.println(p.toString());
        scanner.close();
       System.out.println(motor);
    }
}
