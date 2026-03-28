package payroll.api.service;

import payroll.api.model.ApiEmployee;
import payroll.api.model.BackupResponse;
import payroll.api.model.PayslipRequest;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PayrollApiService {
    
    // Employee endpoints
    @GET("employees")
    Call<List<ApiEmployee>> getAllEmployees();
    
    @GET("employees/{id}")
    Call<ApiEmployee> getEmployee(@Path("id") String employeeId);
    
    @POST("employees")
    Call<ApiEmployee> createEmployee(@Body ApiEmployee employee);
    
    @PUT("employees/{id}")
    Call<ApiEmployee> updateEmployee(@Path("id") String employeeId, @Body ApiEmployee employee);
    
    @DELETE("employees/{id}")
    Call<Void> deleteEmployee(@Path("id") String employeeId);
    
    // Payslip endpoints
    @POST("payslips/generate")
    Call<Void> generatePayslip(@Body PayslipRequest request);
    
    @GET("payslips/employee/{employeeId}")
    Call<List<PayslipRequest>> getEmployeePayslips(@Path("employeeId") String employeeId);
    
    // Backup endpoints
    @POST("backup")
    Call<BackupResponse> createBackup();
    
    @GET("backup/restore")
    Call<BackupResponse> restoreBackup();
    
    // Reports
    @GET("reports/salary-range")
    Call<List<ApiEmployee>> getEmployeesBySalaryRange(
        @Query("min") double minSalary, 
        @Query("max") double maxSalary
    );
    
    // Health check
    @GET("health")
    Call<Void> healthCheck();
}