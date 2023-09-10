package med.voll.api.medico;
//UN DTO RECIBE Y DEVUELVE DATOS AL FRONT
//recibe el pedido de nombre, especialidad,
// documento y email del front y los devuelve en un listado con solo esos campos -es un DTO
public record DatosListadoMedico(
   Long id,
    String nombre, 
    String especialidad, 
    String documento,
     String email) {

     public DatosListadoMedico(Medico medico){
        this(medico.getId(),medico.getNombre(), medico.getEspecialidad().toString(),medico.getDocumento(),medico.getEmail());
     }
        

}
