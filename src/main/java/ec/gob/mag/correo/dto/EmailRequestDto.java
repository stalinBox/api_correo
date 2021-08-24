package ec.gob.mag.correo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDto {
	private String para;
	private String asunto;
	private String contenido;
	private String destinatarioNombre;
	private String proyectoNombre;
}// OK
