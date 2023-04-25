package org.check.something.repository;

import org.check.something.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
