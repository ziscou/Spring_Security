package org.iesvdm.repository;

import org.iesvdm.domain.Pelicula;
import org.iesvdm.view.PeliculaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public PeliculaView findPeliculaByIdPelicula(long idPelicula);

}
