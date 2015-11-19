package com.osreboot.ridhvl.painter.painter2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;

public class HvlFontPainter2D {

	private Texture image;
	private char[] layout;
	private float textureWidth, textureHeight, fontWidth, fontHeight;
	private int rowCount;
	private float rowSpacing;

	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, float textureWidthArg, float textureHeightArg,
			float fontWidthArg, float fontHeightArg, int rowCountArg) {
		image = imageArg;
		layout = layoutArg;
		textureWidth = textureWidthArg;
		textureHeight = textureHeightArg;
		fontWidth = fontWidthArg;
		fontHeight = fontHeightArg;
		rowCount = rowCountArg;
		rowSpacing = 0f;
	}

	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, float textureWidthArg, float textureHeightArg,
			float fontWidthArg, float fontHeightArg, int rowCountArg, float rowSpaceArg) {
		image = imageArg;
		layout = layoutArg;
		textureWidth = textureWidthArg;
		textureHeight = textureHeightArg;
		fontWidth = fontWidthArg;
		fontHeight = fontHeightArg;
		rowCount = rowCountArg;
		rowSpacing = rowSpaceArg;
	}

	// TODO: Boolean for stretching individual lines?
	public void drawWord(String text, float x, float y, float width, float height, Color c) {
		String[] lines = text.split("\n");

		for (int l = 0; l < lines.length; l++) {
			String line = lines[l];
			int lineLength = line.length();
			for (int i = 0; i < lineLength; i++) {
				if (HvlFontUtil.containsChar(layout, line.charAt(i))) {
					int xpos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) % rowCount;
					int ypos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) / rowCount;
					float charWidth = width / line.length();

					HvlPainter2D.hvlDrawQuad(x + (i * charWidth),
							y + (l * ((height - (rowSpacing * lines.length)) / lines.length)) + (l * rowSpacing),
							charWidth, height / lines.length - (lines.length - 1) * rowSpacing,
							(fontWidth / textureWidth) * xpos, (fontHeight / textureHeight) * ypos,
							(fontWidth / textureWidth) * xpos + (fontWidth / textureHeight),
							(fontHeight / textureHeight) * ypos + (fontHeight / textureHeight), image, c);
				}
			}
		}
	}

	public void drawWord(String text, float x, float y, Color c) {
		String[] lines = text.split("\n");

		for (int l = 0; l < lines.length; l++) {
			String line = lines[l];

			for (int i = 0; i < line.length(); i++) {
				if (HvlFontUtil.containsChar(layout, line.charAt(i))) {
					int xpos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) % rowCount;
					int ypos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) / rowCount;

					HvlPainter2D.hvlDrawQuad(x + (i * fontWidth), y + (l * fontHeight) + (l * rowSpacing), fontWidth,
							fontHeight, (fontWidth / textureWidth) * xpos, (fontHeight / textureHeight) * ypos,
							(fontWidth / textureWidth) * xpos + (fontWidth / textureHeight),
							(fontHeight / textureHeight) * ypos + (fontHeight / textureHeight), image, c);
				}
			}
		}
	}

	// TODO: Should spacing be affected by scale?
	public void drawWord(String text, float x, float y, float scale, Color c) {
		String[] lines = text.split("\n");

		for (int l = 0; l < lines.length; l++) {
			String line = lines[l];

			for (int i = 0; i < line.length(); i++) {
				if (HvlFontUtil.containsChar(layout, line.charAt(i))) {
					int xpos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) % rowCount;
					int ypos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) / rowCount;

					HvlPainter2D.hvlDrawQuad(x + (i * fontWidth * scale), y + (l * fontHeight * scale) + (l * rowSpacing * scale), fontWidth * scale, fontHeight * scale,
							(fontWidth / textureWidth) * xpos, (fontHeight / textureHeight) * ypos,
							(fontWidth / textureWidth) * xpos + (fontWidth / textureHeight),
							(fontHeight / textureHeight) * ypos + (fontHeight / textureHeight), image, c);
				}
			}
		}
	}

	public float getFontWidth() {
		return fontWidth;
	}

	public float getFontHeight() {
		return fontHeight;
	}

	public float getLineWidth(String text) {
		return getFontWidth() * text.length();
	}

}
