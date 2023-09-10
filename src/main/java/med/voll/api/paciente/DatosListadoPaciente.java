package med.voll.api.paciente;
//UN DTO RECIBE Y DEVUELVE DATOS AL FRONT
//recibe el pedido de nombre, especialidad,
// documento y email del front y los devuelve en un listado con solo esos campos -es un DTO
public record DatosListadoPaciente(
     String nombre, 
     String email,
     String getDocumentoIdentidad
   ) {

     public DatosListadoPaciente(Paciente paciente){
        this(paciente.getNombre(), paciente.getEmail(),paciente.getDocumentoIdentidad());
     }
        

}
