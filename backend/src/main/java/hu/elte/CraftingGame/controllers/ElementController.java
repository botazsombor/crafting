package hu.elte.CraftingGame.controllers;

import hu.elte.CraftingGame.entities.Element;
import hu.elte.CraftingGame.repositories.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/element")
public class ElementController {
    @Autowired
    private ElementRepository elementRepository;

    @GetMapping("/basic")
    public ResponseEntity<Iterable<Element>> getBasicElements() {
        Iterable<Element> elements = elementRepository.findAllByFirstParentAndSecondParent(null,null);
        return ResponseEntity.ok(elements);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Element>> getAllElements() {
        Iterable<Element> elements = elementRepository.findAll();
        return ResponseEntity.ok(elements);
    }

    @PostMapping("/fusion")
    public ResponseEntity<Optional<Element>> fusion(@RequestBody List<Element> reqElements) {
        Optional<Element> optionalElement = elementRepository.findByFirstParentAndSecondParent(reqElements.get(0).getId(),reqElements.get(1).getId());
        if(!optionalElement.isPresent()) {
            optionalElement = elementRepository.findByFirstParentAndSecondParent(reqElements.get(1).getId(),reqElements.get(2).getId());
            if(!optionalElement.isPresent()) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(optionalElement);
    }
}
