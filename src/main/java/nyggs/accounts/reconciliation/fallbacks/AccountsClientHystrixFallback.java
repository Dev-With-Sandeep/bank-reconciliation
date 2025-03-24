package nyggs.accounts.reconciliation.fallbacks;

import org.springframework.http.HttpStatus;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.TransactionRequestDto;
import nyggs.accounts.reconciliation.feignClient.AccountsClient;

public class AccountsClientHystrixFallback implements AccountsClient {

	@Override
	public CustomResponse findTransactionsForReconciliation(TransactionRequestDto requestDto, String authHead) {
		return new CustomResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), null,
				HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());

	}
}
