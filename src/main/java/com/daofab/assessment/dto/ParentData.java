package com.daofab.assessment.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class object to retrieve Parent json data from file Parent.json
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentData {

	private List<TransactionDto> data;
}
