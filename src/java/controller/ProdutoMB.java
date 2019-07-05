package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Produto;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoMB {
    
    private Produto produto;
    private List<Produto> produtos;
    
    public ProdutoMB() {
        produtos = new ArrayList<>();
    }
    
   
    
    
    public void inserir(){
         produto = new Produto();
         produto.setQuantidade(1);
        if(produtos.add(produto)){
            System.out.println("Salvo com sucesso !!!");
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('productDialog').hide();");
            
        }else{
            System.out.println("Ocorreu um erro ao inserir o gênero !!!");
        }
    }
    
    public void alterar(Produto produto){
        this.produto = produto;
        System.out.println("Alterado com sucesso !!!");
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('productDialogAlterar').hide();");
        
    }
    
    public void deletar(Produto produto){
        if(produtos.remove(produto)){
            System.out.println("Excluido com sucesso !!!");
        }else{
            System.out.println("Ocorreu um erro ao excluir o produto !!!");
        }
    }
    
    public String carrinhoDeCompras(){
        return "carrinho.xhtml?faces-redirect=true";
    }
    
    public void carregarDados(Produto produto){
        this.produto = produto;
    }
   
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
   
    
}
