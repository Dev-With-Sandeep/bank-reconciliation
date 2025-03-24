package nyggs.accounts.reconciliation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.ReconcileRequestDto;
import nyggs.accounts.reconciliation.service.TestService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestService testService;

	@PostMapping("/reconcile/statement")
	public ResponseEntity<?> reconcileStatement(@ModelAttribute ReconcileRequestDto requestDto) {
		CustomResponse response = testService.reconcileStatement(requestDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/reconcile/now")
	public ResponseEntity<?> reconcileNow() {
		CustomResponse response = testService.reconcileNow();
		return ResponseEntity.ok(response);
	}
}
