package org.iesvdm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public class CategoriaCustomRepositoryJPQLImpl implements CategoriaCustomRepository{
    @Autowired
    private EntityManager em;
    @Override
    public List<Categoria> queryCustomCategoria(Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT C FROM Categoria C");
        if (buscarOptional.isPresent()) {
            queryBuilder.append(" ").append("WHERE C.nombre like :nombre");
        }
        if (ordenarOptional.isPresent()) {
            if (buscarOptional.isPresent() && "asc".equalsIgnoreCase(buscarOptional.get()) ) {
                queryBuilder.append(" ").append("ORDER BY C.nombre ASC");
            }  else if (buscarOptional.isPresent() && "desc".equalsIgnoreCase(buscarOptional.get()) ) {
                queryBuilder.append(" ").append("ORDER BY C.nombre DESC");
            }
        }
        //En este caso se trata de una consulta JPQL, es decir, sintaxis de SQL pero con Entidades de JPA
        Query query = em.createQuery(queryBuilder.toString());
        if (buscarOptional.isPresent()) {
            query.setParameter("nombre", "%"+buscarOptional.get()+"%");
        }
        return query.getResultList();
    }

    @Override
    public List<Categoria> queryCustomCategoriaConPageable(Optional<String> buscarOptional
            , Optional<String> ordenarOptional
            , Pageable pageable) {

        return null;

    }
}
