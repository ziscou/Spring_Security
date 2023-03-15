package org.iesvdm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="idioma")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//Si utilizo @OneToMany(FetchType.LAZY) además debo usar
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idioma")
    private Long id;
    private String nombre;

    @Column(name = "ultima_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;

    @OneToMany(mappedBy = "idioma")
    @JsonIgnore
    @ToString.Exclude
    private List<Pelicula> peliculasIdioma;

    @OneToMany(mappedBy = "idiomaOriginal")
    @JsonIgnore
    @ToString.Exclude
    private List<Pelicula> peliculasIdiomaOriginal;
}
