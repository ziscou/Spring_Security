package org.iesvdm.repository;

import org.iesvdm.domain.Categoria;
import org.iesvdm.dto.CategoriaDTO;
import org.iesvdm.dto.PeliculaDTO;
import org.iesvdm.dto.PeliculaRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre);

    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreDesc(String nombre);

    public List<Categoria> findAllByOrderByNombreAsc();

    public List<Categoria> findAllByOrderByNombreDesc();

    public List<CategoriaDTO> findAllBy();


    //Bloque @Query JPQL

    @Query(value = "select C from Categoria C where C.nombre like %:nombre%")
    public List<Categoria> queryCategoriaContieneNombre(@Param("nombre") String nombre);
    @Query(value = "select C from Categoria C where C.nombre like %:nombre% order by C.nombre asc")
    public List<Categoria> queryCategoriaContieneNombreOrdenadoAsc(@Param("nombre") String nombre);

    @Query(value = "select C from Categoria C where C.nombre like %?1% order by C.nombre desc")
    public List<Categoria> queryCategoriaContieneNombreOrdenadoDesc(String nombre);

    @Query(value = "select C from Categoria C order by C.nombre asc")
    public List<Categoria> queryCategoriaOrdenadoAsc();

    @Query(value = "select C from Categoria C order by C.nombre asc")
    public List<Categoria> queryCategoriaOrdenadoDesc();

    @Query(value = "select P.idPelicula, P.titulo, P.idioma.nombre from Pelicula P")
    public List<Object[]> queryPeliculaProyeccionEscalar();

    @Query(value = "select new org.iesvdm.dto.PeliculaDTO(P.idPelicula, P.titulo, P.descripcion) from Pelicula P")
    public List<PeliculaDTO> queryPeliculaProyeccionDTOConstructorJPQL();

    @Query(value = "select new org.iesvdm.dto.PeliculaRecord(P.idPelicula, P.titulo, P.descripcion) from Pelicula P")
    public List<PeliculaRecord> queryPeliculaProyeccionRecordConstructorJPQL();



    //Bloque method query


    public List<Categoria> findByNombreContainingIgnoreCase(String nombre);
    public Page<Categoria> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    @Query(value = "select C from Categoria C where C.nombre like %?1%"
            ,countQuery = "select count(C) from Categoria C where C.nombre like %?1%")
    public Page<Categoria> queryJPQLBuscarCategoria(String nombreBuscar, Pageable pageable);

    @Query(value = "select * from categoria where nombre like %?1%"
                    ,countQuery = "select count(*) from categoria where nombre like %?1%"
                    , nativeQuery = true)
    public Page<Categoria> querySQLBuscarCategoria(String nombreBuscar, Pageable pageable);


    //Bloque pageable
    //    public List<Categoria> queryCategoriaOrdenadoDesc();
//    @Query(value = "select *  from categoria order by nombre asc", nativeQuery = true)
//
//    public List<Categoria> queryCategoriaOrdenadoAsc();
//    @Query(value = "select *  from categoria order by nombre asc", nativeQuery = true)
//
//    public List<Categoria> queryCategoriaContieneNombreOrdenadoDesc(String nombre);
//    @Query(value = "select *  from categoria where nombre like %?1% order by nombre desc", nativeQuery = true)
//
//    public List<Categoria> queryCategoriaContieneNombreOrdenadoAsc(@Param("nombre") String nombre);
//    @Query(value = "select *  from categoria where nombre like %:nombre% order by nombre asc", nativeQuery = true)
//
//    public List<Categoria> queryCategoriaContieneNombre(@Param("nombre") String nombre);
//    @Query(value = "select *  from categoria where nombre like %:nombre%", nativeQuery = true)
//Bloque @Query
//Bloque method query

}


