package org.jurassicraft.server.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.container.IncubatorContainer;
import org.jurassicraft.server.item.DinosaurEggItem;
import org.jurassicraft.server.item.ItemHandler;

public class IncubatorTile extends MachineBaseTile
{
    private static final int[] INPUTS = new int[] { 0, 1, 2, 3, 4 };
    private static final int[] OUTPUTS = new int[0];

    private int[] temperature = new int[5];

    private ItemStack[] slots = new ItemStack[6];

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        for (int i = 0; i < getProcessCount(); i++)
        {
            temperature[i] = compound.getShort("Temperature" + i);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);

        for (int i = 0; i < getProcessCount(); i++)
        {
            compound.setShort("Temperature" + i, (short) this.temperature[i]);
        }

        return compound;
    }

    @Override
    protected int getProcess(int slot)
    {
        if (slot == 5)
        {
            return 0;
        }
        else
        {
            return slot;
        }
    }

    @Override
    protected boolean canProcess(int process)
    {
        return slots[process] != null && slots[process].stackSize > 0 && slots[process].getItem() instanceof DinosaurEggItem;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process) && !worldObj.isRemote)
        {
            ItemStack egg = slots[process];

            ItemStack incubatedEgg = new ItemStack(ItemHandler.HATCHED_EGG, 1, egg.getItemDamage());
            NBTTagCompound compound = new NBTTagCompound();
            compound.setBoolean("Gender", temperature[process] > 50);

            if (egg.getTagCompound() != null)
            {
                compound.setString("Genetics", egg.getTagCompound().getString("Genetics"));
                compound.setInteger("DNAQuality", egg.getTagCompound().getInteger("DNAQuality"));
            }

            incubatedEgg.setTagCompound(compound);

            slots[process] = incubatedEgg;
        }
    }

    @Override
    protected int getMainOutput(int process)
    {
        return 0;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack)
    {
        return 8000;
    }

    @Override
    protected int getProcessCount()
    {
        return 5;
    }

    @Override
    protected int[] getInputs()
    {
        return INPUTS;
    }

    @Override
    protected int[] getInputs(int process)
    {
        return new int[] { process };
    }

    @Override
    protected int[] getOutputs()
    {
        return OUTPUTS;
    }

    @Override
    protected ItemStack[] getSlots()
    {
        return slots;
    }

    @Override
    protected void setSlots(ItemStack[] slots)
    {
        this.slots = slots;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new IncubatorContainer(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.MODID + ":incubator";
    }

    @Override
    public String getName()
    {
        return hasCustomName() ? customName : "container.incubator";
    }

    @Override
    public int getField(int id)
    {
        if (id < 5)
        {
            return processTime[id];
        }
        else if (id < 10)
        {
            return totalProcessTime[id - 5];
        }
        else if (id < 15)
        {
            return temperature[id - 10];
        }

        return 0;
    }

    @Override
    public void setField(int id, int value)
    {
        if (id < 5)
        {
            processTime[id] = value;
        }
        else if (id < 10)
        {
            totalProcessTime[id - 5] = value;
        }
        else if (id < 15)
        {
            temperature[id - 10] = value;
        }
    }

    public String getCommandSenderName() // Forge Version compatibility, keep both getName and getCommandSenderName
    {
        return getName();
    }
}
