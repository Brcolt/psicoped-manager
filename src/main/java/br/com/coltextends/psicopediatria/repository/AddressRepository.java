package br.com.coltextends.psicopediatria.repository;

import br.com.coltextends.psicopediatria.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
