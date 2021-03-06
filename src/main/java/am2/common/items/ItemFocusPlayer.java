package am2.common.items;

import am2.api.ArsMagicaAPI;
import am2.api.affinity.Affinity;
import am2.api.items.ItemFilterFocus;
import am2.common.defs.ItemDefs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemFocusPlayer extends ItemFilterFocus{

	public ItemFocusPlayer(){
		super();
	}

	@Override
	public Class<? extends Entity> getFilterClass(){
		return EntityPlayer.class;
	}

	@Override
	public Object[] getRecipeItems(){
		return new Object[]{
				"L",
				"F",
				'L', new ItemStack(ItemDefs.essence, 1, (Affinity.LIFE).getID()),
				'F', ItemDefs.standardFocus
		};
	}

	@Override
	public String getInGameName(){
		return "Player Focus";
	}
}
