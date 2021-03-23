package com.cos.javagg.model.champion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aatrox {
	private String version;
	private String id;
	private Long key;
	private String name;
	private String title;
	private String blurb;
	private Info info;
	private Image image;
	private Tags tags;
	private Stats stats;
}
