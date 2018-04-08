package br.com.rr.feed.repository;

import br.com.rr.feed.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
