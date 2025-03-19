package nyggs.accounts.reconciliation.service;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.ReconcileRequestDto;

public interface TestService {

	public CustomResponse reconcileStatement(ReconcileRequestDto requestDto);
}
