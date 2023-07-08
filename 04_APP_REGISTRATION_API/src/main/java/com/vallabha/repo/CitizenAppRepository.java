package com.vallabha.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vallabha.entity.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {

}
