package fr.chokearth.rpm.object.block.stand;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.init.BlockInit;
import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.object.block.base.BlockBaseAdjustableHorizontal;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import fr.chokearth.rpm.object.item.ItemPlaceStand;
import fr.chokearth.rpm.util.Reference;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class StandSellBlock extends BlockBaseAdjustableHorizontal implements ITileEntityProvider{

    public StandSellBlock(String name, Material material, CreativeTabs creativeTabs, boolean rotateAssistance) {
        super(name, material, creativeTabs, rotateAssistance, false, new double[]{0, -0.5, 0, 1, 0.5, 1});
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock() instanceof StandStorageBlock) {
            if (((TileEntityStand) worldIn.getTileEntity(pos)).getOwner().equals(playerIn.getName())) {
                playerIn.openGui(Main.instance, Reference.GUI_STAND_SELL_OWNER, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }else{
                playerIn.openGui(Main.instance, Reference.GUI_STAND_SELL_BUYER, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if(!(stack.getItem() instanceof ItemPlaceStand)){
            worldIn.setBlockToAir(pos);
            ItemStack itemStack = worldIn.getPlayerEntityByName(placer.getName()).inventory.getCurrentItem();
            if(itemStack.isEmpty())
                itemStack = new ItemStack(stack.getItem());
            else
                itemStack.grow(1);
        }else {
            super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
            ((TileEntityStand) worldIn.getTileEntity(pos)).setOwner(placer.getName());
        }
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityStand();
    }
}
