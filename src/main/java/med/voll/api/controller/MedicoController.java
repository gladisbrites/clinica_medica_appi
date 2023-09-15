package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.DatosActulizarMedico;
import med.voll.api.domain.medico.DatosListadoMedico;
import med.voll.api.domain.medico.DatosRegistroMedico;
import med.voll.api.domain.medico.DatosRespuestaMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
     @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping 
    public ResponseEntity <DatosRespuestaMedico>registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
    UriComponentsBuilder uriComponentsBuilder){
       Medico medico= medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico =new DatosRespuestaMedico(medico.getId(),
        medico.getNombre(),
        medico.getEmail(),
        medico.getTelefono(),
        medico.getDocumento(),
        medico.getEspecialidad(),
        new DatosDireccion(medico.getDireccion().getCalle(),
            medico.getDireccion().getDistrito(), 
            medico.getDireccion().getComplemento(),
            medico.getDireccion().getNumero(),
            medico.getDireccion().getCiudad()));

        //retorna 201 created
        //donde encontrar al medico
    URI url =uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaMedico);
      

    } 
    @GetMapping
    public ResponseEntity<Page <DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 4)Pageable paginacion ){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new)) ;
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActulizarMedico datosActulizarMedico){
        Medico medico=medicoRepository.getReferenceById(datosActulizarMedico.id());
        medico.actualizarDatos(datosActulizarMedico);

        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),
        medico.getNombre(),
        medico.getEmail(),
        medico.getTelefono(),
        medico.getDocumento(),
        medico.getEspecialidad(),
        new DatosDireccion(medico.getDireccion().getCalle(),
            medico.getDireccion().getDistrito(), 
            medico.getDireccion().getComplemento(),
            medico.getDireccion().getNumero(),
            medico.getDireccion().getCiudad())));
    }

    /*@DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
         Medico medico=medicoRepository.getReferenceById(id);
         medicoRepository.delete(medico);
    }*/ //este metodo delete borra el objeto de la base de datos sin posibilidad de recupero


    //delete logico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }
      @GetMapping("/{id}")
        public ResponseEntity <DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        var datosMedicos =new DatosRespuestaMedico(medico.getId(),
        medico.getNombre(),
        medico.getEmail(),
        medico.getTelefono(),
        medico.getDocumento(),
        medico.getEspecialidad(),
        new DatosDireccion(medico.getDireccion().getCalle(),
            medico.getDireccion().getDistrito(), 
            medico.getDireccion().getComplemento(),
            medico.getDireccion().getNumero(),
            medico.getDireccion().getCiudad()));

        return ResponseEntity.ok(datosMedicos);
    }
}
