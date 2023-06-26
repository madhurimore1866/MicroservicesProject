package com.address.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.address.entity.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Serializable> {

}
