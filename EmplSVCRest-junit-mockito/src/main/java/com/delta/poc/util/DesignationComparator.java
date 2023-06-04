package com.delta.poc.util;

import java.util.Comparator;

import com.delta.poc.vo.Empl;


public class DesignationComparator implements Comparator<Empl> {

	@Override
	public int compare(Empl o1, Empl o2) {
		return o2.getEmpDesignation().compareTo(o1.getEmpDesignation());
		
	}

}
