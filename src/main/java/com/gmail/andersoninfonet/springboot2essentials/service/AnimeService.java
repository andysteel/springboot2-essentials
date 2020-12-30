package com.gmail.andersoninfonet.springboot2essentials.service;

import java.util.List;

import com.gmail.andersoninfonet.springboot2essentials.mapper.AnimeMapper;
import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeRepository;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeService {
    
    private final AnimeRepository animeRepository;

    public List<Anime> list() {
        return animeRepository.findAll();
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

	public Anime save(AnimeRequestPost requestPost) {
		return animeRepository.save(AnimeMapper.INSTANCE.toAnime(requestPost));
    }
    
    public void delete(long id) {
        animeRepository.delete(this.findById(id));
    }

    public void replace(AnimeRequestPut requestPut) {
        Anime savedAnime = this.findById(requestPut.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(requestPut);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
