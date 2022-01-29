package com.daofab.assessment.dto; 
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class object to retrieve Child json data from file Child.json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildData {

	private List<InstallmentDto> data;
}
