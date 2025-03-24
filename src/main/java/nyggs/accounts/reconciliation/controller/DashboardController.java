package nyggs.accounts.reconciliation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.DashboardRequestDto;
import nyggs.accounts.reconciliation.service.DashboardService;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@RequestMapping(value = "/get/all")
	public ResponseEntity<?> getAll(@RequestBody DashboardRequestDto requestDto) {
		CustomResponse response = dashboardService.findReconciliationTransactionsByFilter(requestDto);
		return ResponseEntity.ok(response);
	}
}
