package comanda.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.entity.Comprobante;
import comanda.service.IComprobantesService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class ComprobantesController {

	@Autowired
	private IComprobantesService serviceComprobantes;
	
	@GetMapping("/comprobante")
	public List<Comprobante> buscarTodos(){
		return serviceComprobantes.buscarTodos();
	}
	
	@GetMapping("/comprobante/{id}")
	public Optional<Comprobante> buscarComprobante(@PathVariable("id") int idComprobante) {
		return serviceComprobantes.buscarComprobante(idComprobante);
	}
	
	@PostMapping("/comprobante") 
	public Comprobante guardar(@RequestBody Comprobante comprobante) {
		serviceComprobantes.guardar(comprobante);
		return comprobante;
	}
	
	@PutMapping("/comprobante")
	public Comprobante modificar(@RequestBody Comprobante comprobante) {
		serviceComprobantes.guardar(comprobante);
		return comprobante;
	} 		
		
	@DeleteMapping("/comprobante/{id}")
	public String eliminar(@PathVariable("id") int idComprobante) {
		serviceComprobantes.eliminar(idComprobante);
		return "Registro Eliminado";
	}
}
