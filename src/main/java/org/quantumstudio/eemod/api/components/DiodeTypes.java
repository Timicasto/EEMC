package org.quantumstudio.eemod.api.components;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum DiodeTypes implements StringRepresentable {
	N4001("n4001", 50, 0.93F),
	N4002("n4002", 100, 0.93F),
	N4003("n4003", 200, 0.93F),
	N4004("n4004", 400, 0.93F),
	N4005("n4005", 600, 0.93F),
	N4006("n4006", 800, 0.93F),
	N4007("n4007", 1000, 0.93F)
	;

	final String name;
	final int breakdownVoltage;
	final float forwardDrop;

	DiodeTypes(String name, int breakdownVoltage, float forwardDrop) {
		this.name = name;
		this.breakdownVoltage = breakdownVoltage;
		this.forwardDrop = forwardDrop;
	}

	@Override
	public @NotNull String getSerializedName() {
		return this.name;
	}

	public int getBreakdownVoltage() {
		return breakdownVoltage;
	}

	public float getForwardDrop() {
		return forwardDrop;
	}
}
