package com.example.crud.Repository;

import com.example.crud.entity.Composers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRepository extends JpaRepository<Composers, Integer> {

    List<Composers> findByComposer(String composer);

    Composers findByWriter(String writer);

}
