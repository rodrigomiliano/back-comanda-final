package comanda.service;

import java.util.List;
import java.util.Optional;

import comanda.entity.Usuario;
import comanda.form.FormLogin;
import comanda.form.FormUsuario;
import comanda.service.exception.InvalidPasswordException;
import comanda.service.exception.RolNoEncontradoException;
import comanda.service.exception.UsuarioNotFoundException;

public interface IUsuariosService {

	List<Usuario> buscarTodos();

	Usuario guardar(FormUsuario formUsuario) throws RolNoEncontradoException;

	void eliminar(int idUsuario);

	Optional<Usuario> buscarUsuario(int idUsuario);

	Usuario login(FormLogin formLogin) throws UsuarioNotFoundException, InvalidPasswordException;
}
