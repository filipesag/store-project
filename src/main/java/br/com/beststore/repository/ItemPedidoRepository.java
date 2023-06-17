package br.com.beststore.repository;

import br.com.beststore.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Integer> {
}
