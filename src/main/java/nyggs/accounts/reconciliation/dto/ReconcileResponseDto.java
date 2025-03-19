package nyggs.accounts.reconciliation.dto;

import java.util.List;

public class ReconcileResponseDto {

	private List<BankStatementDto> transactionsNotFoundList;
	
	private List<MergedTransactionDto> mergedTransactionDtoList;
}
