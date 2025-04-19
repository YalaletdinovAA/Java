package ru.innopolis.blog.repositories;

import ru.innopolis.blog.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long> {
}