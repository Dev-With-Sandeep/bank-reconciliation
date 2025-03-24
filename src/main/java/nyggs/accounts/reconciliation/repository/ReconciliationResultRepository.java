package nyggs.accounts.reconciliation.repository;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import nyggs.accounts.reconciliation.dto.DashboardRequestDto;
import nyggs.accounts.reconciliation.entity.ReconciliationResult;

public interface ReconciliationResultRepository
		extends JpaRepository<ReconciliationResult, Long>, JpaSpecificationExecutor<ReconciliationResult> {

	@Query(value = "select * from reconciliation_result where account_id = :accountId and status = :statusId", nativeQuery = true)
	List<ReconciliationResult> findAllReconciliationResults(Long accountId, Integer statusId);

	static Specification<ReconciliationResult> getReconciliationSpecification(DashboardRequestDto request) {
		return (root, query, cb) -> {
			Predicate predicate = cb.conjunction();

			if (request.getAccountId() != null) {
				predicate = cb.and(predicate, cb.equal(root.get("accountId"), request.getAccountId()));
			}
			if (request.getStatusId() != null) {
				predicate = cb.and(predicate, cb.equal(root.get("status"), request.getStatusId()));
			}
			if (request.getFromDate() != null) {
				predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("createdDate"), request.getFromDate()));
			}
			if (request.getToDate() != null) {
				predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("createdDate"), request.getToDate()));
			}

			return predicate;
		};
	}

	default Page<ReconciliationResult> findAllWithFilters(DashboardRequestDto request, Pageable pageable) {
		return findAll(getReconciliationSpecification(request), pageable);
	}

}
