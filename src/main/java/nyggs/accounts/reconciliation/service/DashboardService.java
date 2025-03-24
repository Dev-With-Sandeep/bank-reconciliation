package nyggs.accounts.reconciliation.service;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.DashboardRequestDto;

public interface DashboardService {

	CustomResponse findReconciliationTransactionsByFilter(DashboardRequestDto requestDto);
}
