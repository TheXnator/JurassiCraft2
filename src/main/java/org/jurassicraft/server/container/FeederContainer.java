package org.jurassicraft.server.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jurassicraft.server.container.slot.CustomSlot;
import org.jurassicraft.server.entity.base.Diet;
import org.jurassicraft.server.food.FoodHelper;
import org.jurassicraft.server.tile.FeederTile;

public class FeederContainer extends Container
{
    private FeederTile tile;

    public FeederContainer(InventoryPlayer inventory, FeederTile tile)
    {
        this.tile = tile;

        int id = 0;

        for (int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(inventory, id, 8 + x * 18, 142));
            id++;
        }

        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(inventory, id, 8 + x * 18, 84 + y * 18));
                id++;
            }
        }

        id = 0;

        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                this.addSlotToContainer(new CustomSlot(tile, id, 26 + x * 18, 18 + y * 18, stack -> FoodHelper.INSTANCE.canDietEat(Diet.CARNIVORE, stack.getItem())));
                id++;
            }
        }

        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                this.addSlotToContainer(new CustomSlot(tile, id, 98 + x * 18, 18 + y * 18, stack -> FoodHelper.INSTANCE.canDietEat(Diet.HERBIVORE, stack.getItem())));
                id++;
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        return null;
    }
}
