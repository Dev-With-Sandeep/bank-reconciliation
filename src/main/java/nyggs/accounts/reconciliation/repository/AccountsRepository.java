package nyggs.accounts.reconciliation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nyggs.accounts.reconciliation.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Integer> {

}
