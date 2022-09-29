package me.balbino.wamotopecas.repository;

import me.balbino.wamotopecas.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNome(String nome, Pageable sort);
}
