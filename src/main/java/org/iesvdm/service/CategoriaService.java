package org.iesvdm.service;

import org.iesvdm.domain.Categoria;
import org.iesvdm.exception.CategoriaNotFoundException;
import org.iesvdm.repository.CategoriaCustomRepository;
import org.iesvdm.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;
    private CategoriaCustomRepository categoriaCustomRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaCustomRepository categoriaCustomRepository) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaCustomRepository = categoriaCustomRepository;
    }

    public List<Categoria> allByQueryFiltersStream(Optional<String> buscarOptional, Optional<String> ordenarOptional) {

        List<Categoria> categoriaList = this.categoriaRepository.findAll();

        if ( buscarOptional.isPresent()) {

            categoriaList = categoriaList.stream().filter(categoria -> categoria.getNombre().toLowerCase()
                                                                        .contains(buscarOptional.get().toLowerCase())).collect(toList());
        }

        if ( ordenarOptional.isPresent() && "asc".equals(ordenarOptional.get())) {

            categoriaList = categoriaList.stream().sorted(comparing(Categoria::getNombre)).collect(toList());

        } else if ( ordenarOptional.isPresent() && "desc".equals(ordenarOptional.get())) {

            categoriaList = categoriaList.stream().sorted(comparing(Categoria::getNombre).reversed()).collect(toList());

        }

        return categoriaList;

    }

    public List<Categoria> allByQueryFiltersMethodQuery(Optional<String> buscarOptional, Optional<String> ordenarOptional) {

        List<Categoria> categoriaList = new ArrayList<>();

        if ( buscarOptional.isPresent() && !ordenarOptional.isPresent() ) {

            categoriaList = categoriaRepository.findByNombreContainingIgnoreCase(buscarOptional.get());

        } else if ( buscarOptional.isPresent() && ordenarOptional.isPresent() && "asc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOptional.get());

        } else if ( buscarOptional.isPresent() && ordenarOptional.isPresent() && "desc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOptional.get());

        } else if ( !buscarOptional.isPresent() && !ordenarOptional.isPresent() ) {

            categoriaList = categoriaRepository.findAll();

        } else if ( !buscarOptional.isPresent() && ordenarOptional.isPresent() && "asc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.findAllByOrderByNombreAsc();

        } else if ( !buscarOptional.isPresent() && ordenarOptional.isPresent() && "desc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.findAllByOrderByNombreDesc();

        }
        return categoriaList;
    }

    public List<Categoria> allByQueryFiltersAnnotationQuery(Optional<String> buscarOptional, Optional<String> ordenarOptional) {

        List<Categoria> categoriaList = new ArrayList<>();

        if (buscarOptional.isPresent() && !ordenarOptional.isPresent()) {

            categoriaList = categoriaRepository.queryCategoriaContieneNombre(buscarOptional.get());

        } else if (buscarOptional.isPresent() && ordenarOptional.isPresent() && "asc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.queryCategoriaContieneNombreOrdenadoAsc(buscarOptional.get());

        } else if (buscarOptional.isPresent() && ordenarOptional.isPresent() && "desc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.queryCategoriaContieneNombreOrdenadoDesc(buscarOptional.get());

        } else if (!buscarOptional.isPresent() && !ordenarOptional.isPresent()) {

            categoriaList = categoriaRepository.findAll();

        } else if (!buscarOptional.isPresent() && ordenarOptional.isPresent() && "asc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.queryCategoriaOrdenadoAsc();

        } else if (!buscarOptional.isPresent() && ordenarOptional.isPresent() && "desc".equals(ordenarOptional.get())) {

            categoriaList = categoriaRepository.queryCategoriaOrdenadoDesc();

        }
        return categoriaList;
    }

    public List<Categoria> allByQueryFiltersByCategoriaCustomRepository(Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        return this.categoriaCustomRepository.queryCustomCategoria(buscarOptional, ordenarOptional);
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        categoria.setUltimaActualizacion(new Date());
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {
        categoria.setUltimaActualizacion(new Date());
        return this.categoriaRepository.findById(id).map(p -> (id.equals(categoria.getIdCategoria()) ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {
                    this.categoriaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

}
