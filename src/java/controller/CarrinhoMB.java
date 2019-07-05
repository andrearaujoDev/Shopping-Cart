package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Carrinho;
import model.Produto;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "carrinhoMB")
@SessionScoped
public class CarrinhoMB {
    private Carrinho carrinho = new Carrinho();
    private List<Produto> produtos = new ArrayList<>();
    
    private String formadepagamento;
    private String parcelado;
    private String emailDigitado;
    
    public CarrinhoMB() {
        
    }
    
    public String adicionarProduto(Produto produto){
        produtos.add(produto);
        carrinho.setProdutos(produtos);
        return "carrinho.xhtml?faces-redirect=true";
    }
    
    public void remover(Produto produto){
        if(carrinho.getProdutos().remove(produto)){
            System.out.println("Excluido com sucesso !!!");
        }else{
            System.out.println("Ocorreu um erro ao excluir o produto !!!");
        }
    }
    
    public float calculaTotal(){
        float total = 0;
        for(Produto p : carrinho.getProdutos()){
            total += p.getPreco() * p.getQuantidade();
        }
        return total;
    }
    
    public void SubtrairQuantidade(Produto produto){
        if(produto.getQuantidade() - 1 >= 1){
          produto.setQuantidade(produto.getQuantidade() - 1);  
        }
        
        //calculaTotal();
    }
    
    public void SomarQuantidade(Produto produto){
        produto.setQuantidade(produto.getQuantidade() + 1);
        //calculaTotal();
    }
    
    public void formadePagamento(){
        if(formadepagamento.equals("Visa") || formadepagamento.equals("Mastercard") || formadepagamento.equals("American Express")){
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('finalizarPedidoCartao').show();");
        }
        if(formadepagamento.equals("Boleto")){
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('finalizarPedidoBoleto').show();");
        }
    }
    
    public String voltarCompra(){
        return "index.xhtml?faces-redirect=true";
    }
    
    public void finalizado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Parcelado no Cartão (" + formadepagamento + ") em : ", parcelado + " sem juros !!!"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra Realizada com Sucesso !!!", ""));
    }
    
    public void finalizadoBoleto() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, formadepagamento +  " gerado e enviado para : " , emailDigitado ));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra Realizada com Sucesso !!!", ""));
    }
    
    public void campoObrigatorio(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo email é obrigatorio ",""));
    }
    
    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getFormadepagamento() {
        return formadepagamento;
    }

    public void setFormadepagamento(String formadepagamento) {
        this.formadepagamento = formadepagamento;
    }

    public String getParcelado() {
        return parcelado;
    }

    public void setParcelado(String parcelado) {
        this.parcelado = parcelado;
    }

    public String getEmailDigitado() {
        return emailDigitado;
    }

    public void setEmailDigitado(String emailDigitado) {
        this.emailDigitado = emailDigitado;
    }
    
}

