package pe.edu.cibertec.rueditas_backend_b.service;

import pe.edu.cibertec.rueditas_backend_b.dto.SearchRequestDTO;

import java.io.IOException;

public interface AutenticacionService {
    String[] validarPlaca(SearchRequestDTO searchRequestDTO) throws IOException;
}
