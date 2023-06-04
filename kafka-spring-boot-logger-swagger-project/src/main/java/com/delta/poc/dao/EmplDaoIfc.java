package com.delta.poc.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delta.poc.vo.Empl;
@Repository
public interface EmplDaoIfc extends JpaRepository<Empl, Long> {

    
}
