package com.info.settlespot.userservice.controller;

import com.info.settlespot.userservice.dto.HostResponseDTO;
import com.info.settlespot.userservice.dto.LoginRequestDTO;
import com.info.settlespot.userservice.dto.TenantResponseDTO;
import com.info.settlespot.userservice.entity.Host;
import com.info.settlespot.userservice.entity.Tenant;
import com.info.settlespot.userservice.service.HostService;
import com.info.settlespot.userservice.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final TenantService tenantService;
    private final HostService hostService;

    public UserController(TenantService tenantService, HostService hostService) {
        this.tenantService = tenantService;
        this.hostService = hostService;
    }

    @PostMapping("/tenants/register")
    public ResponseEntity<TenantResponseDTO> registerTenant(@RequestBody Tenant tenant) {
        TenantResponseDTO newTenant = tenantService.registerTenant(tenant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTenant);
    }

    @PostMapping("/tenants/login")
    public ResponseEntity<TenantResponseDTO> loginTenant(@RequestBody LoginRequestDTO loginRequest) {
        TenantResponseDTO tenantDTO = tenantService.loginTenant(loginRequest);
        return ResponseEntity.ok(tenantDTO);
    }

    @GetMapping("/tenants/{id}")
    public ResponseEntity<TenantResponseDTO> getTenant(@PathVariable Integer id) {
        TenantResponseDTO tenantDTO = tenantService.getTenantById(id);
        return ResponseEntity.ok(tenantDTO);
    }
    // =============================================================================================================
    //                                                   HOST APIs
    // ==============================================================================================================

    @PostMapping("/hosts/register")
    public ResponseEntity<HostResponseDTO> registerHost(@RequestBody Host host) {
        HostResponseDTO newHost = hostService.registerHost(host);
        // Returns Status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(newHost);
    }

    @PostMapping("/hosts/login")
    public ResponseEntity<HostResponseDTO> loginHost(@RequestBody LoginRequestDTO loginRequest) {
        HostResponseDTO hostDTO = hostService.loginHost(loginRequest);
    
        return ResponseEntity.ok(hostDTO);
    }

    @GetMapping("/hosts/{id}")
    public ResponseEntity<HostResponseDTO> getHost(@PathVariable Integer id) {
        HostResponseDTO hostDTO = hostService.getHostById(id);
        return ResponseEntity.ok(hostDTO);
    }

   
    @GetMapping("/hosts/{id}/exists")
    public ResponseEntity<Boolean> doesHostExist(@PathVariable Integer id) {
        boolean exists = hostService.hostExists(id);
        return ResponseEntity.ok(exists);
    }
}