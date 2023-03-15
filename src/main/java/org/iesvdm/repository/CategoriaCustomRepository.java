package org.iesvdm.repository;

import org.iesvdm.domain.Categoria;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface CategoriaCustomRepository {

    public List<Categoria> queryCustomCategoria(Optional<String> buscarOptional, Optional<String> ordenarOptional);

    public List<Categoria> queryCustomCategoriaConPageable(Optional<String> buscarOptional
            , Optional<String> ordenarOptional
            , Pageable pageable);

}
