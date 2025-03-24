package nyggs.accounts.reconciliation.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.TransactionRequestDto;
import nyggs.accounts.reconciliation.fallbacks.AccountsClientHystrixFallback;

@FeignClient(name = "AccountingServiceSandeep", fallback = AccountsClientHystrixFallback.class)
public interface AccountsClient {
	
	@RequestMapping(value = "account-transaction/v1/find/by/account/ids", method = RequestMethod.POST)
	public CustomResponse findTransactionsForReconciliation(@RequestBody TransactionRequestDto requestDto, @RequestHeader("auth-head") String authHead);
}
