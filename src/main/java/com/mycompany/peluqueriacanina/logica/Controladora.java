
package com.mycompany.peluqueriacanina.logica;

import com.mycompany.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia(); 

    public void guardar(String nombreMascota, String raza, String color, String alergico, 
            String atEsp, String nomDuenio, String celDuenio, String obs) {
        
        //creamos al dueño y le asignamos sus nuevos valores:
        Duenio duenio = new Duenio(); 
        duenio.setNombreDuenio(nomDuenio);
        duenio.setCelDuenio(celDuenio);
    
    
        //creamos la mascota y le asignamos sus nuevos valores:
        Mascota masco = new Mascota(); 
        masco.setNombre(nombreMascota);
        masco.setRaza(raza); 
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setAtencionEspecial(atEsp);
        masco.setObservaciones(obs);
        masco.setUnDuenio(duenio);
        
        
        controlPersis.guardar(duenio, masco); 
        
    
       
            
        }

    public List<Mascota> traerMascotas() {
        return controlPersis.traerMascotas();
    }

    public void borrarMascota(int id) {
        controlPersis.borrarMascota(id);   
    }

    public Mascota buscarMascota(int id) {
       return controlPersis.buscarMascota(id);
    }

    public void editarMascota(Mascota masco, String nombreMascota, String raza, String color, 
            String alergico, String atEsp, String obs, String nomDuenio, String celDuenio) {
        masco.setNombre(nombreMascota);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setAtencionEspecial(atEsp);
        masco.setObservaciones(obs);
        
        //MODIFICO DATOS DE LA MASCOTA: 
        controlPersis.editarMascota(masco);
        
        //SETEO DATOS DEL DUEÑO GRACIAS A MÉTODO EXCLUSIVO COLOCADO MAS ABAJO:
        Duenio dueno = this.buscarDuenio(masco.getUnDuenio().getIdDuenio());
        dueno.setCelDuenio(celDuenio);
        dueno.setNombreDuenio(nomDuenio);
        
        //MODIFICO LOS DATOS DEL DUENIO EN MI BD GRACIAS A OTRO MÉTODO EXCLUSIVO (colocado abajo)
        this.modificarDuenio(dueno);
    }

    private Duenio buscarDuenio(int idDuenio) {
       return controlPersis.buscarDuenio(idDuenio); 
    }

    private void modificarDuenio(Duenio dueno) {
        controlPersis.modificarDuenio(dueno); 
    }
    
    

    


    

    
    
}
