package com.mortgage.plan.web.repository;

import com.mortgage.plan.web.model.MortgagePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MortgagePlanRepository extends JpaRepository<MortgagePlan, Integer> {

}
