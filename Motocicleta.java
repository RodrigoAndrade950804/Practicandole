public class Motocicleta {

    int codigo;
    String marca;
    int cilindraje;
    String color;
    float precio;

    public Motocicleta(int codigo, String marca, int cilindraje, String color, float precio) {
        this.codigo = codigo;
        this.marca = marca;
        this.cilindraje = cilindraje;
        this.color = color;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Motocicleta" + "\n" +
                "codigo=" + codigo + "\n" +
                "marca=" + marca + "\n" +
                "cilindraje=" + cilindraje + "\n" +
                "color=" + color + "\n" +
                "precio=" + precio + "\n" + "\n";
    }
}
