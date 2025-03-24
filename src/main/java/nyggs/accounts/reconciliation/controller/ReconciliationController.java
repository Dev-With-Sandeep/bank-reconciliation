package nyggs.accounts.reconciliation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.ReconcileRequestDto;
import nyggs.accounts.reconciliation.service.ReconciliationService;

@RestController
@RequestMapping(value = "/reconcile")
public class ReconciliationController {

	@Autowired
	private ReconciliationService reconciliationService;

	@RequestMapping(value = "/v1/import/statement")
	public ResponseEntity<?> importBankStatement(@ModelAttribute ReconcileRequestDto reconcileRequestDto) {
		CustomResponse response = reconciliationService.importBankStatement(reconcileRequestDto);
		return ResponseEntity.ok(response);
	}
}
