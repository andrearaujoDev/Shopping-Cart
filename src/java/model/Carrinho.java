package model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos;
    
    public void Carrinho(){
        produtos = new ArrayList<>();
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
}
