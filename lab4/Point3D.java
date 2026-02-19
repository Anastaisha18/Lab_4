package lab4;

public class Point3D extends Point2D {
    private double z;

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() { return z; }

    @Override
    public String toString() {
        return "{" + x + ";" + y + ";" + z + "}";
    }

    // Переопределяем equals для 3D точек
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        Point3D point = (Point3D) obj;
        return Double.compare(point.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Double.hashCode(z);
    }
}