package org.jurassicraft.server.block.machine;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.handler.GuiHandler;
import org.jurassicraft.server.tab.TabHandler;
import org.jurassicraft.server.tile.FeederTile;

public class FeederBlock extends BlockContainer
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public FeederBlock()
    {
        super(Material.IRON);
        this.setUnlocalizedName("feeder");
        this.setHardness(2.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(TabHandler.BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tile = worldIn.getTileEntity(pos);

            if (tile instanceof FeederTile)
            {
                ((FeederTile) tile).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof FeederTile)
        {
            InventoryHelper.dropInventoryItems(world, pos, (FeederTile) tileentity);
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.isSneaking())
        {
            TileEntity tileEntity = world.getTileEntity(pos);

            if (tileEntity instanceof FeederTile)
            {
                FeederTile feeder = (FeederTile) tileEntity;

                if (feeder.isUseableByPlayer(player))
                {
                    player.openGui(JurassiCraft.INSTANCE, GuiHandler.FEEDER_ID, world, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new FeederTile();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getFront(meta);

        if (facing.getAxis() == EnumFacing.Axis.Y)
        {
            facing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return true;
    }
}
