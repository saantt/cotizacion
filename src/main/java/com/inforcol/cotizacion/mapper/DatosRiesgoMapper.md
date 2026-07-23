Después de colocar las anotaciones @Mapping, defines el método abstracto (la firma del método). Lo que pasa tras bambalinas es que MapStruct lee tus instrucciones y escribe el código Java real por ti.

En lugar de que tú escribas los getters y setters a mano en tu servicio, MapStruct genera de forma automática una clase oculta que implementa tu interfaz (normalmente llamada DatosRiesgoMapperImpl.java) durante la fase de compilación.

## ¿Cómo se ve el código real que genera MapStruct?
Cuando compilas el proyecto, MapStruct interpreta tus decoradores y genera un código limpio y nativo idéntico a este:

```java
// Clase generada automáticamente por MapStruct en la carpeta target/generated-sources
@Component
public class DatosRiesgoMapperImpl implements DatosRiesgoMapper {

    @Override
    public DatosRiesgoDTO modeloADto(DatosRiesgo modelo) {
        if (modelo == null) {
            return null;
        }

        DatosRiesgoDTO datosRiesgoDTO = new DatosRiesgoDTO();

        // MapStruct aplica las reglas de tus decoradores @Mapping:
        datosRiesgoDTO.setId(modelo.getIdCotizacion());
        datosRiesgoDTO.setMatricula(modelo.getPlaca());
        datosRiesgoDTO.setCedula(modelo.getCcTomador());
        datosRiesgoDTO.setEstadoId(modelo.getIdEstado());
        datosRiesgoDTO.setFecha(modelo.getFechaCotizacion());
        datosRiesgoDTO.setServicio(modelo.getTipoServicio());
        
        // Los atributos con nombres idénticos se mapean solos:
        datosRiesgoDTO.setModelo(modelo.getModelo());

        return datosRiesgoDTO;
    }

    @Override
    public DatosRiesgo dtoAModelo(DatosRiesgoDTO dto) {
        if (dto == null) {
            return null;
        }

        DatosRiesgo datosRiesgo = new DatosRiesgo();

        // Aplica el mapeo inverso que definiste
        datosRiesgo.setIdCotizacion(dto.getId());
        datosRiesgo.setPlaca(dto.getMatricula());
        datosRiesgo.setCcTomador(dto.getCedula());
        datosRiesgo.setIdEstado(dto.getEstadoId());
        datosRiesgo.setFechaCotizacion(dto.getFecha());
        datosRiesgo.setTipoServicio(dto.getServicio());
        datosRiesgo.setModelo(dto.getModelo());

        return datosRiesgo;
    }
}
```

## ¿Por qué es una ventaja competitiva?
- **Evita errores de tipado:** Si cambias el tipo de un atributo en el DTO (por ejemplo, de Integer a String), el compilador te avisará inmediatamente que el mapper está roto.
- **Protección contra valores nulos:** Si te fijas, MapStruct añade automáticamente una validación de seguridad (if (modelo == null)). Si el objeto viene vacío, te retorna un null en lugar de lanzar una excepción de puntero nulo (NullPointerException).
- **Mapeo automático por defecto:** Si tienes 20 atributos en tu clase y 15 de ellos se llaman exactamente igual en la base de datos y en el DTO, solo necesitas escribir decoradores @Mapping para los 5 atributos que cambiaron de nombre. Los otros 15 se conectan solos de forma inteligente.