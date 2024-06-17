package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.ProdutoRepository;
import br.edu.ifto.aula02.model.dao.VendaRepository;
import br.edu.ifto.aula02.model.entity.ItemVenda;
import br.edu.ifto.aula02.model.entity.Produto;
import br.edu.ifto.aula02.model.entity.Venda;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository repository;


    public VendaController(){
        repository = new VendaRepository();
    }


    @GetMapping("/form")
    public String form(Venda Venda){
        return "/venda/form";
    }


    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", repository.Vendas());
        return new ModelAndView("/venda/list");
    }



    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("venda") Venda venda) {
        repository.save(venda);
        return new ModelAndView("redirect:/venda/list");
    }

    /*@PostMapping("/venda/addItem")
    public ModelAndView VendaAddItem(ItemVenda item){

    }*/


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/venda/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/venda/form", model);
    }


    @GetMapping("/list/{id}")
    public ModelAndView list(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/venda/detalhes", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        repository.update(venda);
        return new ModelAndView("redirect:/venda/list");
    }


}
