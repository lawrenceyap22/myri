package com.epicnoobz.myri.utils;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
public class MyriTexturePacker {
	private static final String INPUT_DIR = "images/champions";
	private static final String OUTPUT_DIR = "../myri-android/assets/image-atlases";
	private static final String PACK_FILE = "champions-images-packed";

	public static void main(String[] args){
		TexturePacker2.process(INPUT_DIR, OUTPUT_DIR, PACK_FILE);
	}
}
