package com.delta.poc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.delta.poc.vo.Empl;
@Mapper
public interface EmplDaoIfc{
  List<Empl> findAllEmployees();
  Empl findEmployeeById(Integer id);
  void insertEmployee(Empl empl);
  void updateEmployee(Empl empl,int id);
  void deleteEmployee(Integer id);
}
