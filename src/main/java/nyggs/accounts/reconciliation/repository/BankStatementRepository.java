package nyggs.accounts.reconciliation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nyggs.accounts.reconciliation.entity.BankStatement;

public interface BankStatementRepository extends JpaRepository<BankStatement, Long> {

	@Query(value = "select * from bank_statement where reconciled = 0 and is_active = 1", nativeQuery = true)
	List<BankStatement> findAllPendingData();
}
