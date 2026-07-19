package br.com.ifba.prg03projeto.mercado.filial.controller;

import br.com.ifba.prg03projeto.mercado.filial.entity.Filial;
import br.com.ifba.prg03projeto.mercado.filial.service.FilialService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class FilialController {

    private final FilialService filialService;

    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    public Filial save(Filial filial) {
        return filialService.save(filial);
    }

    public Filial update(Long id, Filial filial) {
        return filialService.update(id, filial);
    }

    public List<Filial> findAll() {
        return filialService.findAll();
    }

    public Optional<Filial> findById(Long id) {
        return filialService.findById(id);
    }

    public void delete(Long id) {
        filialService.delete(id);
    }
}