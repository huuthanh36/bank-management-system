package com.example.bank_management.repository;

import com.example.bank_management.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
