package com.algaworks.algafoodapi2.domain.repository;

import com.algaworks.algafoodapi2.domain.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
