package med.voll.api.domain.medico;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;
//representa la informacion que llega desde el front es un DTO
// que no necesariamente es la misma que esta en la base de datos
public record DatosRegistroMedico(
    
    @NotBlank(message = "Nombre es obligatorio")
    String nombre,

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Formato de email es inválido")
    String email, 

    @NotBlank(message = "Teléfono es obligatorio")
    String telefono,

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{4,8}")
    String documento,

    @NotNull(message = "Especialidad es obligatorio")
    Especialidad especialidad,

    @NotNull(message = "Datos de dirección son obligatorios")
    @Valid
     DatosDireccion direccion) {
    
}
