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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Transactional
@Scope("request")
@Controller
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private Venda venda; // O Spring cria o objeto na sessão


    public VendaController(){
        vendaRepository = new VendaRepository();
    }

    @GetMapping("/adicionarCarrinho/{id}")
    public ModelAndView adicionarCarrinho(@PathVariable Long id, HttpSession session) {

        Produto produto = produtoRepository.produto(id);//acho o produto pelo id
        Venda venda = (Venda) session.getAttribute("venda"); // Obtém a venda (carrinho) da sessão

        if (venda == null) {
            venda = new Venda(); // Se não houver venda na sessão, cria uma nova
        }
        boolean itemExists = false;
        // Percorre os itens da venda para verificar se o produto já está no carrinho
        for (ItemVenda item : venda.getItens()) {
            if (item.getProduto().getId().equals(produto.getId())) {
                item.setQuantidade(item.getQuantidade() + 1.0); // Aumenta a quantidade se já existir
                itemExists = true;
                break;
            }
        }
        // Se o item não existir, adiciona no carrinho
        if (!itemExists) {
            ItemVenda novoItem = new ItemVenda();
            novoItem.setProduto(produto);
            novoItem.setQuantidade(1.0);
            novoItem.setVenda(venda); // Vincula o item na venda
            venda.getItens().add(novoItem);
        }

        session.setAttribute("venda", venda); // Atualiza a venda na sessão

        return new ModelAndView("/compra/view");
    }

    @GetMapping("/removerCarrinho/{id}")
    public ModelAndView removerCarrinho(@PathVariable Long id, HttpSession session) {

        Produto produto = produtoRepository.produto(id); // acho o produto pelo id
        Venda venda = (Venda) session.getAttribute("venda"); // acho a venda (carrinho) da sessão

        if (venda != null) {
            Iterator<ItemVenda> iterator = venda.getItens().iterator();

            while (iterator.hasNext()) {
                ItemVenda item = iterator.next();

                if (item.getProduto().getId().equals(produto.getId())) {
                    if (item.getQuantidade() > 1.0) {
                        item.setQuantidade(item.getQuantidade() - 1.0); // Diminui a quantidade se for maior que 1
                    } else {
                        iterator.remove(); // Remove o item se a quantidade for 1
                    }
                    break;
                }
            }

            session.setAttribute("venda", venda);
        }
        return new ModelAndView("/compra/view");
    }

    @GetMapping("/finalizarCarrinho")
    public ModelAndView finalizarCarrinho(HttpSession session) {

        Pessoa p = pessoaRepository.pessoa(1L);
        venda.setData(LocalDate.now());
        venda.setPessoa(p);
        p.getVendas().add(venda);

        vendaRepository.save(venda);
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
        model.addAttribute("vendas", vendaRepository.Vendas());
        return new ModelAndView("/venda/list");
    }



    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("venda")@Valid Venda venda, BindingResult result) {
        if(result.hasErrors())
            return new ModelAndView("compra/list");

        vendaRepository.save(venda);
        return new ModelAndView("redirect:/venda/list");
    }



    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        vendaRepository.remove(id);
        return new ModelAndView("redirect:/venda/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.venda(id));
        return new ModelAndView("/venda/form", model);
    }


    @GetMapping("/list/{id}")
    public ModelAndView list(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.venda(id));
        return new ModelAndView("/venda/detalhes", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        vendaRepository.update(venda);
        return new ModelAndView("redirect:/venda/list");
    }



    /* BUSCAS NOVAS POR HQL*/

    @GetMapping("/filtrardata")
    public ModelAndView filtroVendasPorData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, Model model) {

        System.out.println("chegou no VendaController ");

        List<Venda> vendas = vendaRepository.findByData(data);
        model.addAttribute("vendas", vendas);
        return new ModelAndView("venda/list");
    }


}
