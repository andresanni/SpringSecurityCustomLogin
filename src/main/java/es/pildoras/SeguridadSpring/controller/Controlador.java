package es.pildoras.SeguridadSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {
	
	@GetMapping("/")
	public String muestraInicio() {
		return "inicio";		
	}
	
	@GetMapping("/administradores")
	public String menuAdministradores() {
		return "administradores";
	}
	
	@GetMapping("/usuarios")
	public String menuUsuarios() {
		return "usuariosComunes";
	}
	
	@GetMapping("/accesoDenegado")
	public String accesoDenegado() {
		return "accesoDenegado";
	}
	

}
