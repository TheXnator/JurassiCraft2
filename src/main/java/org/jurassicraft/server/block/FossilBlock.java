package org.jurassicraft.server.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.server.api.SubBlocksBlock;
import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.EntityHandler;
import org.jurassicraft.server.item.itemblock.FossilItemBlock;
import org.jurassicraft.server.tab.TabHandler;

import java.util.List;

public class FossilBlock extends Block implements SubBlocksBlock
{
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15);

    private int start;

    public FossilBlock(int start)
    {
        super(Material.ROCK);
        this.setHardness(2.0F);
        this.setResistance(8.0F);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(TabHandler.FOSSILS);

        this.start = start;

        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, 0));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(this, 1, getMetaFromState(state));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        List<Dinosaur> dinosaurs = EntityHandler.getDinosaurs();

        for (int i = 0; i < 16; i++)
        {
            int dinoIndex = i + start;

            if (dinoIndex >= dinosaurs.size())
            {
                break;
            }

            if (dinosaurs.get(dinoIndex).shouldRegister())
            {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    public Dinosaur getDinosaur(int metadata)
    {
        return EntityHandler.getDinosaurById(start + metadata);
    }

    @Override
    public String getHarvestTool(IBlockState state)
    {
        return "pickaxe";
    }

    @Override
    public int getHarvestLevel(IBlockState state)
    {
        return 1;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }

    @Override
    public ItemBlock getItemBlock()
    {
        return new FossilItemBlock(this);
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        return false;
    }
}
