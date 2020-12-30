package com.gmail.andersoninfonet.springboot2essentials.repository;

import java.util.List;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    
    List<Anime> findByName(String name);
}
