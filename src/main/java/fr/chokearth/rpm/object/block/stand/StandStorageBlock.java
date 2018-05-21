package fr.chokearth.rpm.object.block.stand;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.init.BlockInit;
import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.object.block.base.BlockBase;
import fr.chokearth.rpm.object.block.base.BlockBaseAdjustableHorizontal;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import fr.chokearth.rpm.object.item.ItemPlaceStand;
import fr.chokearth.rpm.util.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class StandStorageBlock extends BlockBase{
    public StandStorageBlock(String name, Material material, CreativeTabs creativeTabs) {
        super(name, material, creativeTabs, false, new double[]{0, 0, 0, 1, 0.5, 1});
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        pos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());

        if(worldIn.getTileEntity(pos) instanceof TileEntityStand) {

            if (!worldIn.isRemote && ((TileEntityStand) worldIn.getTileEntity(pos)).getOwner().equals(playerIn.getName())) {
                playerIn.openGui(Main.instance, Reference.GUI_STAND_STORAGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
        }
    }
}
