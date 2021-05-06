package com.example.customerbackend.repositories;

import com.example.customerbackend.documents.CustomerDocument;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDocument, String> {
    @Query(value = "{'firstName': {$regex : ?0, $options: 'i'}}")
    Page<CustomerDocument> findByQuery(String firstName, Pageable pageable);

}
