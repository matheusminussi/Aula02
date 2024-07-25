package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.ProdutoRepository;
import br.edu.ifto.aula02.model.entity.Produto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Scope("request")
@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    public ProdutoController(){
        repository = new ProdutoRepository();
    }

    @GetMapping("/form")
    public ModelAndView form(Produto produto) {
        return new ModelAndView("/produto/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("/produto/list");
    }

    @GetMapping("/listProdutos")
    public ModelAndView listarCompra(ModelMap model) {
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("/compra/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("produto")@Valid Produto produto, BindingResult result) {
        if(result.hasErrors())
            return form(produto);

        repository.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", repository.produto(id));
        return new ModelAndView("/produto/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Produto produto, BindingResult result) {
        if(result.hasErrors())
            return form(produto);

        repository.update(produto);
        return new ModelAndView("redirect:/produto/list");
    }
}
