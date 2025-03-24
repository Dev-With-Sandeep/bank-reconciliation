package nyggs.accounts.reconciliation.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.DashboardRequestDto;
import nyggs.accounts.reconciliation.dto.ReconcileResponseDto;
import nyggs.accounts.reconciliation.entity.BankStatement;
import nyggs.accounts.reconciliation.entity.ReconciliationResult;
import nyggs.accounts.reconciliation.enums.TransactionType;
import nyggs.accounts.reconciliation.repository.BankStatementRepository;
import nyggs.accounts.reconciliation.repository.ReconciliationResultRepository;
import nyggs.accounts.reconciliation.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private BankStatementRepository bankStatementRepository;

	@Autowired
	private ReconciliationResultRepository reconciliationResultRepository;

	@Override
	public CustomResponse findReconciliationTransactionsByFilter(DashboardRequestDto requestDto) {
		try {
//			List<ReconciliationResult> reconciliationResultList = reconciliationResultRepository
//					.findAllReconciliationResults(requestDto.getAccountId(), requestDto.getStatusId());
			Pageable pageable = PageRequest.of(requestDto.getPageNo().intValue(), requestDto.getPageSize());
			Page<ReconciliationResult> reconciliationResultPage = reconciliationResultRepository
	                .findAllWithFilters(requestDto, pageable);
	        List<ReconciliationResult> reconciliationResultList = reconciliationResultPage.getContent();
			Set<Long> bankStatementIds = reconciliationResultList.stream().map(ReconciliationResult::getBankStatementId)
					.collect(Collectors.toSet());
			List<BankStatement> bankStatementList = bankStatementRepository.findAllById(bankStatementIds);

			List<ReconcileResponseDto> reconcileResponseDtoList = new ArrayList<>();

			for (ReconciliationResult reconciliationResult : reconciliationResultList) {
				Optional<BankStatement> bankStatementOptional = bankStatementList.stream()
						.filter(txn -> txn.getId().equals(reconciliationResult.getBankStatementId())).findFirst();
				ReconcileResponseDto responseDto = new ReconcileResponseDto();
				responseDto.setId(reconciliationResult.getId());
				responseDto.setAccountId(reconciliationResult.getAccountId());
				responseDto.setAccountNumber(reconciliationResult.getAccountNumber());
				responseDto.setAccountName(reconciliationResult.getAccountName());
				if (bankStatementOptional.isPresent()) {
					if (bankStatementOptional.get().getTransactionType().getId()
							.equals(TransactionType.DEBIT.getId())) {
					} else {
						responseDto.setDepositAmount(bankStatementOptional.get().getAmount());
					}
					responseDto.setTransactionType(bankStatementOptional.get().getTransactionType().getName());
					responseDto.setBalance(bankStatementOptional.get().getBalance());
				} else {

					responseDto.setBalance(null);
					responseDto.setBalance(null);
				}
				responseDto.setDayBookTxnId(reconciliationResult.getDaybookTxnId());
				responseDto.setStatus(reconciliationResult.getStatus().getName());
				responseDto.setBankStatementId(reconciliationResult.getBankStatementId());
				reconcileResponseDtoList.add(responseDto);

			}

			return new CustomResponse(HttpStatus.OK.value(), reconcileResponseDtoList, "Success");
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
		}
	}

}
