package ec.gob.mag.correo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.gob.mag.correo.dto.EmailRequestDto;
import ec.gob.mag.correo.dto.ResponseDTO;
import ec.gob.mag.correo.util.EmailService;
import ec.gob.mag.correo.util.Util;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCESS"),
		@ApiResponse(code = 404, message = "RESOURCE NOT FOUND"), @ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 201, message = "CREATED"), @ApiResponse(code = 401, message = "UNAUTHORIZED"),
		@ApiResponse(code = 415, message = "UNSUPPORTED TYPE - Representation not supported for the resource"),
		@ApiResponse(code = 500, message = "SERVER ERROR") })
public class CorreosController implements ErrorController {

	private static final String PATH = "/error";
	public static final Logger LOGGER = LoggerFactory.getLogger(CorreosController.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	@Qualifier("util")
	private Util util;

	@RequestMapping(value = "/mail_notificacion/", method = RequestMethod.POST)
	@ApiOperation(value = "Envio de mails", response = Object.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> sendMail(@Validated @RequestBody EmailRequestDto emailRequest,
			@RequestHeader(name = "Authorization") String token) {
		Map<String, String> model = new HashMap<>();
		model.put("contenido", emailRequest.getContenido());
		model.put("proyectoNombre", emailRequest.getProyectoNombre());
		model.put("destinatarioNombre", emailRequest.getDestinatarioNombre());
		String response = emailService.sendHTMLEmail(emailRequest, model);
		LOGGER.info("api_corrreo/api/mail_notificacion/" + response + " usuario: " + util.filterUsuId(token));
		return ResponseEntity.ok(new ResponseDTO("OK", response));
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

}
