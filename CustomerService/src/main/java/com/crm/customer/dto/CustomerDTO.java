package com.crm.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO
{	    
    private Integer customerId;

	@NotBlank(message = "Customer name cannot be blank")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
	private String customerName;

	@NotBlank(message = "Customer email cannot be blank")
    @Email(message = "Email should be valid")
    private String customerEmail;

	@NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid") 
	private String phoneNo;

    @NotBlank(message = "Interactions cannot be blank")
    private String interactions;
}
