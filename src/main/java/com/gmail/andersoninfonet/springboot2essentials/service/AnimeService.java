package com.gmail.andersoninfonet.springboot2essentials.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnimeService {
    
    private static List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(new Anime(1L, "Boku"), new Anime(2L, "Berserk")));
    }

    public List<Anime> list() {
        return animes;
    }

    public Anime findById(long id) {
        return animes.stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

	public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 200));
        animes.add(anime);
		return anime;
    }
    
    public void delete(long id) {
        animes.remove(this.findById(id));
    }

    public void replace(Anime anime) {
        delete(anime.getId());
        animes.add(anime);
    }
}
