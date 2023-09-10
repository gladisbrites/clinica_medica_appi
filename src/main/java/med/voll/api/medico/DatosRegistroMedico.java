package med.voll.api.medico;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;
//representa la informacion que llega desde el front es un DTO
// que no necesariamente es la misma que esta en la base de datos
public record DatosRegistroMedico(
    
    @NotBlank
    String nombre,

    @NotBlank
    @Email
    String email, 

    @NotBlank
    String telefono,

    @NotBlank
    @Pattern(regexp = "\\d{4,8}")
    String documento,

    @NotNull
    Especialidad especialidad,

    @NotNull
    @Valid
     DatosDireccion direccion) {
    
}
