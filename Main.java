import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Produto {
    private int id;
    private String nome;
    private double valor;

    public Produto(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }


    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + "]";
    }
}

class CarrinhoItem {
    private int idProduto;
    private int quantidade;

    public CarrinhoItem(int idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "CarrinhoItem [idProduto=" + idProduto + ", quantidade=" + quantidade + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1, "Produto 1", 19.99));
        produtos.add(new Produto(2, "Produto 2", 29.99));
        produtos.add(new Produto(3, "Produto 3", 9.99));

        List<CarrinhoItem> carrinho = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirListaProdutos(produtos);

            System.out.print("Digite o ID do produto que deseja adicionar ao carrinho (ou -1 para sair): ");
            int idProduto = scanner.nextInt();

            if (idProduto == -1) {
                break;
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();

            adicionarProdutoAoCarrinho(carrinho, produtos, idProduto, quantidade);
        }


        System.out.println("Carrinho de Compras:");
        for (CarrinhoItem item : carrinho) {
            System.out.println(item);
        }


        double valorTotal = calcularValorTotalCarrinho(carrinho, produtos);
        System.out.println("Valor Total do Carrinho: R$ " + valorTotal);
    }

  
    public static void adicionarProdutoAoCarrinho(List<CarrinhoItem> carrinho, List<Produto> produtos, int idProduto, int quantidade) {
     
        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
          
                for (CarrinhoItem item : carrinho) {
                    if (item.getIdProduto() == idProduto) {
              
                        item.setQuantidade(item.getQuantidade() + quantidade);
                        return;
                    }
                }
               
                carrinho.add(new CarrinhoItem(idProduto, quantidade));
                return;
            }
        }
        System.out.println("Produto com ID " + idProduto + " não encontrado.");
    }
    
    public static void exibirListaProdutos(List<Produto> produtos) {
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }


    public static double calcularValorTotalCarrinho(List<CarrinhoItem> carrinho, List<Produto> produtos) {
        double valorTotal = 0.0;

        for (CarrinhoItem item : carrinho) {
            int idProduto = item.getIdProduto();
            int quantidade = item.getQuantidade();

          
            for (Produto produto : produtos) {
                if (produto.getId() == idProduto) {
                    valorTotal += produto.getValor() * quantidade;
                    break; 
                }
            }
        }

        return valorTotal;
    }
}
