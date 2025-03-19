package nyggs.accounts.reconciliation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nyggs.accounts.reconciliation.entity.AccountTransactions;

public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Long> {

	@Query(value = "select * from acc_account_transaction where debit_account_id = :debitAccountId", nativeQuery = true)
	List<AccountTransactions> findAllBySourceAccountId(Integer debitAccountId);

	@Query(value = "select * from acc_account_transaction where credit_account_id = :creditAccountId", nativeQuery = true)
	List<AccountTransactions> findAllByCreditAccountId(Integer creditAccountId);

	@Query(value = "SELECT * FROM acc_account_transaction WHERE debit_account_id = :accountId OR credit_account_id = :accountId and is_active = 1", nativeQuery = true)
	List<AccountTransactions> findAllByAccountId(@Param("accountId") Integer accountId);

}
