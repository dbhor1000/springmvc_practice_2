package javacode_dk.springmvc_practice.repository;

import javacode_dk.springmvc_practice.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findAuthorByAuthorName(String authorName);

}
