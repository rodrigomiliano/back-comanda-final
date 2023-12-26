package comanda.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.entity.Comanda;
import comanda.service.IComandasService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class ComandasController {

	@Autowired
	private IComandasService serviceComandas;

	@GetMapping("/comanda")
	public List<Comanda> buscarTodas() {
		return serviceComandas.buscarTodas();
	}

	@GetMapping("/comanda/{id}")
	public Optional<Comanda> buscarComanda(@PathVariable("id") int idComanda) {
		return serviceComandas.buscarComanda(idComanda);
	}

	@PostMapping("/comanda")
	public Comanda guardar(@RequestBody Comanda comanda) {
		serviceComandas.guardar(comanda);
		return comanda;
	}
	
	// --------------CREAR ITEMCOMANDA--------------
		@PostMapping("/comanda/{id}/crearitemcomanda")
		public Comanda buscarComanda(@PathVariable("id") @RequestBody Comanda comanda) {
			serviceComandas.crearItemComanda(comanda);
			return comanda;
		}

	@PutMapping("/comanda")
	public Comanda modificar(@RequestBody Comanda comanda) {
		serviceComandas.guardar(comanda);
		return comanda;
	}

	@DeleteMapping("/comanda/{id}")
	public String eliminar(@PathVariable("id") int idComanda) {
		serviceComandas.eliminar(idComanda);
		return "Registro Eliminado";
	}
}
