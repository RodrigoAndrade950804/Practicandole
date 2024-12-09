import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MotocicletaManager {

    private List<Motocicleta> motocicletas;

    public MotocicletaManager(){
        motocicletas=new ArrayList<Motocicleta>();
        try{
            predefinir();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public boolean buscarMotocicletaCodigo(int codigo){ //busqueda secuencial

        boolean encontrado=false;
        for (Motocicleta x:motocicletas){
            if (x.getCodigo()==codigo){
                encontrado=true;
                return encontrado;
            }
        }
        return encontrado;
    }

    public Motocicleta buscarBinario(int codigo){ // caracteristicas que esten en orden y que no se repita
        if (codigo<motocicletas.get(0).getCodigo() || codigo>motocicletas.getLast().getCodigo())
            return null; // no existe
        int inf=0;
        int sup= motocicletas.size()-1;
        int centro;

        while (inf<=sup){
            centro=(inf+sup)/2;
            if (motocicletas.get(centro).getCodigo()==codigo){
                return motocicletas.get(centro);
            } else if (codigo>motocicletas.get(centro).getCodigo()) {
                inf=centro+1;
            } else {
                sup=centro-1;
            }
        }
        return null;
    }

    public boolean agregarMotocicleta(Motocicleta nuevo) throws Exception{

        if (buscarMotocicletaCodigo(nuevo.getCodigo())==true){
            throw new Exception("El ID ya existe");
        } else {
            motocicletas.add(nuevo);
            ordenar();
            return true;
        }
    }

    public void predefinir()throws Exception{
        agregarMotocicleta(new Motocicleta(1,"HONDA",70,"ROJO",5245.4f));
        agregarMotocicleta(new Motocicleta(2,"BMW",28,"AZUL",5245.4f));
        agregarMotocicleta(new Motocicleta(3,"VESPA",39,"AMARILLO",5245.4f));
        agregarMotocicleta(new Motocicleta(4,"ROYAL",60,"NARANJA",5245.4f));
        agregarMotocicleta(new Motocicleta(5,"ENFIELD",60,"CELESTE",5245.4f));
    }

    public boolean agregarOActualizarMotocicleta(Motocicleta nuevo) {
        for (Motocicleta moto : motocicletas) {
            if (moto.getCodigo() == nuevo.getCodigo()) {
                moto.setMarca(nuevo.getMarca());
                moto.setCilindraje(nuevo.getCilindraje());
                moto.setColor(nuevo.getColor());
                moto.setPrecio(nuevo.getPrecio());
                return false; // Indica que fue una actualización
            }
        }
        motocicletas.add(nuevo);
        ordenar();
        return true; // Indica que fue una adición
    }


    public  void ordenar(){

        int j=0;
        Motocicleta aux;
        for (int p=1;p<motocicletas.size();p++){
            j=p-1;
            aux=motocicletas.get(p);
            while(j>=0 && aux.getCodigo()<motocicletas.get(j).getCodigo()){

                motocicletas.set(j+1,motocicletas.get(j));
                j--;
            }
            motocicletas.set(j+1,aux);
        }
    }

    public void ordenarPorPrecio(boolean ascendente) {
        motocicletas.sort((m1, m2) -> {
            if (ascendente) {
                return Float.compare(m1.getPrecio(), m2.getPrecio());
            } else {
                return Float.compare(m2.getPrecio(), m1.getPrecio());
            }
        });
    }

    public boolean eliminarMotocicleta(int codigo) {
        return motocicletas.removeIf(moto -> moto.getCodigo() == codigo);
    }

    public List<Motocicleta> getMotocicletas() {
        return motocicletas;
    }

    @Override
    public String toString() {
        String resultado="";
        for (Motocicleta j:motocicletas){
            resultado+=j.toString()+"";
        }
        return resultado;
    }
}
