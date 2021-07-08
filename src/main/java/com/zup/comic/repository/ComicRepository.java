package com.zup.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.comic.model.Comic;

public interface ComicRepository extends JpaRepository<Comic, Long> {

}
