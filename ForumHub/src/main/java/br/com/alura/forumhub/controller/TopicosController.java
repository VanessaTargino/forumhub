package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.model.Topico;
import br.com.alura.forumhub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalhar(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico != null) {
            return ResponseEntity.ok(topico);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Topico> cadastrar(@RequestBody Topico topico) {
        Topico novoTopico = topicoRepository.save(topico);
        return ResponseEntity.ok(novoTopico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody Topico topico) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        topico.setId(id);
        Topico topicoAtualizado = topicoRepository.save(topico);
        return ResponseEntity.ok(topicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}