package com.cos.javagg.model.champion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChampList <T> {
	private String type;
	private String format;
	private String version;
	private data data;
}
