class Vehicle {
    String make, model;
    int year;

    Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    String getMake() {
        return make;
    }

    void setMake(String make) {
        this.make = make;
    }

    String getModel() {
        return model;
    }

    void setModel(String model) {
        this.model = model;
    }

    int getYear() {
        return year;
    }

    void setYear(int year) {
        this.year = year;
    }

    String detailsDisplay() {
        return "Make: " + make + "\n" +
                "Model: " + model + "\n" +
                "Year: " + year;
    }
}

class Car extends Vehicle {

    int numDoors;
    boolean isElectric;

    Car(int numDoors, boolean isElectric, String make, String model, int year) {
        super(make, model, year);
        this.numDoors = numDoors;
        this.isElectric = isElectric;
    }

    int getNumDoors() {
        return numDoors;
    }

    void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    boolean getIsElectric() {
        return isElectric;
    }

    void setIsElectric(boolean isElectric) {
        this.isElectric = isElectric;
    }

    @Override
    public String detailsDisplay() {
        return super.detailsDisplay() + "\n" +
                "Number of Doors: " + "\n" +
                "Electric?: " + isElectric;
    }
}
