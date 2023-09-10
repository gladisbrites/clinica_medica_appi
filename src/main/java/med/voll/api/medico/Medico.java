package med.voll.api.medico;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Medico {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

 public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo=true;
        this.nombre=datosRegistroMedico.nombre();
        this.email=datosRegistroMedico.email();
         this.documento=datosRegistroMedico.documento();
        this.telefono=datosRegistroMedico.telefono();
        this.especialidad =datosRegistroMedico.especialidad();
        this.direccion=new Direccion(datosRegistroMedico.direccion()) ;
    }

    public void actualizarDatos(DatosActulizarMedico datosActulizarMedico) {   
        if (datosActulizarMedico.nombre()!=null){
            this.nombre=datosActulizarMedico.nombre();
           }
        if(datosActulizarMedico.documento()!=null){
             this.documento=datosActulizarMedico.documento();
        }
        if(datosActulizarMedico.direccion()!=null){
            this.direccion= direccion.actualizarDatos(datosActulizarMedico.direccion());
        } 
       
    }

    public void desactivarMedico() {
        this.activo=false;
    }
}
