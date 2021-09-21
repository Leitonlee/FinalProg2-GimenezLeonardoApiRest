package com.example.persona.services;

import com.example.persona.entities.Persona;
import com.example.persona.repositories.BaseRepository;
import com.example.persona.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository){
        super(baseRepository);
    }

    @Override
    @Transactional
    public List<Persona> findAll() throws Exception {
        try{
            List<Persona> entities = personaRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona findById(Long id) throws Exception {
        try{
            Optional<Persona> entityOptional = personaRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona save(Persona entity) throws Exception {
        try{
            entity = personaRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona entity) throws Exception {
        try{
            Optional<Persona> entityOptional = personaRepository.findById(id);
            Persona persona = entityOptional.get();
            persona = personaRepository.save(entity);
            return persona;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if (personaRepository.existsById(id)){
                personaRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Persona> search(String filtro) throws Exception {
        try {
            //List<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro, filtro);

            //QUERI CON JPQL
            //List<Persona> personas = personaRepository.search(filtro);

            //QUERI CON SQL NATIVO  -EN USO-
            List<Persona> personas = personaRepository.searchNativo(filtro);

            return personas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Persona> search(String filtro, Pageable pageable) throws Exception {
        try {
            //METODOS DE QUERY  PAGINADO
            //Page<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro, filtro, pageable);

            //QUERI CON JPQL   PAGINADO
            //Page<Persona> personas = personaRepository.search(filtro, pageable);

            //QUERI CON SQL NATIVO   PAGINADO  -EN USO-
            Page<Persona> personas = personaRepository.searchNativo(filtro, pageable);

            return personas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
