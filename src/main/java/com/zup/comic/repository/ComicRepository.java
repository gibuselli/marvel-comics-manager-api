package com.zup.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.comic.model.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

}
