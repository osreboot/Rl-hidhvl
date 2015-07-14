package com.osreboot.ridhvl.menu.component;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;

public class HvlPanel extends HvlComponent {

	protected List<HvlComponent> children;
	
	protected HvlPanel(float wArg, float hArg) {
		super(wArg, hArg);
		children = new LinkedList<>();
	}

	protected HvlPanel(float xArg, float yArg, float wArg, float hArg) {
		super(xArg, yArg, wArg, hArg);
		children = new LinkedList<>();
	}

	@Override
	public void update(float delta) {
		for (HvlComponent comp : children) {
			if (comp == null)
				continue;
			comp.update(delta);
		}
	}

	@Override
	public void draw(float delta) {
		for (HvlComponent comp : children) {
			if (comp == null)
				continue;
			if (comp.isVisible())
				comp.draw(delta);
		}
	}

	public void add(HvlComponent toAdd) {
		children.add(toAdd);
	}

	public void remove(HvlComponent toRemove) {
		children.remove(toRemove);
	}

	public void remove(int i) {
		children.remove(i);
	}

	public void removeAll() {
		children.clear();
	}

	public HvlComponent get(int i) {
		return children.get(i);
	}

	public int getChildCount() {
		return children.size();
	}

	public static class Builder {
		private HvlPanel tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlPanel.class))
				tr = HvlComponentDefault.getDefault(HvlPanel.class).clone();
			else
				tr = new HvlPanel(0, 0, 0, 0);
		}

		public Builder add(HvlComponent toAdd) {
			tr.add(toAdd);
			return this;
		}

		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public void setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public HvlPanel build() {
			return tr;
		}
	}

	public HvlPanel clone() {
		HvlPanel tr = new HvlPanel(0, 0);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlPanel
		tr.children = children; // TODO: This might not be ideal due to
								// references.
		return tr;
	}
}
