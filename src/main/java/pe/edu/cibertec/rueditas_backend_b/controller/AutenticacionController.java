package pe.edu.cibertec.rueditas_backend_b.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.rueditas_backend_b.dto.SearchRequestDTO;
import pe.edu.cibertec.rueditas_backend_b.dto.SearchResponseDTO;
import pe.edu.cibertec.rueditas_backend_b.service.AutenticacionService;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/search")
    public SearchResponseDTO search(@RequestBody SearchRequestDTO searchRequestDTO) {
        try {
            String[] datosVehiculo = autenticacionService.validarPlaca(searchRequestDTO);

            if (datosVehiculo == null || datosVehiculo.length < 5) {
                return new SearchResponseDTO("Error", "Error: Vehiculo no encontrado", 0, 0, "");
            }

            String marca = datosVehiculo[0];
            String modelo = datosVehiculo[1];
            int nro_asientos = Integer.parseInt(datosVehiculo[2]);
            int precio = Integer.parseInt(datosVehiculo[3]);
            String color = datosVehiculo[4];

            return new SearchResponseDTO(marca, modelo, nro_asientos, precio, color);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new SearchResponseDTO("Error", "Error: Ocurrio un problema", 0, 0, "");
        }
    }
}
