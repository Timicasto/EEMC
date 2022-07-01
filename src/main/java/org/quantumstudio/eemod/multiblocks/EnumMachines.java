package org.quantumstudio.eemod.multiblocks;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.quantumstudio.eemod.api.IEnumPropertyBlock;

public enum EnumMachines implements IEnumPropertyBlock, StringRepresentable {
	COKE_OVEN(false),
	BUFFER(false)
	;

	public final boolean customBlockState;

	EnumMachines(boolean customBlockState) {
		this.customBlockState = customBlockState;
	}

	@Override
	public boolean displayInItemGroup() {
		return false;
	}

	@Override
	public @NotNull String getSerializedName() {
		return this.toString().toLowerCase();
	}

	public String getCustomState() {
		return getSerializedName().toLowerCase();
	}
}
