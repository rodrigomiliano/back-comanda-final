package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import comanda.entity.Rol;
import comanda.entity.Usuario;
import comanda.form.FormLogin;
import comanda.form.FormUsuario;
import comanda.repository.UsuariosRepository;
import comanda.service.IUsuariosService;
import comanda.service.exception.InvalidPasswordException;
import comanda.service.exception.RolNoEncontradoException;
import comanda.service.exception.UsuarioNotFoundException;

@Service
public class UsuariosService implements IUsuariosService {

	@Autowired
	private UsuariosRepository repoUsuarios;

	@Autowired
	private RolesService rolesService;

	public List<Usuario> buscarTodos() {
		return repoUsuarios.findAll();
	}

	public Usuario guardar(FormUsuario form) throws RolNoEncontradoException {
		Usuario usuario = new Usuario(form);
		Optional<Rol> rol = rolesService.buscarRol(form.getRolId());
		if (rol.isEmpty())
			throw new RolNoEncontradoException();
		usuario.setRol(rol.get());
		return repoUsuarios.save(usuario);
	}

	public void eliminar(int idUsuario) {
		repoUsuarios.deleteById(idUsuario);
	}

	public Optional<Usuario> buscarUsuario(int idUsuario) {
		return repoUsuarios.findById(idUsuario);
	}

	@Override
	public Usuario login(FormLogin formLogin) throws UsuarioNotFoundException, InvalidPasswordException {
		if (formLogin == null || formLogin.getEmail() == null || formLogin.getPassword() == null) {
			throw new UsuarioNotFoundException();
		}
		Usuario user = new Usuario();
		user.setEmail(formLogin.getEmail());
		Example<Usuario> example = Example.of(user);
		Optional<Usuario> userFound = repoUsuarios.findOne(example);
		if (userFound.isEmpty()) {
			throw new UsuarioNotFoundException();
		}
		if (!userFound.get().getContrasena().equals(formLogin.getPassword())) {
			throw new InvalidPasswordException();
		}
		return userFound.get();
	}
}
