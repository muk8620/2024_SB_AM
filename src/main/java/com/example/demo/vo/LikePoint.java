package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikePoint {
	private int id;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private int point;
}
