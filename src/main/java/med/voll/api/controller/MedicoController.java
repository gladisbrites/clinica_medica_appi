package med.voll.api.controller;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import med.voll.api.medico.DatosActulizarMedico;
import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
     @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping 
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
       
        medicoRepository.save(new Medico(datosRegistroMedico));

    } 
    @GetMapping
    public Page <DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2)Pageable paginacion ){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }

    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActulizarMedico datosActulizarMedico){
        Medico medico=medicoRepository.getReferenceById(datosActulizarMedico.id());
        medico.actualizarDatos(datosActulizarMedico);

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
    public void eliminarMedico(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }
   
}
