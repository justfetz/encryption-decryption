abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {

    double lengthA;
    double lengthB;
    double lengthC;


    public Triangle(double lengthA, double lengthB, double lengthC) {
        super();
        this.lengthA = lengthA;
        this.lengthB = lengthB;
        this.lengthC = lengthC;
    }
    public Triangle() {
        super();
    }

    public double getP(/*double lengthA, double lengthB, double lengthC*/) {
        return (this.lengthA + this.lengthB + this.lengthC) / 2;
    }

    @Override
    double getPerimeter() {
        return lengthA + lengthB + lengthC;
    }

    @Override
    double getArea() {
        return Math.sqrt(getP() * (getP() - lengthA) * (getP() - lengthB) * (getP() - lengthC));
    }
}

class Rectangle extends Shape {

    double shortSide;
    double longSide;

    public Rectangle(double shortSide, double longSide) {
        super();
        this.shortSide = shortSide;
        this.longSide = longSide;
    }

    public Rectangle() {
        super();
    }

    @Override
    double getPerimeter() {
        return (2 *shortSide) + (2 * longSide);
    }

    @Override
    double getArea() {
        return shortSide * longSide;
    }
}

class Circle extends Shape {

    double radius;

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    double getArea() {
        return Math.PI * this.radius * this.radius;
    }
}