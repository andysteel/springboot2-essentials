package com.gmail.andersoninfonet.springboot2essentials.service;

import java.util.List;

import com.gmail.andersoninfonet.springboot2essentials.exception.AnimeBadRequestException;
import com.gmail.andersoninfonet.springboot2essentials.mapper.AnimeMapper;
import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeRepository;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnimeService {
    
    private final AnimeRepository animeRepository;

    public List<Anime> list() {
        return animeRepository.findAll();
    }

    public List<Anime> listByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                                .orElseThrow(() -> new AnimeBadRequestException("Anime not found."));
    }

	public Anime save(AnimeRequestPost requestPost) {
		return animeRepository.save(AnimeMapper.INSTANCE.fromPostToAnime(requestPost));
    }
    
    public void delete(long id) {
        animeRepository.delete(this.findById(id));
    }

    public void replace(AnimeRequestPut requestPut) {
        Anime savedAnime = this.findById(requestPut.getId());
        Anime anime = AnimeMapper.INSTANCE.fromPutToAnime(requestPut);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
