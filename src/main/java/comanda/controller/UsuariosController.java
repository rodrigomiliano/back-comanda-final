package comanda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comanda.entity.Usuario;
import comanda.form.FormLogin;
import comanda.form.FormUsuario;
import comanda.service.IUsuariosService;
import comanda.service.exception.InvalidPasswordException;
import comanda.service.exception.RolNoEncontradoException;
import comanda.service.exception.UsuarioNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class UsuariosController {

	@Autowired
	private IUsuariosService serviceUsuarios;

	@GetMapping("/usuario")
	public List<Usuario> buscarTodos() {
		return serviceUsuarios.buscarTodos();
	}

	@GetMapping("/usuario/{id}")
	public Optional<Usuario> buscarUsuario(@PathVariable("id") int idUsuario) {
		return serviceUsuarios.buscarUsuario(idUsuario);
	}

	@PostMapping("/usuario")
	public Usuario guardar(@RequestBody FormUsuario formUsuario) throws RolNoEncontradoException {
		return serviceUsuarios.guardar(formUsuario);
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> guardar(@RequestBody FormLogin formLogin) throws RolNoEncontradoException {
		try {
			return ResponseEntity.ok(serviceUsuarios.login(formLogin));
		} catch (UsuarioNotFoundException | InvalidPasswordException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PutMapping("/usuario")
	public Usuario modificar(@RequestBody FormUsuario formUsuario) throws RolNoEncontradoException {
		return serviceUsuarios.guardar(formUsuario);
	}

	@DeleteMapping("/usuario/{id}")
	public String eliminar(@PathVariable("id") int idUsuario) {
		serviceUsuarios.eliminar(idUsuario);
		return "Registro Eliminado";
	}

}
