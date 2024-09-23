package pe.edu.cibertec.rueditas_backend_b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.rueditas_backend_b.dto.SearchRequestDTO;
import pe.edu.cibertec.rueditas_backend_b.service.AutenticacionService;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarPlaca(SearchRequestDTO searchRequestDTO) throws IOException {
        String[] datosVehiculo = null;
        Resource resource = resourceLoader.getResource("classpath:vehiculos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(","); // Cambiar ";" a ","

                if (searchRequestDTO.placa().equals(datos[0])) {
                    datosVehiculo = new String[5];
                    datosVehiculo[0] = datos[1]; // marca
                    datosVehiculo[1] = datos[2]; // modelo
                    datosVehiculo[2] = datos[3]; // nro_asientos
                    datosVehiculo[3] = datos[4]; // precio
                    datosVehiculo[4] = datos[5]; // color
                    break; // Salir del bucle si se encuentra el vehículo
                }
            }

        } catch (IOException e) {
            throw new IOException(e);
        }

        return datosVehiculo;
    }
}
