package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.PessoaRepository;
import br.edu.ifto.aula02.model.dao.ProdutoRepository;
import br.edu.ifto.aula02.model.dao.VendaRepository;
import br.edu.ifto.aula02.model.entity.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Scope("request")
@Controller
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private Venda venda; // O Spring cria o objeto na sessão


    public VendaController(){
        repository = new VendaRepository();
    }

    @GetMapping("/adicionarCarrinho/{id}")
    public ModelAndView adicionarCarrinho(@PathVariable Long id, HttpSession session) {

        Produto produto = produtoRepository.produto(id);//acho o produto pelo id
        ItemVenda item = new ItemVenda(); //cria o item

        item.setProduto(produto);
        item.setQuantidade(1.0);
        item.setVenda(venda); // diz pro item qual a venda ele ta linkado


        venda.getItens().add(item);// adiciona o item na lista de itens da venda

        return new ModelAndView("/compra/view");

    }

    @GetMapping("/removerCarrinho/{id}")
    public ModelAndView removerCarrinho(@PathVariable Long id) {

        Produto produto = produtoRepository.produto(id);
        ItemVenda item = new ItemVenda();

        item.setProduto(produto);
        item.setQuantidade(1.0);

        venda.getItens().remove(item);

        return new ModelAndView("/compra/view");

    }

    @GetMapping("/finalizarCarrinho")
    public ModelAndView finalizarCarrinho(HttpSession session) {

        Pessoa p = pessoaRepository.pessoa(1L);
        venda.setData(LocalDateTime.now());
        venda.setPessoa(p);
        p.getVendas().add(venda);

        repository.save(venda);
        session.invalidate();
        return new ModelAndView("redirect:/venda/list");
    }


    @GetMapping("/listCarrinho")
    public ModelAndView listarCarrinho(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.produtos());
        model.addAttribute("venda", new Venda());
        return new ModelAndView("/compra/view");
    }

    @GetMapping("/listarProdutos")
    public ModelAndView listarProdutosAVenda(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.produtos());
        model.addAttribute("venda", new Venda());
        return new ModelAndView("/compra/list");
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
    public ModelAndView save(@ModelAttribute("venda")@Valid Venda venda, BindingResult result) {
        if(result.hasErrors())
            return new ModelAndView("compra/list");

        repository.save(venda);
        return new ModelAndView("redirect:/venda/list");
    }



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
