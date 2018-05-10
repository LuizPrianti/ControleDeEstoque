package view;

import java.util.List;

import javax.swing.JOptionPane;

import controle.Estoque;
import modelo.Produto;

public class Venda {
	public Venda(Estoque estoque) {
		int codigo = 0;
		try {
			codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do produto: "));
		} catch (Exception NumberFormatException) {
			System.out.println("Digite um valor válido!");
		}

		if (estoque.buscaProduto(codigo) == false) {
			JOptionPane.showMessageDialog(null, "Produto nao encontrado.");
		} else {
			int quantidadeDisponivel = estoque.quantidadeDisponivel(codigo);
			
			List<Produto> produtos = estoque.imprimirEstoque();
			
			
			
			for (Produto produto : produtos) {
				if (produto.getCodigo() == codigo) {
					JOptionPane.showMessageDialog(null, "Nome: " + produto.getNome() + "\nPreço: " + produto.getPreco()
							+ "\nQunt. disponível: " + produto.getQuantidade());
					int quantidadeVenda = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de venda: "));
					
					if (quantidadeVenda > quantidadeDisponivel) {
						JOptionPane.showMessageDialog(null, "Quantidade nao disponível.");
					} else {
						estoque.venda(codigo, quantidadeVenda);
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja adicionar um comentário?", "Opinião",
								JOptionPane.YES_NO_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
							String comentario = JOptionPane.showInputDialog("");
							if (produto.getCodigo() == codigo) {
								produto.setComentario(comentario);
							}
						}
						JOptionPane.showMessageDialog(null, "Produto vendido!.");
					}
				}
			}
		}
	}
}
